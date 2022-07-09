package hexlet.code.formatters;

public abstract class Formatter {

    public static Formatter createFormatter(String type) {
        Formatter formatter = null;
        switch (type) {
            case "stylish":
                formatter = new StylishFormatter();
                break;
            case "plain":
                formatter = new PlainFormatter();
                break;
            case "json":
                formatter = new JsonFormatter();
                break;
            default:
                throw new RuntimeException("Для типа - '" + type + "' не задан форматтер.");
        }
        return formatter;
    }

    public abstract void fillEqualsValue(String key, Object value);

    public abstract void fillChangedValue(String key, Object value1, Object value2);

    public abstract void fillDeletedValue(String key, Object value);

    public abstract void fillAddedValue(String key, Object value);

    public abstract String getDiffString();
}
