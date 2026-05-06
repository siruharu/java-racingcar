package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class OperatorTest {

    @ParameterizedTest(name = "{0} {1} {2} = {3}")
    @DisplayName("사칙연산이 정상 동작한다")
    @CsvSource(value = {
            "1, +, 2, 3",
            "5, -, 3, 2",
            "4, *, 3, 12",
            "10, /, 2, 5",
            "7, /, 2, 3"
    })
    void calculate(int a, String symbol, int b, int expected) {
        Operator operator = Operator.from(symbol);
        assertThat(operator.calculate(a, b)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "기호: [{0}]")
    @DisplayName("사칙연산 기호가 아니면 IllegalArgumentException")
    @ValueSource(strings = {"%", "&", "^", "1", "abc"})
    void from_invalidSymbol(String symbol) {
        assertThatThrownBy(() -> Operator.from(symbol))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("사칙연산 기호");
    }

    @ParameterizedTest(name = "기호: [{0}]")
    @DisplayName("기호가 null이거나 빈 문자열이면 IllegalArgumentException")
    @NullAndEmptySource
    void from_nullOrEmpty(String symbol) {
        assertThatThrownBy(() -> Operator.from(symbol))
                .isInstanceOf(IllegalArgumentException.class);
    }


}