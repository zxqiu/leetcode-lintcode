/*

Frog Jump

A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

 Notice

The number of stones is ≥ 2 and is < 1100.
Each stone's position will be a non-negative integer < 2^31.
The first stone's position is always 0.
Have you met this question in a real interview? Yes
Example
Given stones = [0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.

Given stones = `[0,1,2,3,4,8,9,11]`

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.



解：
HashMap
这道题对空间复杂度没有严格限制，所以其实有很多可能性。

由于每一个石头有可能从之前的多个石头上跳过来，故每个石头上可以起跳的距离有多种。
用一个HashMap加HashSet来保存每个石头上可以跳跃的距离。
比如第一个石头只能跳跃1，那么就保存{0, {1}}。
第二个石头就可以跳跃0，1，2，那么保存{1, {0, 1, 2}}。

每到达一个石头，就计算这个石头可以到达的所有石头，并对其中每一个加入下一跳的可能距离。
遍历完之后，如果最后一个石头存在于HashMap之中，证明其可达。

*/

public class Solution {
    /*
     * @param stones: a list of stones' positions in sorted ascending order
     * @return: true if the frog is able to cross the river or false
     */
    
    public boolean canCross(int[] stones) {
        HashMap<Integer, HashSet<Integer>> dp = new HashMap<Integer, HashSet<Integer>>();
        
        dp.put(0, new HashSet<Integer>());
        dp.get(0).add(1);
        
        for (int i = 0; i < stones.length; i++) {
            if (!dp.containsKey(stones[i])) {
                continue;
            }
            
            for (Integer j : dp.get(stones[i])) {
                int next = stones[i] + j;
                
                if (next == stones[i]) {
                    continue;
                }
                
                if (!dp.containsKey(next)) {
                    dp.put(next, new HashSet<Integer>());
                }
                
                dp.get(next).add(j - 1);
                dp.get(next).add(j);
                dp.get(next).add(j + 1);
            }
        }
        
        return dp.containsKey(stones[stones.length - 1]);
    }
}
