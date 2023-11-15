package christmas.dto;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.model.eventcalculator.BenefitDetails;
import christmas.domain.model.event.EventType;
import christmas.domain.model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


class BenefitDetailsDtoTest {

    @DisplayName("혜택내역이 있을 때 혜택내역 dto객체 생성에 성공한다.")
    @Test
    void createBenefitDetailsDtoIfExists() {
        //given
        Map<EventType, Money> benefitsData = new HashMap<>();
        benefitsData.put(EventType.CHRISTMAS_DDAY_DISCOUNT, Money.from(1200));
        benefitsData.put(EventType.WEEKDAY_DISCOUNT, Money.from(4046));
        benefitsData.put(EventType.SPECIAL_DISCOUNT, Money.from(1000));
        benefitsData.put(EventType.GIFT_EVENT, Money.from(25000));
        BenefitDetails benefitDetailsInput = new BenefitDetails(benefitsData);

        //when
        BenefitDetailsDto benefitDetailsDto = BenefitDetailsDto.from(benefitDetailsInput);
        Map<String, BigDecimal> benefitDetails = benefitDetailsDto.getDetails();

        //then
        assertThat(benefitDetails.size())
                .isEqualTo(4);
        assertThat(benefitDetails.get("크리스마스 디데이 할인"))
                .isEqualTo(BigDecimal.valueOf(1200));
        assertThat(benefitDetails.get("평일 할인"))
                .isEqualTo(BigDecimal.valueOf(4046));
        assertThat(benefitDetails.get("특별 할인"))
                .isEqualTo(BigDecimal.valueOf(1000));
        assertThat(benefitDetails.get("증정 이벤트"))
                .isEqualTo(BigDecimal.valueOf(25000));
    }

    @DisplayName("증정메뉴가 없을 때 증정메뉴 dto객체 생성에 성공한다.")
    @Test
    void createBenefitDetailsDtoIfNotExists() {
        // given
        BenefitDetails emptyBenefitDetails = new BenefitDetails(Collections.emptyMap());

        // when
        BenefitDetailsDto benefitDetailsDto = BenefitDetailsDto.from(emptyBenefitDetails);
        Map<String, BigDecimal> benefitDetails = benefitDetailsDto.getDetails();

        // then
        assertThat(benefitDetails).isEmpty();
    }
}