package ru.sandem.java3.lesson3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task1 {

    public static void main(String[] args) {

        List<String> words = new ArrayList<>();

        words.add("le");
        words.add("1233");
        words.add("213123");
        words.add("213123");
        words.add("213123");
        words.add("ff");
        words.add("213123");
        words.add("213123");
        words.add("ff");
        words.add("213123");

        List<String> unicwords = new ArrayList<>();

        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            if (wordMap.containsKey(word)){
                wordMap.put(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
                unicwords.add(word);
            }
        }
        
        System.out.println("Счетчик повторений слов  " + wordMap);
        System.out.println("Список всех слов = " + unicwords);

        System.out.println("Уникальные слова, не имеющие повторений в списке:");
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println(entry.getKey() + " ");
            }
        }
        
    }

}
