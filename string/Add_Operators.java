/*

Add Operators

Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []


解：
DFS
这道题需要遍历所有可能才能找出所有组合，故可以用DFS。

每一次对于一个数字可以选择加减运算或者乘法运算。
1.对于加减运算，直接计算入target即可，不用在乎之前的数字。
2.对于乘法运算，必须考虑之前的运算数。

以此为出发点，每一次DFS都把当前的运算数和运算符传入下一次运算，而本次只计算上一次传入的运算数prevNum和运算符prevOp。
1.对于加减运算，每一次都把上一次的prevNum根据prevOp计入target，然后把本次的数字和运算符传入下一次计算。
2.对于乘法运算，用prevNum与当前数字相乘，然后继续保留上一次的运算符传入下一次计算。
3.当处理完所有数位时，把prevNum根据prevOp计入target，然后再判断target。

如此相当于乘法运算只有遇到加减运算或者计算结束时才会结算。

对于数字的选取，每次DFS只需要按照长度递增依次选取即可。
需要注意0不能作为大于0的任何数字的起始，故如果传入num第一个数位是0，那么这次DFS只能处理0，跳过后面所有。

*/



public class Solution {
    /*
     * @param num: a string contains only digits 0-9
     * @param target: An integer
     * @return: return all possibilities
     */
    public List<String> addOperators(String num, int target) {
        List<String> ret = new ArrayList<String>();
        
        if (num.length() == 0) {
            return ret;
        }
        
        dfs(ret, num, target, 0, '#', "");
        
        return ret;
    }
    
    private void dfs(List<String> ret, String num, int target, int prevNum, char prevOp, String path) {
        int prevResult = (prevOp == '+') ? prevNum :
                         (prevOp == '-') ? prevNum * -1 : 0; 
        
        if (num.length() == 0) {
            target -= prevResult;
            
            if (target == 0) {
                ret.add(path.substring(1));
            }
            return;
        }
        
        for (int len = 1; len <= num.length(); len++) {
            long cur = Long.valueOf(num.substring(0, len));
            
            if ((len > 1 && num.charAt(0) == '0') || cur > Integer.MAX_VALUE) {
                return;
            }
            
            // +
            dfs(ret, num.substring(len), target - prevResult, (int)cur, '+', path + "+" + cur);
            
            if (prevOp != '#') {
                // -
                dfs(ret, num.substring(len), target - prevResult, (int)cur, '-', path + "-" + cur);
                
                // x
                dfs(ret, num.substring(len), target, prevNum * (int)cur, prevOp, path + "*" + cur);
            }
        }
    }
}
