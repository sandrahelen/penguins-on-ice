package poi.game.models.clientServer;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Datahandler {
    private final Map<String, Integer> scores;

    public Datahandler() {
        scores = new LinkedHashMap<>();
    }

    public void setScores(String name, int time) {
        scores.put(name, time);
    }

    public Map<String, Integer> getScores() {
        return sortByValue(scores);
    }

    public void clearScores() {
        scores.clear();
    }

    public void printScores() {
        System.out.println(scores);
    }

    // Sorting Map by value (https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values)
    private Map<String, Integer> sortByValue(final Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
