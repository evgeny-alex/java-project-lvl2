package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class StylishFormatter {

    private static final char[] COMPARE_SYMBOLS = {'+', '-', ' '};

    private static final int INDEX_PLUS = 0;

    private static final int INDEX_MINUS = 1;

    private static final int INDEX_SPACE = 2;

    public static String buildDifferenceString(List<Map<String, Object>> differenceList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n  ");
        for (Map<String, Object> keyMap : differenceList) {
            String key = (String) keyMap.get("key");
            String type = (String) keyMap.get("type");

            Object value;

            switch (type) {
                case "added" -> {
                    value = keyMap.get("value");
                    stringBuilder
                            .append(COMPARE_SYMBOLS[INDEX_PLUS])
                            .append(" ")
                            .append(key)
                            .append(": ")
                            .append(value)
                            .append("\n  ");
                }
                case "deleted" -> {
                    value = keyMap.get("value");
                    stringBuilder
                            .append(COMPARE_SYMBOLS[INDEX_MINUS])
                            .append(" ")
                            .append(key)
                            .append(": ")
                            .append(value)
                            .append("\n  ");
                }
                case "changed" -> {
                    Object value1 = keyMap.get("value1");
                    Object value2 = keyMap.get("value2");
                    stringBuilder
                            .append(COMPARE_SYMBOLS[INDEX_MINUS])
                            .append(" ")
                            .append(key)
                            .append(": ")
                            .append(value1)
                            .append("\n  ")
                            .append(COMPARE_SYMBOLS[INDEX_PLUS])
                            .append(" ")
                            .append(key)
                            .append(": ")
                            .append(value2)
                            .append("\n  ");
                }
                case "equals" -> {
                    value = keyMap.get("value");
                    stringBuilder
                            .append(COMPARE_SYMBOLS[INDEX_SPACE])
                            .append(" ")
                            .append(key)
                            .append(": ")
                            .append(value)
                            .append("\n  ");
                }
                default -> throw new RuntimeException("Для типа изменения'" + type
                        + "' не определена логика заполнения строки.");
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
