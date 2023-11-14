package christmas.view;

import christmas.domain.model.OrderRequest;
import christmas.error.ErrorMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputProcessor {

    private static final String COMMA = ",";
    private static final String DASH = "-";

    public int toDate(String input) {
        validateNotNull(input);
        input = input.trim();
        return toNumber(input);
    }

    private void validateNotNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException(ErrorMessage.NULL_ERROR.getMessage());
        }
    }

    private Integer toNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NUMERIC.getMessage());
        }
    }

    private List<String> parseWith(String input, String mark) {
        return Arrays.stream(input.split(mark))
                .map(String::trim)
                .toList();
    }

}

