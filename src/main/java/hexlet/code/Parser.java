package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    private static final String EXTENSION_JSON = "json";

    private static final String EXTENSION_YML = "yml";

    @SuppressWarnings("unchecked")
    public static Map<String, Object> parse(String content, String extensionType) throws JsonProcessingException {
        Map<String, Object> resultMap;
        switch (extensionType) {
            case EXTENSION_JSON -> resultMap = new ObjectMapper(new JsonFactory()).readValue(content, Map.class);
            case EXTENSION_YML -> resultMap = new ObjectMapper(new YAMLFactory()).readValue(content, Map.class);
            default -> throw new RuntimeException("Для расширения файла - '" + extensionType + "' не задан парсер.");
        }
        return resultMap;
    }
}
