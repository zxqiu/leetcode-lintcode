/*

Digit Counts

Count the number of k's between 0 and n. k can be 0 - 9.

Example
if n = 12, k = 1 in

[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
we have FIVE 1's (1, 10, 11, 12)

解：
遍历从0到n的所有数字，数出每个数字中k出现的个数即可。


*/

class Solution {

/*
 *
 * param k : As description.
 *
 * param n : As description.
 *
 * return: An integer denote the count of digit k in 1..n
 *
 */

    public int digitCounts(int k, int n) {
        if (k < 0 || n < 0) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i <= n; i++) {
            int tmp = i;
            do {
                if (tmp % 10 == k) {
                    count++;
                }
                tmp = tmp / 10;
            } while (tmp > 0);
        }

        return count;
    }
};



