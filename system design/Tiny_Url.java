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
