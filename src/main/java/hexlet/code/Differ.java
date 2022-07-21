package hexlet.code;

import hexlet.code.formatters.Formatter;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatType) throws Exception {
        String contentFile1 = Files.readString(Path.of(filePath1));
        String contentFile2 = Files.readString(Path.of(filePath2));

        Map<String, Object> dataFile1 = Parser.parse(contentFile1, FilenameUtils.getExtension(filePath1));
        Map<String, Object> dataFile2 = Parser.parse(contentFile2,  FilenameUtils.getExtension(filePath2));

        List<Map<String, Object>> differenceList = Tree.buildDifferenceList(dataFile1, dataFile2);

        return Formatter.format(differenceList, formatType);
    }
}
