package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class JsonFormatter extends Formatter {

    public static String buildDifferenceString(List<Map<String, Object>> differenceList) {
        List<Map<String, Object>> diffList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (Map<String, Object> keyMap : differenceList) {
            String key = (String) keyMap.get("key");
            String type = (String) keyMap.get("type");

            Object value;

            switch (type) {
                case "added" -> {
                    value = keyMap.get("value");
                    diffList.add(Map.of("added", Map.of(key, value)));
                }
                case "deleted" -> {
                    value = keyMap.get("value");
                    diffList.add(Map.of("deleted", Map.of(key, value)));
                }
                case "changed" -> {
                    Object value1 = keyMap.get("value1");
                    Object value2 = keyMap.get("value2");
                    diffList.add(Map.of("changed from", Map.of(key, value1 != null ? value1 : "null")));
                    diffList.add(Map.of("changed to", Map.of(key, value2 != null ? value2 : "null")));
                }
                case "equals" -> {
                    value = keyMap.get("value");
                    diffList.add(Map.of("equals", Map.of(key, value)));
                }
                default -> throw new RuntimeException("Для типа изменения'" + type
                        + "' не определена логика заполнения.");
            }
        }

        try {
            return objectMapper.writeValueAsString(diffList);
        } catch (JsonProcessingException e) {
            System.err.println("Произошла ошибка при парсинге в Json");
        }
        return null;
    }
}
