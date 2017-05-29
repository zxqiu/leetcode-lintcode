/*

Implement Queue by Two Stacks

As the title described, you should only use two stacks to implement a queue's actions.

The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

Both pop and top methods should return the value of first element.

Example
push(1)
pop()     // return 1
push(2)
push(3)
top()     // return 2
pop()     // return 2

Challenge 
implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE.


解：
stack in负责存，stack out负责取。
push直接存入in。
pop和top的时候如果out不为空，直接对out进行pop和peek。
如果out为空，则将in中的元素pop出来push进out中，这样最早push进入in的元素将在out的顶端。

*/


public class MyQueue {
    private Stack<Integer> in;
    private Stack<Integer> out;

    public MyQueue() {
       in = new Stack<Integer>();
       out = new Stack<Integer>();
    }
    
    public void push(int element) {
        in.push(element);
    }

    public int pop() {
        if (out.isEmpty()) {
            pour();
        }
        
        return out.pop();
    }

    public int top() {
        if (out.isEmpty()) {
            pour();
        }
        
        return out.peek();
    }
    
    private void pour() {
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
    }
}
