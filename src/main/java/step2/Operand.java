package step2;

public class Operand {
    private final int value;

    public Operand(String token) {
        this.value = parse(token);
    }

    private int parse(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다: " + token, e);
        }
    }

    public int value() {
        return value;
    }
}
