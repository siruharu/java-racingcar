package step2;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Operator {
    PLUS("+", Integer::sum),
    MINUS("-", (a, b) -> a - b),
    MULTIPLY("*", (a, b) -> a * b),
    DIVIDE("/", (a, b) -> a / b);

    private final String symbol;
    // 연산기호 기준으로 a, b가 나뉘고 해당하는 값을 연산하는 것이라 BiFunction 사용을 하는 형태 a, b -> result
    private final BiFunction<Integer, Integer, Integer> expression;


    Operator(String symbol, BiFunction<Integer, Integer, Integer> expression) {
        this.symbol = symbol;
        this.expression = expression;
    }

    public int calculate(int a, int b) {
        return expression.apply(a, b);
    }

    public static Operator from(String symbol) {
        return Arrays.stream(values())
                .filter(op -> op.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("사칙연산 기호가 아닙니다."));
    }
}
