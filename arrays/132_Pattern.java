/*

132 Pattern

Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

n will be less than 20,000.

Example
Given nums = [1, 2, 3, 4]
return False // There is no 132 pattern in the sequence.

Given nums = [3, 1, 4, 2]
return True // There is a 132 pattern in the sequence: [1, 4, 2].


解：
这道题由于限定了n最大值，所以可以从头查找每一个递增区间，使用一个HashSet保存区间中的数字。
如果后面遇到了一个在HashSet中的数字，则说明存在132 pattern。
这种方法不能解决不限定n最大值的情况，所以并不推荐。


这里参照九章算法答案给出解释。

1.用min[i]保存从0到i-1中的最小值。
2.从后向前遍历，用stack保存递减subsequence。
  1)如果nums[i]大于min[i]，说明存在两个数满足ai<ak。
    1>将stack中所有小于nums[i]的数全部出栈，并保存其中最大的那个max。
    如果max大于min[i]，说明存在min[i]<max<nums[i]，满足ai<ak<aj。
    2>如果没有数字出栈，说明nums[i]小于stack中的所有数字。
      由于nums[i]>min[i]，故stack中一定存在大于min[i]的数字。
      直接比较之前保存的max和min[i]，如果max小于min[i]，则说明存在ai<ak<aj。
    3>将nums[i]入栈。
      由于之前已经将小于nums[i]的数出栈，故入栈后stack中的值依然递减。


这里对原来的算法进行了稍微修改，当nums[i]小于等于min[i]时不必将nums[i]入栈。
这时由于min数组一定是递减的，nums[i]小于min[i]那就一定小于min[0~i-1]，不可能满足ak>ai。
这样的数字可以直接丢弃。

*/


    /**
     * 本参考程序来自九章算法，由 @九章算法 提供。版权所有，转发请注明出处。
     * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
     * - 现有的面试培训课程包括：九章算法班，系统设计班，算法强化班，Java入门与基础算法班，Android 项目实战班，Big Data 项目实战班，
     * - 更多详情请见官方网站：http://www.jiuzhang.com/?source=code
     */ 

public class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        Stack<Integer> st = new Stack<Integer>();
        int[] min = new int[nums.length];
        int max = Integer.MIN_VALUE;
        min[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i - 1]);
        }
        
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > min[i]) {
                while (!st.isEmpty() && nums[i] > st.peek()) {
                    max = st.pop();
                }
                
                if (max > min[i]) {
                    return true;
                }
            }
            st.push(nums[i]);
        }
        
        return false;
    }
}
