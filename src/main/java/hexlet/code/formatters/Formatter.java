package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(List<Map<String, Object>> differenceList, String type) {
        return switch (type) {
            case "stylish" -> StylishFormatter.buildDifferenceString(differenceList);
            case "plain" -> PlainFormatter.buildDifferenceString(differenceList);
            case "json" -> JsonFormatter.buildDifferenceString(differenceList);
            default -> throw new RuntimeException("Для типа - '" + type + "' не задан форматтер.");
        };
    }
}
