/*

Heapify



Given an integer array, heapify it into a min-heap array.
For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].

Have you met this question in a real interview?
Clarification

What is heap?

    Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, "top" return the minimum/maximum element.


What is heapify?

    Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].


What if there is a lot of solutions?

    Return any of them.

Example

Given [3,2,1,4,5], return [1,2,3,4,5] or any legal heap array.
Challenge

O(n) time complexity


解：
参照：
https://www.cs.princeton.edu/~wayne/kleinberg-tardos/pdf/DemoHeapify.pdf

从最底层子树开始，把每个子树的根结点a与两个孩子比较，并与最小的那个交换，相当于一次下沉操作。
完成之后再继续比较a作为根的新的子树，直到满足heap条件，或者没有更多孩子节点。

由于输入数组的后半段的节点都没有孩子，所以只需要处理前半段。

*/

public class Solution {
    /**
     * @param A: Given an integer array
     * @return: void
     */
    public void heapify(int[] A) {
        for (int i = A.length / 2; i >= 0; i--) {
            sink(A, i);
        }
    }
    
    private void sink(int[] A, int idx) {
        while (idx < A.length) {
            int leftIdx, rightIdx, left, right, sinkIdx;
            
            leftIdx = idx * 2 + 1;
            rightIdx = idx * 2 + 2;
            left = (leftIdx < A.length) ? A[leftIdx] : Integer.MAX_VALUE;
            right = (rightIdx < A.length) ? A[rightIdx] : Integer.MAX_VALUE;
            
            if (left < A[idx] && left <= right) {
                sinkIdx = leftIdx;
            } else if (right < A[idx] && right <= left) {
                sinkIdx = rightIdx;
            } else {
                return;
            }
            
            swap(A, idx, sinkIdx);
            /* continue to check the child node */
            idx = sinkIdx;
        }
    }
    
    private void swap(int[] A, int idxA, int idxB) {
        int tmp = A[idxA];
        A[idxA] = A[idxB];
        A[idxB] = tmp;
    }
}
