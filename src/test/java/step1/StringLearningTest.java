package step1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StringLearningTest {

    @Test
    @DisplayName("요구사항 1. split 에 대한 테스트")
    public void splitTest() {
        String splitString = "1,2";
        String[] splitResult = splitString.split(",");

        // 특정 자료구조의 원소 값을 확인할 때. 반복을 돌려서 체크하는 방식도 있지만 assertThat 사용가능
        // assertThat 이후에 사용하는 contains는 중복여부, 순서 관계없이 값만 일치하면 테스트 성공
        // containsOnly: 순서, 중복을 무시하는 대신 원소값과 갯수가 정확히 일치할 때
        // containsExactly: 순서를 포함해서 정확히 일치
        assertThat(splitResult).containsExactly("1", "2");

        String splitString2 = "1";
        String[] splitResult2 = splitString2.split(",");
        assertThat(splitResult2).containsExactly("1");
    }

    @Test
    @DisplayName("요구사항 2. substring에 대한 테스트")
    public void substringTest() {
        String subString = "(1,2)";
        String subStringResult = subString.substring(1, subString.length() - 1);

        // contains 를 쓰지 않은 이유는 결과의 일부만 보장하면 통과이기에 느슨한 테스트가 될 가능성이 있어서.
        // 요구사항이 "(1,2)" 값이 주어졌을때 substring()을 통해 "1,2"를 반환하도록 구현하라 이기에
        // 내가 기대하고있는 결과를 정확히 알고있기에 isEqualTo를 씀
        assertThat(subStringResult).isEqualTo("1,2");
    }

    @Test
    @DisplayName("요구사항 3. charAt() 메소드 활용 특정 위치의 문자를 가져오는 것 에 대한 테스트")
    public void charAtTest() {
        String abc = "abc";

        assertThat(abc.charAt(0)).isEqualTo('a');
        assertThat(abc.charAt(1)).isEqualTo('b');
        assertThat(abc.charAt(2)).isEqualTo('c');

        assertThatThrownBy(() -> {
            abc.charAt(-1);
        }).isInstanceOf(StringIndexOutOfBoundsException.class)
                .hasMessageContaining("Index -1 out of bounds for length 3");

        assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
                .isThrownBy(() -> {
                    abc.charAt(3);
                })
                .withMessageMatching("Index 3 out of bounds for length 3");
    }
}
