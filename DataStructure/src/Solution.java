import java.util.Stack;

class Solution {
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i <s.length() ; i++) {

            char x = s.charAt(i);
            if(x =='{' || x=='['|| x=='(' ){
                stack.push(x);
            }else {
                if (stack.isEmpty()){
                    return false;
                }
                if(x == '}'&& stack.pop()!='{'){
                    return false;
                }
                if(x == ']'&& stack.pop()!='['){
                    return false;
                }
                if(x == ')'&& stack.pop()!='('){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}