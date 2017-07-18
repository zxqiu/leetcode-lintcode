/*

Permutation Sequence


Given n and k, return the k-th permutation sequence.

 Notice

n will be between 1 and 9 inclusive.

Example
For n = 3, all permutations are listed as follows:

"123"
"132"
"213"
"231"
"312"
"321"
If k = 4, the fourth permutation is "231"

Challenge 
O(n*k) in time complexity is easy, can you do it in O(n^2) or less?

解：
分析上面的例子。
第一个数字后面的数有2!种组合，也就是说第一个数字选定后一共有2!个以该数字开头的组合，故第一个数字应当是{1,2,3}中的第(k-1)/2!个(从第0个到第3个)，k-1如果小于0则应当改为0，故：
n = max(0,k-1)/2! = 1
k=3故第一个数字为2。
第一个数字选定之后，k应当修改为下一个数字是剩余所有数字中的第几个，也就是说k中应当减去2之前所有数字作为第一个数时的所有排列数量：
k -= n*2! = 1 (由于n从0开始，故与当前选定数字之前有几个数相等)

同理第二个数字应当为{1,3}中的第n = max(0,k-1)/1!个，此时k=1，故n=0，选中1。
更新k:
k -= n*1! = 1

同理第三个数字应当选中{3}中的第n = max(0,k-1)/1!个，此时k=1，故n=0，选中3。

*/

class Solution {
    /**
      * @param n: n
      * @param k: the kth permutation
      * @return: return the k-th permutation
      */
    public String getPermutation(int n, int k) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        ArrayList<Integer> nums = new ArrayList<Integer>();
        String ret = "";

        factors.add(1); // 0! = 1
        for (int i = 1; i <= n; i++) {
            nums.add(i);
            factors.add(factors.get(i - 1) * i);
        }
        
        for (int i = n; i >= 1; i--) {
            int idx = Math.max(0, k - 1) / factors.get(i - 1);
            k -= idx * factors.get(i - 1);
            ret += String.valueOf(nums.get(idx));
            nums.remove(idx);
        }
        
        return ret;
    }
}
