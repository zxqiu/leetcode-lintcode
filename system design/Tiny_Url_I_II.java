/*

Tiny Url


Given a long url, make it shorter. To make it simpler, let's ignore the domain name.

You should implement two methods:

    longToShort(url). Convert a long url to a short url.
    shortToLong(url). Convert a short url to a long url starts with http://tiny.url/.

You can design any shorten algorithm, the judge only cares about two things:

    The short key's length should equal to 6 (without domain and slash). And the acceptable characters are [a-zA-Z0-9]. For example: abcD9E
    No two long urls mapping to the same short url and no two short urls mapping to the same long url.


 Example

Given url = http://www.lintcode.com/faq/?id=10, run the following code (or something similar):

short_url = longToShort(url) // may return http://tiny.url/abcD9E
long_url = shortToLong(short_url) // return http://www.lintcode.com/faq/?id=10

The short_url you return should be unique short url and start with http://tiny.url/ and 6 acceptable characters. For example "http://tiny.url/abcD9E" or something else.

The long_url should be http://www.lintcode.com/faq/?id=10 in this case.


解：
Long url转short url分四步：
一，判断是否已经转换过。若已经转换过，直接取值返回。为此，保存两张表，从long到short和从short到long。
二，取出long url中除了网站地址的部分key。在此使用正则表达式。
三，用hash function把key进行转换。在此使用简单计数方式作hash。每转换一次，count加一。将count转成62进制数，然后若不够6位在前面补0。6位短网址有62^6～64^6～2^36个，count需要用long型才够用。
四，把转换好的短网址接上短网址前缀然后存入两个map中。

Short url转long url直接从map中取值即可。


*/



import java.util.regex.*;

public class TinyUrl {
    /**
     * @param url a long url
     * @return a short url starts with http://tiny.url/
     */
     
    private static long count = 0;
    private static Map<String, String> s2l = new HashMap<>();
    private static Map<String, String> l2s = new HashMap<>();
    private static final String prefix = "http://tiny.url/";
    
    public String longToShort(String url) {
        if (l2s.containsKey(url)) {
            return l2s.get(url);
        }
        
        Pattern reg = Pattern.compile("https?:\\/\\/.*\\.\\w+\\/(.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = reg.matcher(url);
        
        if (!matcher.find()) {
            return "";
        }
        
        String key = matcher.group(1);
        String shortUrl = prefix + hashLong(key);
        
        s2l.put(shortUrl, url);
        l2s.put(url, shortUrl);
        
        return shortUrl;
    }

    /**
     * @param url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String url) {
        if (s2l.containsKey(url)) {
            return s2l.get(url);
        }
        
        return "";
    }
    
    private String hashLong(String url) {
        String ret = "";
        long div, res;
        int digits = 0;
        
        div = count++;
        
        do {
            res = div % 62;
            div = div / 62;
            
            char c = '0';
            if (res >= 0 && res <= 9) {
                c = (char)(res + '0');
            } else if (res >= 10 && res <= 35) {
                c = (char)(res - 10 + 'a');
            } else {
                c = (char)(res - 36 + 'A');
            }
            ret = String.valueOf(c) + ret;
            digits++;
        } while (div > 0 || digits < 6);
        
        return ret;
    }
}

/*

Tiny Url II




As a follow up for Tiny URL, we are going to support custom tiny url, so that user can create their own tiny url.

 Notice

Custom url may have more than 6 characters in path.


 Example

createCustom("http://www.lintcode.com/", "lccode")
>> http://tiny.url/lccode
createCustom("http://www.lintcode.com/", "lc")
>> error
longToShort("http://www.lintcode.com/problem/")
>> http://tiny.url/1Ab38c   // this is just an example, you can have you own 6 characters.
shortToLong("http://tiny.url/lccode")
>> http://www.lintcode.com/
shortToLong("http://tiny.url/1Ab38c")
>> http://www.lintcode.com/problem/
shortToLong("http://tiny.url/1Ab38d")
>> null



解：
这道题的重点在理解题意。
首先，custom tiny URL不受到普通tiny URL的各种限制，比如长度，字符类型。所以直接对输入的custom tiny URL进行查重然后插入表中就可以了。
其次，对于普通的long URL到short URL的转换与上面完全一致，直接照抄过来。

由于作为global ID的count不能检查custom tiny URL，所以可能会出现custom tiny URL覆盖或者被普通URL覆盖的情况。

如果一定要防止这种情况发生，需要设定以下原则：
一，在生成普通tiny URL时，如果发现long URL被生成过，不管是否是custom tiny URL，都直接返回该值。
二，在生成普通tiny URL时，如果发现生成的short URL已经存在，需要重新生成，即count++后重生成。
三，如果一个long URL已经有了对应的custom tiny URL，再次生成不同的custom tiny URL时直接返回错误。但是如果只是有了一个普通的tiny URL，则覆盖这个普通tiny URL。
    为此，增加一个isCustom map来标记一个long URL对应的short URL是否是customized。

*/


import java.util.regex.*;

public class TinyUrl2 {
    private static long count = 0;
    private static Map<String, String> s2l = new HashMap<>();
    private static Map<String, String> l2s = new HashMap<>();
    private static Map<String, Boolean> isCustom = new HashMap<>();
    private static final String prefix = "http://tiny.url/";
    
    /**
     * @param long_url a long url
     * @param a short key
     * @return a short url starts with http://tiny.url/
     */
    String createCustom(String long_url, String short_key) {
        String shortUrl = prefix + short_key;
        if (s2l.containsKey(shortUrl) &&
            !s2l.get(shortUrl).equals(long_url)) {
            return "error";
        }
        
        if (l2s.containsKey(long_url) &&
            isCustom.get(long_url) &&
            !l2s.get(long_url).equals(shortUrl)) {
            return "error";
        }
        
        l2s.put(long_url, shortUrl);
        s2l.put(shortUrl, long_url);
        isCustom.put(long_url, true);
        
        return prefix + short_key;
    }
    
    /**
     * @param long_url a long url
     * @return a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        if (l2s.containsKey(url)) {
            return l2s.get(url);
        }
        
        Pattern reg = Pattern.compile("https?:\\/\\/.*\\.\\w+\\/(.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = reg.matcher(url);
        
        if (!matcher.find()) {
            return "error";
        }
        
        String key = matcher.group(1);
        String shortUrl = prefix + hashLong(key);
        while (s2l.containsKey(shortUrl)) {
            shortUrl = prefix + hashLong(key);
        }
        
        s2l.put(shortUrl, url);
        l2s.put(url, shortUrl);
        isCustom.put(url, false);
        
        return shortUrl;
    }

    /**
     * @param url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String url) {
        if (s2l.containsKey(url)) {
            return s2l.get(url);
        }
        
        return null;
    }
    
    private String hashLong(String url) {
        String ret = "";
        long div, res;
        int digits = 0;
        
        div = count++;
        
        do {
            res = div % 62;
            div = div / 62;
            
            char c = '0';
            if (res >= 0 && res <= 9) {
                c = (char)(res + '0');
            } else if (res >= 10 && res <= 35) {
                c = (char)(res - 10 + 'a');
            } else {
                c = (char)(res - 36 + 'A');
            }
            ret = String.valueOf(c) + ret;
            digits++;
        } while (div > 0 || digits < 6);
        
        return ret;
    }
}
