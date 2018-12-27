package ru.sandem.java3.lesson3;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Task2 {
    public static void main(String[] args) {
        TelBook.add("Иванов", "111111");
        TelBook.add("Иванов", "222222");
        TelBook.add("Иванов", "333333");
        TelBook.add("Петров", "123456");

        System.out.println(TelBook.get("Иванов"));
        System.out.println(TelBook.get("Петров"));
    }

    public static class TelBook {
        static Map<String, ArrayList<String>> telephones = new HashMap<>();

        static void add(String name, String telephone) {
            if (telephones.containsKey(name)) {
                telephones.get(name).add(telephone);
            } else {
                telephones.put(name, new ArrayList<>(Arrays.asList(telephone)));
            }
        }

        static String get(String name) {
            return MessageFormat.format("{0} : {1}", name, telephones.get(name));
        }
    }
}
