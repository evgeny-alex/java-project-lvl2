package hexlet.code;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

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

        StringBuilder diffStringBuilder = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : dataFile1.entrySet()) {
            diffStringBuilder.append("\n\t");
            if (dataFile2.get(entry.getKey()) != null) {
                if (dataFile2.get(entry.getKey()).equals(entry.getValue())){ // Значения полностью эквивалентны
                    diffStringBuilder
                            .append(COMPARE_SYMBOLS[INDEX_SPACE])
                            .append(entry.getKey())
                            .append(": ")
                            .append(entry.getValue());
                } else { // Значение изменилось во втором файле
                    diffStringBuilder
                            .append(COMPARE_SYMBOLS[INDEX_MINUS])
                            .append(entry.getKey())
                            .append(": ")
                            .append(entry.getValue())
                            .append("\n\t");
                    // TODO: 04.06.2022 Доделать
                }
            } else { // Значение не найдено во втором файле

            }

        }
        diffStringBuilder.append("}");

        return diffStringBuilder.toString();
    }
}
