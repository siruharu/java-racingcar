package step1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SetLearningTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @Test
    @DisplayName("요구사항 1. size() 메소드를 활용해 Set의 크기를 확인하는 테스트")
    public void sizeTest() {
        int size = numbers.size();
        assertThat(size).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("요구사항 2. Set의 contains() 활용해 1,2,3의 값이 존재하는지 확인 테스트")
    public void containsTest(int number) {
        // 보통 컬렉션들의 값들을 for 문이나 반복문등으로 꺼내서 isTrue를 했었는데 처음 써봄;;
        assertThat(numbers.contains(number)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:true", "3:true", "4:false", "5:false"}, delimiter = ':')
    public void csvSourceTest(int number, boolean expected) {
        // 어노테이션 명에서 알 수 있듯이 기본형은 , 기준이나 편한형태의 delimiter로 구분도 가능하다.
        // 또한 csv파일자체를 읽혀서 처리할 수도있다.
        assertThat(numbers.contains(number)).isEqualTo(expected);
    }
}
