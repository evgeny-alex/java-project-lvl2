package hexlet.code.formatters;

public abstract class Formatter {

    protected StringBuilder stringBuilder = new StringBuilder();

    public static Formatter createFormatter(String type) {
        Formatter formatter;
        switch (type) {
            case "stylish":
                formatter = new StylishFormatter();
                break;
            case "plain":
                formatter = new PlainFormatter();
                break;
            default:
                throw new RuntimeException("Для типа - '" + type + "' не задан форматтер.");
        }
        return formatter;
    }

    public abstract String equalsValue(String key, Object value);

    public abstract String changedValue(String key, Object value1, Object value2);

    public abstract String deletedValue(String key, Object value);

    public abstract String addedValue(String key, Object value);

    public abstract String startString();

    public abstract String endString();

    public abstract String newLineString();
}
