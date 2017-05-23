/*

Min Stack

Implement a stack with min() function, which will return the smallest number in the stack.

It should support push, pop and min operation all in O(1) cost.


 Notice

min operation will never be called if there is no number in the stack.


解：
分析需要哪些数据：
1. 需要使用一个stack保存原始数据的存入顺序；
2. 需要使用一个stack保存每一个数据插入时的当前最小值。

这道题容易走入需要保存数据排序信息的误区。
如果保存排序信息，那么最快的方法是使用一个heap，插入和取出的时间复杂度都将增加到O(log(n))。


当每个数据插入时，比较这个数和之前的最小值，即最小值stack顶端数据的大小，将小的那个再次插入最小值stack。
比如输入数据为：
8，6，3，7，1，2
那么最小值stack为：
8，6，3，3，1，1


可以分析每个值按顺序被取出后最小值序列依然保持着正确的当前最小值。

*/


public class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minSt;
    
    public MinStack() {
        stack = new Stack<Integer>();
        minSt = new Stack<Integer>();
    }

    public void push(int number) {
        stack.push(number);
        
        if (minSt.size() == 0 || number < minSt.peek()) {
            minSt.push(number);
        } else {
            minSt.push(minSt.peek());
        }
    }

    public int pop() {
        minSt.pop();
        return stack.pop();
    }

    public int min() {
        return minSt.peek();
    }
}
