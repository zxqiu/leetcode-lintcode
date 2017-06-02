/*

Sort Letters by Case

Given a string which contains only letters. Sort it by lower case first and upper case second.

 Notice

It's NOT necessary to keep the original order of lower-case letters and upper case letters.

Example
For "abAcD", a reasonable answer is "acbAD"

Challenge 
Do it in one-pass and in-place.


解：
这道题使用quick sort或者quick select的方法来解。
把所有小写字母挪动到数组前端。
使用一个store指针指向数组头，然后用指针i遍历数组。
i每遇到一个小写字母就跟store指向的字母交换，并把store向后移动一次，直到遍历完毕。

*/

public class Solution {
    /** 
 *      *@param chars: The letter array you should sort by Case
 *           *@return: void
 *                */
    public void sortLetters(char[] chars) {
        int store = 0;
        
        for (int i = 0; i < chars.length; i++) {
            if (isLowerCase(chars[i])) {
                char tmp = chars[i];
                chars[i] = chars[store];
                chars[store++] = tmp;
            }
        }
    }
    
    private boolean isLowerCase(char c) {
        return (c >= 'a' && c <= 'z');
    }
}

