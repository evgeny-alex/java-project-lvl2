package hexlet.code;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Differ {

    private static final char[] COMPARE_SYMBOLS = {'+', '-', ' '};

    private static final int INDEX_PLUS = 0;

    private static final int INDEX_MINUS = 1;

    private static final int INDEX_SPACE = 2;


    public static Map<String, Object> getData(String content, String extensionType) throws Exception {
        return Parser.parse(content, extensionType);
    }

    public static String generate(String filePath1, String filePath2, String formatType) throws Exception {
        String contentFile1 = Files.readString(Path.of(filePath1));
        String contentFile2 = Files.readString(Path.of(filePath2));

        Map<String, Object> dataFile1 = getData(contentFile1, FilenameUtils.getExtension(filePath1));
        Map<String, Object> dataFile2 = getData(contentFile2,  FilenameUtils.getExtension(filePath2));

        Map<String, Object> resultData = new TreeMap<>(dataFile1);
        resultData.putAll(dataFile2);

        StringBuilder diffStringBuilder = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : resultData.entrySet()) {
            diffStringBuilder.append("\n\t");

            String key = entry.getKey();
            Object value1 = dataFile1.get(key);
            Object value2 = dataFile2.get(key);

            if (dataFile1.containsKey(key)) {
                if (Objects.equals(value1, value2)) { // Эквивалентны
                    diffStringBuilder
                            .append(COMPARE_SYMBOLS[INDEX_SPACE])
                            .append(" ")
                            .append(key)
                            .append(": ")
                            .append(value1);
                } else {
                    if (dataFile2.containsKey(key)) { // изменилось во 2 файле
                        diffStringBuilder
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
                    } else {            // Удалено во 2 файле
                        diffStringBuilder
                                .append(COMPARE_SYMBOLS[INDEX_MINUS])
                                .append(" ")
                                .append(key)
                                .append(": ")
                                .append(value1);
                    }
                }
            } else {
                diffStringBuilder
                        .append(COMPARE_SYMBOLS[INDEX_PLUS])
                        .append(" ")
                        .append(key)
                        .append(": ")
                        .append(value2);
            }

        }
        diffStringBuilder.append("\n}");

        return diffStringBuilder.toString();
    }
}
