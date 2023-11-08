import java.util.Stack;

public class CalcolatriceRPN {
    private String infissa;
    private String postfissa;
    public CalcolatriceRPN() {}

    public String getInfissa() {
        return infissa;
    }

    public String getPostfissa() {
        return postfissa;
    }

    private boolean isNumber(char c) {
        if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7'
                || c == '8' || c == '9') {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            return true;
        }
        else {
            return false;
        }
    }

    private int getPriority(char c) {
        if (c == '+' || c == '-') {
            return 1;
        }
        else if (c == '*' || c == '/') {
            return 2;
        }
        else {
            return 0;
        }
    }

    private String outputToString(Stack<Character> output) {
        StringBuilder result = new StringBuilder();
        while (!output.isEmpty()) {
            Character element = output.pop();
            result.insert(0, element);
        }
        return result.toString();
    }

    private void trasformaInPostfissa(String input) {
        infissa = input;
        Stack<Character> operatori = new Stack<>();
        Stack<Character> risultato = new Stack<>();

        for (int i = 0; i < infissa.length(); i++) {
            char c = infissa.charAt(i);
            if (isNumber(c)) {
                risultato.push(c);
            } else if (c == '(') {
                operatori.push(c);
            } else if (c == ')') {
                while (operatori.peek() != '(') {
                    risultato.push(operatori.pop());
                }
                operatori.pop();
            } else if (isOperator(c)) {
                while (!operatori.isEmpty() && getPriority(c) <= getPriority(operatori.peek())) {
                    risultato.push(operatori.pop());
                }
                operatori.push(c);
            }
        }

        while (!operatori.isEmpty()) {
            risultato.push(operatori.pop());
        }

        postfissa = outputToString(risultato);
    }
    public String calcola(boolean isPostfissa, String input) {
        if (!isPostfissa) {
            trasformaInPostfissa(input);
        }
        Stack<Double> risultato = new Stack<>();
        for (int i = 0; i < postfissa.length(); i++) {
            char c = postfissa.charAt(i);
            if (isNumber(c)) {
                risultato.push(Double.parseDouble(String.valueOf(c)));
            } else if (isOperator(c)) {
                double a = risultato.pop();
                double b = risultato.pop();
                switch (c) {
                    case '+':
                        risultato.push(b + a);
                        break;
                    case '-':
                        risultato.push(b - a);
                        break;
                    case '*':
                        risultato.push(b * a);
                        break;
                    case '/':
                        risultato.push(b / a);
                        break;
                }
            }
        }
        return String.valueOf(risultato.pop());
    }
}
