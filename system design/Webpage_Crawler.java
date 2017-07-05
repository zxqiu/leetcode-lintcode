/*

Webpage Crawler

Implement a webpage Crawler to crawl webpages of http://www.wikipedia.org/. To simplify the question, let's use url instead of the the webpage content.

Your crawler should:

Call HtmlHelper.parseUrls(url) to get all urls from a webpage of given url.
Only crawl the webpage of wikipedia.
Do not crawl the same webpage twice.
Start from the homepage of wikipedia: http://www.wikipedia.org/

 Notice

You need do it with multithreading.


解：
这道题的基本思路是主线程用来设置seed并启动crawler，返回结果，子线程进行实质操作。
由于最费时间的工作是parseUrls（），所以子线程并行运作可以加速。

先考虑只有一个子线程的情况：
需要有一个List用来保存已经处理过的URL，以及将要处理的URL。为了保证下一次取到的是新的URL，所以加入一个整数变量next来记录下一个将要取出的URL。
当next等于或者大于（实际上应当不可能大于）List的大小的时候，表示List中所有的URL都已经被访问过。这时子线程应当进入循环检查next和List的大小，直到有新的URL被加入List。
如果一个URL已经被处理过，就不应当再被放进List中。即List中不应当有重复的URL。为了快速查重，需要一个HashSet来保存与List中一样的内容。每次加入新的URL之前，检查Set中是否已经存在该URL。

推广到多个子线程的情况：
这时需要考虑的是List有可能存在多重访问的问题。
比如，如果两个线程同时通过了next和List大小的检查，但是一个线程先拿走了最后一个URL，那么这时另一个线程再去拿的时候就会超界。

有很多做法可以解决这个问题。比如LinkedBlockingQueue，Vector等。这里使用ReentrantLock来同时保证List和next以及Set的同步。


剩下的问题就是主线程如何控制子线程退出。

通过判断所有子线程都不在处理任何URL来作为运行结束标志。

使用一个AtomicLong变量，初始化为0。当某一个子线程开始处理URL时，就把它增加1，处理完就减小1。
当所有子线程都不再处理URL时，该变量为0.故主线程读到该变量为0时可以结束所有子线程并返回结果。


*/



/**
 * public class HtmlHelper {
 *     public static List<String> parseUrls(String url);
 *         // Get all urls from a webpage of given url. 
 * }
*/

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicLong;


public class Solution {
    /**
     * @param url a url of root page
     * @return all urls
     */
    
    
    Set<String> urls;
    Queue<String> urlPool;
    ReentrantLock lock;
    private static AtomicLong finished = new AtomicLong(0);
    
    public List<String> crawler(String url) {
        urls = new HashSet<String>();
        urlPool = new LinkedList<String>();
        lock = new ReentrantLock();
        CrawlerThread[] workers = new CrawlerThread[16];
        
        urlPool.offer(url);
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new CrawlerThread();
            workers[i].start();
        }
        
        while (!isAllFinished() || urlPool.size() != 0);
        
        return new ArrayList<String>(urls);
    }
    
    private class CrawlerThread extends Thread {
        public void run() {
            while (true) {
                String url = null;
                try {
                    lock.lock();
                    if (urlPool.size() == 0) {
                        if (isAllFinished()) {
                            break;
                        }
                        continue;
                    }
                    
                    url = urlPool.poll();
                    if (urls.contains(url)) {
                        continue;
                    }
                } finally {
                    lock.unlock();
                }
                
                if (url != null) {
                    finished.incrementAndGet();
                    urls.add(url);
                    for (String s : HtmlHelper.parseUrls(url)) {
                        if (s.indexOf("wikipedia.org") >= 0) {
                            urlPool.offer(s);
                        }
                    }
                    finished.decrementAndGet();
                }
            }
        }
    }
    
    public static boolean isAllFinished() {
        return (finished.get() == 0);
    }
}
