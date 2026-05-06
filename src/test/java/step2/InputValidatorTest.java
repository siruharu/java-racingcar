package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @ParameterizedTest(name = "입력값: [{0}]")
    @DisplayName("입력값이 null이거나 빈 공백 문자열이면 IllegalArgumentException")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    void validate_blankInput(String input) {
        assertThatThrownBy(() -> InputValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값");
    }

    @Test
    @DisplayName("정상 입력값은 예외를 던지지 않는다")
    void validate_validInput() {
        assertThatCode(() -> InputValidator.validate("1 + 2"))
                .doesNotThrowAnyException();
    }

}