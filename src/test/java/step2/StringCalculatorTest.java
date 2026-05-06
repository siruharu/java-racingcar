package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringCalculatorTest {
    private final StringCalculator calculator = new StringCalculator();

    @ParameterizedTest(name = "{0} = {1}")
    @DisplayName("사칙연산을 입력 순서대로 계산한다")
    @CsvSource(value = {
            "'1 + 2', 3",
            "'5 - 3', 2",
            "'4 * 3', 12",
            "'10 / 2', 5",
            "'2 + 3 * 4 / 2', 10",
            "'1 + 2 / 3 * 4', 4",
            "'1 + 2 * 3 / 4', 2"
    })
    void calculate(String expression, int expected) {
        assertThat(calculator.calculate(expression)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "입력값: [{0}]")
    @DisplayName("입력값이 null이거나 빈 공백이면 IllegalArgumentException")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    void calculate_blankInput(String input) {
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "입력값: [{0}]")
    @DisplayName("사칙연산 기호가 아니면 IllegalArgumentException")
    @ValueSource(strings = {"1 % 2", "3 & 4", "5 ^ 2"})
    void calculate_invalidOperator(String input) {
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("사칙연산 기호");
    }

    @ParameterizedTest(name = "입력값: [{0}]")
    @DisplayName("피연산자가 숫자가 아니면 IllegalArgumentException")
    @ValueSource(strings = {"a + 2", "1 + b", "1 + 2 * c"})
    void calculate_invalidOperand(String input) {
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("단일 숫자 입력 시 그 숫자를 반환한다")
    void calculate_singleNumber() {
        assertThat(calculator.calculate("42")).isEqualTo(42);
    }

}