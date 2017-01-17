class QuickSort {
    /*
    首先选定一个基准元素，这里我们元素 5 为基准元素（基准元素可以任意选择）：
              pivot
                ↓
    3   7   8   5   2   1   9   5   4

    将基准元素与数组中最后一个元素交换位置，如果选择最后一个元素为基准元素可以省略该步：
                                  pivot
                                    ↓
    3   7   8   4   2   1   9   5   5

    从左到右（除了最后的基准元素），循环移动小于基准元素 5 的所有元素到数组开头，留下大于等于基准元素的元素接在后面。在这个过程它也为基准元素找寻最后摆放的位置。循环流程如下：

    循环 i == 0 时，storeIndex == 0，找到一个小于基准元素的元素 3，那么将其与 storeIndex 所在位置的元素交换位置，这里是 3 自身，交换后将 storeIndex 自增 1，storeIndex == 1：
                                    pivot
                                      ↓
      3   7   8   4   2   1   9   5   5
      ↑
    storeIndex

    循环 i == 3 时，storeIndex == 1，找到一个小于基准元素的元素 4：
         ┌───────┐                 pivot
         ↓       ↓                   ↓
     3   7   8   4   2   1   9   5   5
         ↑       ↑
    storeIndex   i

    交换位置后，storeIndex 自增 1，storeIndex == 2：
                                  pivot
                                    ↓
    3   4   8   7   2   1   9   5   5
            ↑           
       storeIndex

    循环 i == 4 时，storeIndex == 2，找到一个小于基准元素的元素 2：
            ┌───────┐             pivot
            ↓       ↓               ↓
    3   4   8   7   2   1   9   5   5
            ↑       ↑
       storeIndex   i

    交换位置后，storeIndex 自增 1，storeIndex == 3：
                                  pivot
                                    ↓
    3   4   2   7   8   1   9   5   5
                ↑           
           storeIndex

    循环 i == 5 时，storeIndex == 3，找到一个小于基准元素的元素 1：
                ┌───────┐         pivot
                ↓       ↓           ↓
    3   4   2   7   8   1   9   5   5
                ↑       ↑
           storeIndex   i

    交换后位置后，storeIndex 自增 1，storeIndex == 4：
                                  pivot
                                    ↓
    3   4   2   1   8   7   9   5   5
                    ↑           
               storeIndex

    循环 i == 7 时，storeIndex == 4，找到一个小于等于基准元素的元素 5：
                    ┌───────────┐ pivot
                    ↓           ↓   ↓
    3   4   2   1   8   7   9   5   5
                    ↑           ↑
               storeIndex       i

    交换后位置后，storeIndex 自增 1，storeIndex == 5：
                                  pivot
                                    ↓
    3   4   2   1   5   7   9   8   5
                        ↑           
                   storeIndex

    循环结束后交换基准元素和 storeIndex 位置的元素的位置：
                      pivot
                        ↓
    3   4   2   1   5   5   9   8   7
                        ↑           
                   storeIndex
    */


    public void quickSort(int[] nums) {
        return qsortHelper(nums, 0, nums.length - 1);
    }

    public void qsortHelper(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int store, pivot;
        pivot = nums[end];
        store = start;

        for (int i = start; i <= end; i++) {
            if (nums[i] <= pivot) {
                swap(nums, store++, i);
            }
        }

        // Store points to the index of the next element of pivot.
        // Sort the list before and after pivot.
        qsortHelper(nums, start, store - 2);
        qsortHelper(nums, store, end);
    }
}
