package step2;

public class InputValidator {
    public static void validate(String input) {
        if (isBlank(input)) {
            throw new IllegalArgumentException("입력값이 null 이거나 빈 공백 문자입니다.");
        }
    }

    private static boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }
}
