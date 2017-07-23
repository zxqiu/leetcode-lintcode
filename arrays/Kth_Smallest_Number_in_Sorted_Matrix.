/*

Kth Smallest Number in Sorted Matrix 


Find the kth smallest number in at row and column sorted matrix.

Example
Given k = 4 and a matrix:

[
  [1 ,5 ,7],
  [3 ,7 ,8],
  [4 ,8 ,9],
]
return 5

Challenge 
Solve it in O(k log n) time where n is the bigger one between row size and column size.


解：
方法一：
Min Heap
用min heap遍历整个矩阵。从左上角的点开始，每访问一个点，就把其下方的点放进heap。
如果是第一行，还需要把右边的点放进heap。
如果每次都把下面和右边的点放进heap，会访问到重复的点。

每访问过一个点，就对k减一，直到为0。

时间复杂度O(n*mlog(k))，空间复杂度O(n*m)，n和m分别是矩阵的长和宽。

*/

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */
    
    private class Node {
        int x, y, val;
        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        
        Node walker = new Node(0, 0, matrix[0][0]);
        Queue<Node> q = new PriorityQueue<Node>(1, new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.val - b.val;
            }
        });
        
        q.offer(walker);
        
        while (k > 0 && !q.isEmpty()) {
            Node right, down;
            int x, y;
            
            walker = q.poll();
            x = walker.x;
            y = walker.y;
            
            if (x + 1 < matrix.length) {
                down = new Node(x + 1, y, matrix[x + 1][y]);
                q.offer(down);
            }
            if (x == 0 && y + 1 < matrix[0].length) {
                right = new Node(x, y + 1, matrix[x][y + 1]);
                q.offer(right);
            }
            
            k--;
        }
        
        return (k > 0) ? -1 : walker.val;
    }
}

/*

方法二：
Binary Search
两重二分法。
先按照矩阵的最大值和最小值进行二分搜索。
对于每一个mid的值，由于每一行都已排序，可以用二分法找出其在每一行中的排位，累加得到其在整个矩阵中的排位。
根据这个排位来调整min和max两个边界。

第一重二分时间复杂度O(log(x))，x为最大值和最小值的差。
第二重二分时间复杂度O(log(n))，每轮进行了m次，故O(mlog(n))。
总体为O(log(x)*mlog(n))。
空间复杂度O(1)。

*/

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int min = matrix[0][0];
        int max = matrix[m - 1][n - 1];
        int cnt = 0;
        
        while (min < max) {
            int mid = (min + max) / 2;
            cnt = 0;
            
            for (int i = 0; i < m; i++) {
                cnt += bSearch(matrix[i], mid);
            }
            
            if (cnt < k) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        
        return min;
    }
    
    public int bSearch(int[] row, int val) {
        int left = 0;
        int right = row.length;
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (row[mid] <= val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
}


/*

方法三：
Binary Search
方法二中可以利用每一列也已经排序对第二重二分进行简化。
每次从左下角开始，对于每个坐标(i,j)：
  1.如果mid值大于等于当前值，说明当前一列全都应当计入小于mid值的值数量。cnt += i + 1。同时j++，开始计算下一列。
  2.如果mid值小于当前值，则i--。
  
时间复杂度O((n + m)log(x))，n和m分别为矩阵的长和宽。

*/


public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */
    
    private class Node {
        int x, y, val;
        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        
        Node walker = new Node(0, 0, matrix[0][0]);
        Queue<Node> q = new PriorityQueue<Node>(1, new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.val - b.val;
            }
        });
        
        q.offer(walker);
        
        while (k > 0 && !q.isEmpty()) {
            Node right, down;
            int x, y;
            
            walker = q.poll();
            x = walker.x;
            y = walker.y;
            
            if (x + 1 < matrix.length) {
                down = new Node(x + 1, y, matrix[x + 1][y]);
                q.offer(down);
            }
            if (x == 0 && y + 1 < matrix[0].length) {
                right = new Node(x, y + 1, matrix[x][y + 1]);
                q.offer(right);
            }
            
            k--;
        }
        
        return (k > 0) ? -1 : walker.val;
    }
}
