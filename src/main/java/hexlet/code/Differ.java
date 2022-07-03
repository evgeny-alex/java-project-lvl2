package hexlet.code;

import hexlet.code.formatters.Formatter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
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

        StringBuilder diffStringBuilder = new StringBuilder(formatter.startString());

        Iterator<Map.Entry<String, Object>> it = resultData.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            Object value1 = dataFile1.get(key);
            Object value2 = dataFile2.get(key);

            String currentString;

            if (dataFile1.containsKey(key)) {
                if (Objects.equals(value1, value2)) {   // Эквивалентны
                    currentString = formatter.equalsValue(key, value1);
                } else {
                    if (dataFile2.containsKey(key)) {   // Изменилось во 2 файле
                        currentString = formatter.changedValue(key, value1, value2);
                    } else {                            // Удалено во 2 файле
                        currentString = formatter.deletedValue(key, value1);
                    }
                }
            } else {                                    // Добавлено значение во 2 файле
                currentString = formatter.addedValue(key, value2);
            }
            if (StringUtils.isNotEmpty(currentString)) {
                if (it.hasNext()) {
                    diffStringBuilder.append(currentString).append(formatter.newLineString());
                } else {
                    diffStringBuilder.append(currentString).append(formatter.endString());
                }
            }
        }
        return diffStringBuilder.toString();
    }
}
