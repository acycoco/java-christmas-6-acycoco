package christmas.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputProcessorTest {

    private InputProcessor inputProcessor;

    @BeforeEach
    void setUp() {
        inputProcessor = new InputProcessor();
    }

    @DisplayName("날짜를 숫자로 변환하는 데 성공한다.")
    @Test
    void toDateSuccess() {
        Integer date = inputProcessor.toDate(" 13 ");

        assertThat(date)
                .isEqualTo(13);
    }

    @DisplayName("문자가 들어있어서 날짜를 숫자로 변환하는 데 실패한다.")
    @Test
    void toDateFail() {
        assertThatThrownBy(() -> inputProcessor.toDate(",13 "))
                .isInstanceOf(IllegalArgumentException.class);
    }
}