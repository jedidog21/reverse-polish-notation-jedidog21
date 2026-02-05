import java.util.*;

public class ReversePolishNotation {
    /**
     * evaluatePostfix takes evaluates an equation in postfix notation
     * @param input is a math equation as a string in Reverse Polish Notation (postfix notation) with a space in between each number and operator
     * @return the answer to "input" as an int
     */
    public static int evaluatePostfix(String input){
        Stack numStack = new Stack();
        int total = 0;
        int num1 = 0;
        int num2 = 0;
        String sym;
        String[] pf = input.split(" ");
        for (String i : pf) {
            total = 0;
            try {
                numStack.push(Integer.parseInt(i) + "");
            } catch (NumberFormatException n) {
                if (!(i.equals(" "))) {
                    num2 = Integer.parseInt(numStack.pop());
                    num1 = Integer.parseInt(numStack.pop());
                    sym = i;
                    switch (sym) {
                        case "+" -> total = num1 + num2;
                        case "-" -> total = num1 - num2;
                        case "*" -> total = num1 * num2;
                        case "/" -> total = num1 / num2;
                    }
                    numStack.push(total + "");
                }
            }
        }
        total = Integer.parseInt(numStack.pop());
        return total;
    }

    /**
     * infixToPostFix take an equation in infix/normal notation and turns it into postfix notation
     * @param input is a normal math equation as a string with a space between each number and operator (not needed for parentheses)
     * @return an equation equivalent to "input" but in postfix notation
     */
    public static String infixToPostFix(String input){
        Stack infix = new Stack();
        for (int q = 0; q < input.length(); q++){
            if (input.charAt(q) == '('){
                input = input.substring(0,q+1) + " " + input.substring(q+1);
                q++;
            }
            else if (input.charAt(q) == ')'){
                input = input.substring(0,q) + " " + input.substring(q);
                q++;
            }
        }
        System.out.println(input);
        String[] prec = {"-","+","/","*","(",")"};
        String[] inf = input.split(" ");
        String output = "";
        for (String i : inf){
            if (i.equals("(")){
                infix.push(i);
            }
            else if (!(Arrays.asList(prec).contains(i))) {

                output += i + " ";
            }
            else if (i.equals(")")){
                for (int j = 0; j < infix.size(); j++){
                    if (infix.peek().equals("(")) {
                        infix.pop();
                        break;
                    }
                    output += infix.pop() + " ";
                }
            }

            else if (!infix.isEmpty() && Arrays.asList(prec).indexOf(i) >= Arrays.asList(prec).indexOf(infix.peek())){
                infix.push(i);
            }
            else{
                while (!(infix.isEmpty()) && !(infix.peek().equals("("))){
                    output += infix.pop() + " ";
                }
                infix.push(i);
            }
        }
        while (!(infix.isEmpty())){
            output += infix.pop() + " ";
        }
        return output;
    }
}
