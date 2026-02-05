public class Main {
    public static void main(String[] args) {
        System.out.println(ReversePolishNotation.evaluatePostfix("12 13 1 * + 2 + "));
        System.out.println(ReversePolishNotation.infixToPostFix("a + b * c + (d * e + f) * g"));
        }
}