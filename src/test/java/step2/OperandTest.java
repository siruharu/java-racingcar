package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class OperandTest {
    @ParameterizedTest(name = "[{0}] -> {1}")
    @DisplayName("문자열을 정수로 변환한다")
    @CsvSource(value = {
            "'1', 1",
            "'42', 42",
            "'0', 0",
            "'-5', -5"
    })
    void create(String token, int expected) {
        Operand operand = new Operand(token);
        assertThat(operand.value()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "입력값: [{0}]")
    @DisplayName("숫자가 아닌 문자열은 IllegalArgumentException")
    @ValueSource(strings = {"a", "1.5", "+", " ", "1a", "abc"})
    void create_invalidToken(String token) {
        assertThatThrownBy(() -> new Operand(token))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자가 아닙니다");
    }

    @Test
    @DisplayName("같은 값으로 생성한 Operand는 같은 value를 갖는다")
    void value() {
        assertThat(new Operand("10").value()).isEqualTo(new Operand("10").value());
    }

}