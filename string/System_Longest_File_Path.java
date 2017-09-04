/*

System Longest File Path

Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string

"dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

 Notice

The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.
Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.


Example
Give input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" return


解：
Stack
用一个stack保存每一级目录的最长长度。
由于所有子目录紧挨着父目录，所以每一级只要保存最近的目录即可。
1.遇到下一级目录时，直接入栈。
2.遇到上一级目录或者同级目录时，先把stack中等于或者低于当前目录级别的目录全部出栈，保证栈顶是当前目录的父目录，然后入栈。
3.遇到文件时，用文件长度加上栈顶的目录长度，与保存的最大值比较并更新。

*/


public class Solution {
    /*
     * @param input: an abstract file system
     * @return: return the length of the longest absolute path to file
     */
    public int lengthLongestPath(String input) {
        String[] in = input.split("\n");
        Stack<Integer> st = new Stack<Integer>();
        int max = 0;
        
        for (String dir : in) {
            String[] f = dir.split("\t");
            String name = f[f.length - 1];
            
            while (!st.isEmpty() && f.length <= st.size()) {
                st.pop();
            }
                
            if (!st.isEmpty()) {
                st.push(name.length() + st.peek() + 1);
            } else {
                st.push(name.length());
            }
            
            if (name.indexOf(".") >= 0) {
                max = Math.max(max, st.peek());
            }
        }
        
        
        return max;
    }
}
