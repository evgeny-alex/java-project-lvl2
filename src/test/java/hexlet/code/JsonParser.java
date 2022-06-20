package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class JsonParser {

    private final String filePath1 = "src/test/resources/file1.json";

    private final String filePath2 = "src/test/resources/file2.json";

    private final String diffFilePath = "src/test/resources/diff";

    @Test
    public void testJsonParser() throws Exception {
        String diffString = Differ.generate(filePath1, filePath2, "text");
        String diffStringFromFile = Files.readString(Path.of(diffFilePath));

        Assertions.assertEquals(diffString, diffStringFromFile);
    }
}
