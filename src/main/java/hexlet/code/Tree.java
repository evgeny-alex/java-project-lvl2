package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Objects;

public class Tree {

    public static List<Map<String, Object>> buildDifferenceList(Map<String, Object> data1, Map<String, Object> data2) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        Map<String, Object> resultMap = new TreeMap<>(data1);
        resultMap.putAll(data2);

        for (Map.Entry<String, Object> stringObjectEntry : resultMap.entrySet()) {
            String key = stringObjectEntry.getKey();
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            Map<String, Object> keyMap = new HashMap<>();
            keyMap.put("key", key);

            if (data1.containsKey(key)) {
                if (Objects.equals(value1, value2)) {   // Эквивалентны
                    keyMap.put("type", "equals");
                    keyMap.put("value", value1);
                } else {
                    if (data2.containsKey(key)) {   // Изменилось во 2 файле
                        keyMap.put("type", "changed");
                        keyMap.put("value1", value1);
                        keyMap.put("value2", value2);
                    } else {                            // Удалено во 2 файле
                        keyMap.put("type", "deleted");
                        keyMap.put("value", value1);
                    }
                }
            } else {                                    // Добавлено значение во 2 файле
                keyMap.put("type", "added");
                keyMap.put("value", value2);
            }
            resultList.add(keyMap);
        }

        return resultList;
    }
}
