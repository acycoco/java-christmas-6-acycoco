package christmas.controller;

import christmas.domain.model.*;
import christmas.dto.*;
import christmas.exception.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DecemberEventController {
    private final InputView inputView;
    private final OutputView outputView;

    public DecemberEventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        December visitDate = requestDate();
        Order order = requestOrder();

        OrderInfo orderInfo = new OrderInfo(order, visitDate);
        outputView.printOrderMenu(OrderDto.from(order));

        DiscountCalculator discountCalculator = new DiscountCalculator(orderInfo);
        Money totalPrice = discountCalculator.calculateTotalPrice();
        outputView.printTotalOrderPrice(MoneyDto.from(totalPrice));

        Optional<OrderItem> giftMenu = discountCalculator.calculateGiftMenu();
        outputView.printGiftMenu(GiftMenuDto.from(giftMenu));

        BenefitDetails benefitDetails = discountCalculator.calculateBenefitDetails();
        outputView.printBenefitDetails(BenefitDetailsDto.from(benefitDetails));

        Money totalBenefitAmounts = discountCalculator.calculateTotalBenefitAmounts();
        outputView.printTotalBenefitAmounts(MoneyDto.from(totalBenefitAmounts));

        Money expectedPayment = discountCalculator.calculateExpectedPayment();
        outputView.printExpectedPayment(MoneyDto.from(expectedPayment));

        EventBadge eventBadge = discountCalculator.calculateEventBadge();
        outputView.printEventBadge(EventBadgeDto.from(eventBadge));
    }

    private December requestDate() {
        return requestInput(InputView::readDate, December::from, ErrorMessage.DATE_INPUT_ERROR);
    }

    private Order requestOrder() {
        return requestInput(InputView::readOrderRequests, Order::from, ErrorMessage.ORDER_INPUT_ERROR);
    }

    private <T, U> U requestInput(Function<InputView, T> readFunction, Function<T, U> convertFunction, ErrorMessage errorMessage) {
        while (true) {
            try {
                T input = readFunction.apply(inputView);
                return convertFunction.apply(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(errorMessage);
            } finally {
                outputView.printEmptyLine();
            }
        }
    }

}