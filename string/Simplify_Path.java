/*

simplify path

Given an absolute path for a file (Unix-style), simplify it.

解：
先把输入路径按照“/”来切割成字符串数组，然后遍历该数组。
当数组中的当前字符串不为“..”也不为“.”或者“”时，入栈该字符串。
当前字符串为“..”时，若栈不为空，则出栈栈顶元素。

如此直到遍历完，然后重组字符串。

*/




public class Solution {
    /**
     * @param path the original path
     * @return the simplified path
     */
    public String simplifyPath(String path) {
        if (path == null) {
            return path;
        }
        
        StringBuilder sb = new StringBuilder();
        Stack<String> st = new Stack<String>();
        String[] words = path.split("/");
        
        
        for (String word : words) {
            if (word.equals("..")) {
                // "/../"
                if (!st.isEmpty()) {
                    st.pop();
                }
            } else if (!word.equals(".") && !word.equals("")) {
                st.push(word);
            }
        }
        
        // rebuild
        while (!st.isEmpty()) {
            sb.insert(0, st.pop());
            sb.insert(0, '/');
        }
        
        return (sb.length() == 0) ? "/" : sb.toString();
    }
}
