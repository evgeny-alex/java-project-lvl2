package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class PlainFormatter extends Formatter {

    public static String buildDifferenceString(List<Map<String, Object>> differenceList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map<String, Object> keyMap : differenceList) {
            String key = (String) keyMap.get("key");
            String type = (String) keyMap.get("type");

            String value;

            switch (type) {
                case "added" -> {
                    value = getValueAsString(keyMap.get("value"));
                    stringBuilder
                            .append("Property '")
                            .append(key)
                            .append("' was added with value: ")
                            .append(value)
                            .append("\n");
                }
                case "deleted" -> {
                    stringBuilder
                            .append("Property '")
                            .append(key)
                            .append("' was removed")
                            .append("\n");
                }
                case "changed" -> {
                    Object value1 = getValueAsString(keyMap.get("value1"));
                    Object value2 = getValueAsString(keyMap.get("value2"));

                    stringBuilder
                            .append("Property '")
                            .append(key)
                            .append("' was updated. From ")
                            .append(value1)
                            .append(" to ")
                            .append(value2)
                            .append("\n");
                }
                case "equals" -> {
                }
                default -> throw new RuntimeException("Для типа изменения'" + type
                        + "' не определена логика заполнения строки.");
            }
        }

        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder.toString();
    }

    private static String getValueAsString(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value.toString();
    }
}
