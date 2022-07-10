package hexlet.code;

import hexlet.code.formatters.Formatter;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Differ {

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

        Formatter formatter = Formatter.createFormatter(formatType);

        for (Map.Entry<String, Object> stringObjectEntry : resultData.entrySet()) {
            String key = stringObjectEntry.getKey();
            Object value1 = dataFile1.get(key);
            Object value2 = dataFile2.get(key);

            if (dataFile1.containsKey(key)) {
                if (Objects.equals(value1, value2)) {   // Эквивалентны
                    formatter.fillEqualsValue(key, value1);
                } else {
                    if (dataFile2.containsKey(key)) {   // Изменилось во 2 файле
                        formatter.fillChangedValue(key, value1, value2);
                    } else {                            // Удалено во 2 файле
                        formatter.fillDeletedValue(key, value1);
                    }
                }
            } else {                                    // Добавлено значение во 2 файле
                formatter.fillAddedValue(key, value2);
            }
        }
        return formatter.getDiffString();
    }
}
