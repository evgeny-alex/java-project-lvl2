package hexlet.code;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    private static final char[] COMPARE_SYMBOLS = {'+', '-', ' '};

    private static final int INDEX_PLUS = 0;

    private static final int INDEX_MINUS = 1;

    private static final int INDEX_SPACE = 2;

    public static Map<String, Object> getData(String content) throws Exception {
        return Parser.parse(content);
    }

    public static String generate(String filePath1, String filePath2, String formatType) throws Exception {
        String contentFile1 = Files.readString(Path.of(filePath1));
        String contentFile2 = Files.readString(Path.of(filePath2));

        Map<String, Object> dataFile1 = getData(contentFile1);
        Map<String, Object> dataFile2 = getData(contentFile2);

        Map<String, Object> resultData = new TreeMap<>(dataFile1);
        resultData.putAll(dataFile2);

        StringBuilder diffStringBuilder = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : resultData.entrySet()) {
            diffStringBuilder.append("\n\t");

            String key = entry.getKey();
            Object value1 = dataFile1.get(key);
            Object value2 = dataFile2.get(key);

            if (value1 != null) {
                if (value1.equals(value2)) { // Эквивалентны
                    diffStringBuilder
                            .append(COMPARE_SYMBOLS[INDEX_SPACE])
                            .append(key)
                            .append(": ")
                            .append(value1);
                } else {
                    if (value2 != null) { // изменилось во 2 файле
                        diffStringBuilder
                                .append(COMPARE_SYMBOLS[INDEX_MINUS])
                                .append(key)
                                .append(": ")
                                .append(value1)
                                .append("\n\t")
                                .append(COMPARE_SYMBOLS[INDEX_PLUS])
                                .append(key)
                                .append(": ")
                                .append(value2);
                    } else {            // Удалено во 2 файле
                        diffStringBuilder
                                .append(COMPARE_SYMBOLS[INDEX_MINUS])
                                .append(key)
                                .append(": ")
                                .append(value1);
                    }
                }
            } else {
                diffStringBuilder
                        .append(COMPARE_SYMBOLS[INDEX_PLUS])
                        .append(key)
                        .append(": ")
                        .append(value2);
            }

        }
        diffStringBuilder.append("\n}");

        return diffStringBuilder.toString();
    }
}
