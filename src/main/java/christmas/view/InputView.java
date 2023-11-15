package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderRequestDto;
import christmas.message.PromptMessage;

import java.util.List;

public class InputView {

    private final InputProcessor inputProcessor;

    public InputView(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    public int readDate() {
        System.out.println(PromptMessage.READ_DATE.getPrompt());
        String input = read();
        return inputProcessor.toDate(input);
    }

    public List<OrderRequestDto> readOrderRequests() {
        System.out.println(PromptMessage.READ_ORDER_REQUESTS.getPrompt());
        String input = read();
        return inputProcessor.toOrderRequests(input);
    }

    private String read() {
        return Console.readLine();
    }

}
