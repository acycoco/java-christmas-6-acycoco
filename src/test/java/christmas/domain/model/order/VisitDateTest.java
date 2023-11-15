package christmas.domain.model.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @DisplayName("달력에 별이 찍혀있으면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void hasStar(int starDay) {
        //given
        VisitDate visitDate = VisitDate.from(starDay);

        //when
        boolean hasStar = visitDate.hasStar();

        //then
        assertThat(hasStar)
                .isEqualTo(true);
    }
}