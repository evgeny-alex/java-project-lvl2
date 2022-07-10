package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class JsonFormatter extends Formatter {

    private List<Map<String, Object>> diffList;

    private ObjectMapper objectMapper;

    public JsonFormatter() {
        this.diffList = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void fillEqualsValue(String key, Object value) {
        diffList.add(Map.of("equals", Map.of(key, value)));
    }

    @Override
    public void fillChangedValue(String key, Object value1, Object value2) {
        diffList.add(Map.of("changed from", Map.of(key, value1 != null ? value1 : "null")));
        diffList.add(Map.of("changed to", Map.of(key, value2 != null ? value2 : "null")));
    }

    @Override
    public void fillDeletedValue(String key, Object value) {
        diffList.add(Map.of("deleted", Map.of(key, value)));
    }

    @Override
    public void fillAddedValue(String key, Object value) {
        diffList.add(Map.of("added", Map.of(key, value)));
    }

    @Override
    public String getDiffString() {
        try {
            return objectMapper.writeValueAsString(diffList);
        } catch (JsonProcessingException e) {
            System.err.println("Произошла ошибка при парсинге в Json");
        }
        return null;
    }
}
