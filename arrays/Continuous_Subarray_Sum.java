/*

Continuous Subarray Sum

Given an integer array, find a continuous subarray where the sum of numbers is the biggest. Your code should return the index of the first number and the index of the last number. (If their are duplicate answer, return anyone)

Example
Give [-3, 1, 3, -3, 4], return [1,4].

解：
start从0开始，并从start开始累加，如果sum小于0，则sum清零，并且start改成i+1。
保存sum最大时的start和i。

*/

public class Solution {
    /**
     * @param A an integer array
     * @return  A list of integers includes the index of the first number and the index of the last number
     */
    public ArrayList<Integer> continuousSubarraySum(int[] A) {
        ArrayList<Integer> ret = null;
        int sum = 0;
        int start = 0;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            
            if (sum >= max) {
                ret = new ArrayList<Integer>();
                ret.add(start);
                ret.add(i);
                max = sum;
            }
            
            if (sum < 0) {
                start = i + 1;
                sum = 0;
            }
        }
        
        return ret;
    }
}
