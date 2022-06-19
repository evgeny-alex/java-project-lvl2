package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String content) throws JsonProcessingException {
        return new ObjectMapper().readValue(content, Map.class);
    }
}
