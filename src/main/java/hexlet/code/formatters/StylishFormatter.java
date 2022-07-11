package hexlet.code.formatters;

public final class StylishFormatter extends Formatter {

    private final StringBuilder stringBuilder;

    private static final char[] COMPARE_SYMBOLS = {'+', '-', ' '};

    private static final int INDEX_PLUS = 0;

    private static final int INDEX_MINUS = 1;

    private static final int INDEX_SPACE = 2;

    public StylishFormatter() {
        stringBuilder = new StringBuilder();
        stringBuilder.append("{\n  ");
    }

    @Override
    public void fillEqualsValue(String key, Object value) {
        stringBuilder
                .append(COMPARE_SYMBOLS[INDEX_SPACE])
                .append(" ")
                .append(key)
                .append(": ")
                .append(value)
                .append("\n  ");
    }

    @Override
    public void fillChangedValue(String key, Object value1, Object value2) {
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

    @Override
    public void fillDeletedValue(String key, Object value) {
        stringBuilder
                .append(COMPARE_SYMBOLS[INDEX_MINUS])
                .append(" ")
                .append(key)
                .append(": ")
                .append(value)
                .append("\n  ");
    }

    @Override
    public void fillAddedValue(String key, Object value) {
        stringBuilder
                .append(COMPARE_SYMBOLS[INDEX_PLUS])
                .append(" ")
                .append(key)
                .append(": ")
                .append(value)
                .append("\n  ");
    }

    @Override
    public String getDiffString() {
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }


}
