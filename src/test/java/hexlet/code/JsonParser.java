package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class JsonParser {

    private final String filePath1 = "src/test/resources/file1_2.json";

    private final String filePath2 = "src/test/resources/file2_2.json";

    private final String diffFilePath3 = "src/test/resources/diff3";

    private final String diffFilePath2 = "src/test/resources/diff2";

    private final String diffFilePath1 = "src/test/resources/diff1";

    @Test
    public void testJsonParserStylish() throws Exception {
        String diffString = Differ.generate(filePath1, filePath2, "stylish");
        String diffStringFromFile = Files.readString(Path.of(diffFilePath3));
        Assertions.assertEquals(diffString, diffStringFromFile);
    }

    @Test
    public void testJsonParserDefault() throws Exception {
        String diffString = Differ.generate(filePath1, filePath2);
        String diffStringFromFile = Files.readString(Path.of(diffFilePath3));
        Assertions.assertEquals(diffString, diffStringFromFile);
    }

    @Test
    public void testJsonParserPlain() throws Exception {
        String diffString = Differ.generate(filePath1, filePath2, "plain");
        String diffStringFromFile = Files.readString(Path.of(diffFilePath2));
        Assertions.assertEquals(diffString, diffStringFromFile);
    }

    @Test
    public void testJsonParserJsonFormatter() throws Exception {
        String diffString = Differ.generate(filePath1, filePath2, "json");
        String diffStringFromFile = Files.readString(Path.of(diffFilePath1));
        Assertions.assertEquals(diffString, diffStringFromFile);
    }

}
