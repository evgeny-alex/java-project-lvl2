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
        return getMapperByExtension(extensionType).readValue(content, Map.class);
    }

    private static ObjectMapper getMapperByExtension(String extensionType) {
        switch (extensionType) {
            case EXTENSION_JSON -> {
                return new ObjectMapper(new JsonFactory());
            }
            case EXTENSION_YML -> {
                return new ObjectMapper(new YAMLFactory());
            }
            default -> throw new RuntimeException("Для расширения файла - '" + extensionType + "' не задан парсер.");
        }
    }
}
