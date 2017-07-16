/*

Triangle Count


Given an array of integers, how many three numbers can be found in the array, so that we can build an triangle whose three edges length is the three numbers that we find?

Example
Given array S = [3,4,6,7], return 3. They are:

[3,4,6]
[3,6,7]
[4,6,7]
Given array S = [4,4,4,4], return 4. They are:

[4(1),4(2),4(3)]
[4(1),4(2),4(4)]
[4(1),4(3),4(4)]
[4(2),4(3),4(4)]


解：
1. 对数组从小到大排序。
2. 从后向前遍历数组，每次获得数字a=S[i]。
3. 用双指针指向a之前的数子，left=0.right=i-1。得到b=S[left],c=S[right]。
4. 如果b+c>a，则a，b，c可以组成三角形，并且left到right之间的所有数都可以作为b，一共right-left组，故ret+=right-left。之后应当左移c，也就是right--。
5. 如果b+c<=a，由于已经从小到大排序，故left应当右移。
6. 计算直到left==right，重复2，继续遍历。

*/

public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int S[]) {
        if (S == null || S.length ==0) {
            return 0;
        }
        
        int ret = 0;
        Arrays.sort(S);
        
        for (int i = S.length - 1; i >= 0; i--) {
            int a = S[i];
            int left = 0;
            int right = i - 1;
            
            while (left < right) {
                int b = S[left];
                int c = S[right];
                
                if (b + c > a) {
                    ret += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        
        return ret;
    }
}
