package hexlet.code.formatters;

public class StylishFormatter extends Formatter {

    private static final char[] COMPARE_SYMBOLS = {'+', '-', ' '};

    private static final int INDEX_PLUS = 0;

    private static final int INDEX_MINUS = 1;

    private static final int INDEX_SPACE = 2;

    @Override
    public String equalsValue(String key, Object value) {
        stringBuilder.setLength(0);
        stringBuilder
                .append(COMPARE_SYMBOLS[INDEX_SPACE])
                .append(" ")
                .append(key)
                .append(": ")
                .append(value);
        return stringBuilder.toString();
    }

    @Override
    public String changedValue(String key, Object value1, Object value2) {
        stringBuilder.setLength(0);
        stringBuilder
                .append(COMPARE_SYMBOLS[INDEX_MINUS])
                .append(" ")
                .append(key)
                .append(": ")
                .append(value1)
                .append("\n\t")
                .append(COMPARE_SYMBOLS[INDEX_PLUS])
                .append(" ")
                .append(key)
                .append(": ")
                .append(value2);
        return stringBuilder.toString();
    }

    @Override
    public String deletedValue(String key, Object value) {
        stringBuilder.setLength(0);
        stringBuilder
                .append(COMPARE_SYMBOLS[INDEX_MINUS])
                .append(" ")
                .append(key)
                .append(": ")
                .append(value);
        return stringBuilder.toString();
    }

    @Override
    public String addedValue(String key, Object value) {
        stringBuilder.setLength(0);
        stringBuilder
                .append(COMPARE_SYMBOLS[INDEX_PLUS])
                .append(" ")
                .append(key)
                .append(": ")
                .append(value);
        return stringBuilder.toString();
    }

    @Override
    public String startString() {
        return "{\n\t";
    }

    @Override
    public String endString() {
        return "\n}";
    }

    @Override
    public String newLineString() {
        return "\n\t";
    }
}
