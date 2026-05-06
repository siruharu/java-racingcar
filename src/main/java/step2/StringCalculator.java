package step2;

public class StringCalculator {
    private static final String DELIMITER = " ";

    public int calculate(String input) {
        InputValidator.validate(input);
        String[] tokens = input.split(DELIMITER);
        return calculateTokens(tokens);
    }

    private int calculateTokens(String[] tokens) {
        int result = new Operand(tokens[0]).value();
        for (int i = 1; i < tokens.length; i += 2) {
            result = applyOperation(result, tokens[i], tokens[i + 1]);
        }
        return result;
    }

    private int applyOperation(int result, String symbol, String token) {
        Operator operator = Operator.from(symbol);
        return operator.calculate(result, new Operand(token).value());
    }
}
