/*

Url Parser

Parse a html page, extract the Urls in it.

Hint: use regex to parse html.

 Example
 
Given the following html page:

<html>
  <body>
    <div>
      <a href="http://www.google.com" class="text-lg">Google</a>
      <a href="http://www.facebook.com" style="display:none">Facebook</a>
    </div>
    <div>
      <a href="https://www.linkedin.com">Linkedin</a>
      <a href = "http://github.io">LintCode</a>
    </div>
  </body>
</html>
You should return the Urls in it:

[
  "http://www.google.com",
  "http://www.facebook.com",
  "https://www.linkedin.com",
  "http://github.io"
]


解：
这道题的关键是如何组织正则表达式。
详情可以参考正则表达式教程。

*/



import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlParser {
    /**
     * @param content source code
     * @return a list of links
     */
    public List<String> parseUrls(String content) {
        List<String> ret = new ArrayList<String>();
        if (content == null) {
            return ret;
        }
        
        Pattern reg = Pattern.compile("\\s*(?i)href\\s*=\\s*[\'\"]?([^\'\" >\\s]*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = reg.matcher(content);
        String url = null;
        
        while (matcher.find()) {
            url = matcher.group(1);
            if (url.length() == 0 || url.startsWith("#")) {
                continue;
            }
            ret.add(matcher.group(1));
        }
        
        return ret;
    }
}
