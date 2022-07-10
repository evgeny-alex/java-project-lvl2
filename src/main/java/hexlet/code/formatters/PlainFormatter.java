package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class PlainFormatter extends Formatter {

    private StringBuilder stringBuilder;

    public PlainFormatter() {
        this.stringBuilder = new StringBuilder();
    }

    @Override
    public void fillEqualsValue(String key, Object value) {
    }

    @Override
    public void fillChangedValue(String key, Object value1, Object value2) {
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

    @Override
    public void fillDeletedValue(String key, Object value) {
        stringBuilder
                .append("Property '")
                .append(key)
                .append("' was removed")
                .append("\n");
    }

    @Override
    public void fillAddedValue(String key, Object value) {
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

    @Override
    public String getDiffString() {
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder.toString();
    }
}
