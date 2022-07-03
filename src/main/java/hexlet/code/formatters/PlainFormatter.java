package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter extends Formatter {
    @Override
    public String equalsValue(String key, Object value) {
        return "";
    }

    @Override
    public String changedValue(String key, Object value1, Object value2) {
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
        stringBuilder.setLength(0);
        stringBuilder
                .append("Property '")
                .append(key)
                .append("' was updated. From ")
                .append(value1)
                .append(" to ")
                .append(value2);
        return stringBuilder.toString();
    }

    @Override
    public String deletedValue(String key, Object value) {
        stringBuilder.setLength(0);
        stringBuilder
                .append("Property '")
                .append(key)
                .append("' was removed");
        return stringBuilder.toString();
    }

    @Override
    public String addedValue(String key, Object value) {
        if (value instanceof String) {
            value = "'" + value + "'";
        }
        if (value instanceof Map || value instanceof List) {
            value = "[complex value]";
        }
        stringBuilder.setLength(0);
        stringBuilder
                .append("Property '")
                .append(key)
                .append("' was added with value: ")
                .append(value);
        return stringBuilder.toString();
    }

    @Override
    public String startString() {
        return "";
    }

    @Override
    public String endString() {
        return "";
    }

    @Override
    public String newLineString() {
        return "\n";
    }
}
