package christmas;

import christmas.controller.DecemberEventController;
import christmas.view.InputProcessor;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView(new InputProcessor());
        OutputView outputView = new OutputView();

        DecemberEventController decemberEventController = new DecemberEventController(inputView, outputView);
        decemberEventController.play();
    }
}
