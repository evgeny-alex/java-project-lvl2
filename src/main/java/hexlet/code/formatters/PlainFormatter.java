package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class PlainFormatter extends Formatter {

    public static String buildDifferenceString(List<Map<String, Object>> differenceList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map<String, Object> keyMap : differenceList) {
            String key = (String) keyMap.get("key");
            String type = (String) keyMap.get("type");

            Object value;

            switch (type) {
                case "added" -> {
                    value = keyMap.get("value");
                    if (value instanceof String) {
                        value = "'" + value + "'";
                    }
                    if (value instanceof Map || value instanceof List) {
                        value = "[complex value]";
                    }
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
                    Object value1 = keyMap.get("value1");
                    Object value2 = keyMap.get("value2");
                    if (value1 instanceof String) {
                        value1 = "'" + value1 + "'";
                    }
                    if (value2 instanceof String) {
                        value2 = "'" + value2 + "'";
                    }
                    if (value1 instanceof Map || value1 instanceof List) {
                        value1 = "[complex value]";
                    }
                    if (value2 instanceof Map || value2 instanceof List) {
                        value2 = "[complex value]";
                    }
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
}
