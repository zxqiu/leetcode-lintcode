/*

Gray Code


The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, find the sequence of gray code. A gray code sequence must begin with 0 and with cover all 2n integers.

 Notice

For a given n, a gray code sequence is not uniquely defined.

[0,2,3,1] is also a valid gray code sequence according to the above definition.

Example
Given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Challenge 
O(2n) time.

解：
方法一：
数字i的格雷码为：(i>>1)^i
n bit的最大数字max为pow(2, n)-1。
计算从0到max的格雷码即可。

*/

public class Solution {
    /**
     * @param n a number
     * @return Gray code
     */
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        int max = (int)Math.pow(2, n);
        
        for (int i = 0; i < max; i++) {
            ret.add((i >> 1) ^ i);
        }
        
        return ret;
    }
}


/*

方法二:
递归计算n-1 bit的所有格雷码，获取结果数组。
然后从最后一个开始，对每一个加上(1<<(n-1))，并加入之前获得的数组。
这样可以保证旧的和新的格雷码全部连续。

*/

public class Solution {
    /**
     * @param n a number
     * @return Gray code
     */
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        int len;
        
        if (n == 0) {
            ret.add(0);
            return ret;
        } else if (n == 1) {
            ret.add(0);
            ret.add(1);
            return ret;
        }
        
        ret = grayCode(n - 1);
        len = ret.size();
        
        for (int i = len - 1; i >= 0; i--) {
            ret.add(ret.get(i) + (1 << (n - 1)));
        }
        
        return ret;
    }
}


/*

方法三：
利用二叉树求解。
对于n=3，有以下二叉树：

      Root
   0       1
 0   1   1   0
0 1 1 0 0 1 1 0

DFS遍历上面树，每一条从根到叶子节点的路径便是一个格雷码。

观察可知，每一个左子树的左子树都是0，右子树都是1。
相反每一个左子树的左子树都是1，右子树都是0。
按照如上规则DFS遍历即可。

*/

public class Solution {
    /**
     * @param n a number
     * @return Gray code
     */
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (n == 0) {
            ret.add(0);
            return ret;
        }
        
        helper(ret, "", true, "0", n);
        helper(ret, "", false, "1", n);
        
        return ret;
    }
    
    private void helper(ArrayList<Integer> ret, String path, boolean isLeft, String val, int n) {
        path += val;
        if (path.length() == n) {
            ret.add(stringBinaryToInt(path));
            return;
        }
        
        String leftChildVal = isLeft ? "0" : "1";
        String rightChildVal = isLeft ? "1" : "0";
        
        helper(ret, path, true, leftChildVal, n);
        helper(ret, path, false, rightChildVal, n);
    }
    
    private int stringBinaryToInt(String s) {
        int ret = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                ret += Math.pow(2, s.length() - i - 1);
            }
        }
        
        return ret;
    }
}
