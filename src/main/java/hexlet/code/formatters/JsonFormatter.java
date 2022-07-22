package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public final class JsonFormatter extends Formatter {

    public static String buildDifferenceString(List<Map<String, Object>> differenceList) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(differenceList);
        } catch (JsonProcessingException e) {
            System.err.println("Произошла ошибка при парсинге в Json");
        }
        return null;
    }
}
