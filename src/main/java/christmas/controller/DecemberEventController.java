package christmas.controller;

import christmas.domain.model.*;
import christmas.domain.model.eventcalculator.BenefitDetails;
import christmas.domain.model.eventcalculator.DiscountCalculator;
import christmas.domain.model.eventcalculator.EventBadge;
import christmas.domain.model.order.VisitDate;
import christmas.domain.model.order.Order;
import christmas.domain.model.order.OrderItem;
import christmas.dto.*;
import christmas.message.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;

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
        VisitDate visitDate = requestDate();
        Order order = requestOrder();
        DiscountCalculator discountCalculator = new DiscountCalculator(order, visitDate);

        printOrderMenu(order);
        printTotalOrderPrice(discountCalculator);
        printGiftMenu(discountCalculator);
        printBenefitDetails(discountCalculator);
        printTotalBenefitAmounts(discountCalculator);
        printExpectedPayment(discountCalculator);
        printEventBadge(discountCalculator);
    }

    private VisitDate requestDate() {
        return requestInput(InputView::readDate, VisitDate::from, ErrorMessage.DATE_INPUT_ERROR);
    }

    private Order requestOrder() {
        return requestInput(InputView::readOrderRequests, Order::from, ErrorMessage.ORDER_INPUT_ERROR);
    }

    private void printOrderMenu(Order order) {
        outputView.printOrderMenu(OrderDto.from(order));
    }

    private void printTotalOrderPrice(DiscountCalculator discountCalculator) {
        Money totalPrice = discountCalculator.calculateTotalPrice();
        outputView.printTotalOrderPrice(MoneyDto.from(totalPrice));
    }

    private void printGiftMenu(DiscountCalculator discountCalculator) {
        Optional<OrderItem> giftMenu = discountCalculator.calculateGiftMenu();
        outputView.printGiftMenu(GiftMenuDto.from(giftMenu));
    }

    private void printBenefitDetails(DiscountCalculator discountCalculator) {
        BenefitDetails benefitDetails = discountCalculator.calculateBenefitDetails();
        outputView.printBenefitDetails(BenefitDetailsDto.from(benefitDetails));
    }

    private void printTotalBenefitAmounts(DiscountCalculator discountCalculator) {
        Money totalBenefitAmounts = discountCalculator.calculateTotalBenefitAmounts();
        outputView.printTotalBenefitAmounts(MoneyDto.from(totalBenefitAmounts));
    }

    private void printExpectedPayment(DiscountCalculator discountCalculator) {
        Money expectedPayment = discountCalculator.calculateExpectedPayment();
        outputView.printExpectedPayment(MoneyDto.from(expectedPayment));
    }

    private void printEventBadge(DiscountCalculator discountCalculator) {
        EventBadge eventBadge = discountCalculator.calculateEventBadge();
        outputView.printEventBadge(EventBadgeDto.from(eventBadge));
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
