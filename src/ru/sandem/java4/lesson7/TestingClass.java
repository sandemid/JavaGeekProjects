package ru.sandem.java4.lesson7;

import ru.sandem.java4.lesson7.annotations.AfterSuite;
import ru.sandem.java4.lesson7.annotations.BeforeSuite;
import ru.sandem.java4.lesson7.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestingClass {

    public static void main(String[] args) {
        try {
            System.out.println("Запуск теста с вызовом через класс");
            TestingClass.start(ExampleTestOne.class);
            System.out.println();
            System.out.println("Запуск теста с вызовом через имя класса");
            TestingClass.start("ru.sandem.java4.lesson7.ExampleTestOne");
        } catch (InvocationTargetException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    static void start(Class c) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method[] methods = c.getDeclaredMethods();
        // Использую TreeMap, чтобы тестируемые методы складывались в мапу сразу в порядке приоритета, и затем также вызывались
        // Внутри TreeMap находится List, чтобы обойти коллизию с одинаковыми ключами
        // Т.е. чтобы два метода с одинаковым приоритетом корректно записывались в мапу
        Map<Integer, List<Method>> methodsTest = new TreeMap<>();
        List<Method> methodsAfter = new ArrayList<>();
        List<Method> methodsBefore = new ArrayList<>();
        for (Method o : methods) {
            extractTestMethods(methodsTest, o);
            extractBeforeSuiteMethods(methodsBefore, o);
            extractAfterSuiteMethods(methodsAfter, o);
        }
        runBeforeMethod(c, methodsBefore);
        runTestMethods(c, methodsTest);
        runAfterMethod(c, methodsAfter);
    }

    private static void runAfterMethod(Class c, List<Method> methodsAfter) throws IllegalAccessException, InvocationTargetException {
        // Проверка единственности аннотации AfterSuite. Если одна - запускаем, иначе бросаем exception
        if (methodsAfter.size() == 1) {
            methodsAfter.get(0).setAccessible(true);
            methodsAfter.get(0).invoke(c);
        } else if (methodsAfter.size() != 0) {
            throw new RuntimeException("More 1 methods with annotation AfterSuite!");
        }
    }

    private static void runTestMethods(Class c, Map<Integer, List<Method>> methodsTest) throws IllegalAccessException, InvocationTargetException {
        // Запуск методов-тестов в порядке их приоритета
        for (List<Method> value : methodsTest.values()) {
            for (Method method : value) {
                System.out.println("Run " + c.getName() + " " + method.getName() + " priority = "
                        + method.getAnnotation(Test.class).priority()
                        + ", testing class = " + method.getAnnotation(Test.class).classForTest().getName()
                        + ", testing method = " + method.getAnnotation(Test.class).methodName());
                method.setAccessible(true);
                System.out.println("    Result test = " + method.invoke(c));
            }
        }
    }

    private static void runBeforeMethod(Class c, List<Method> methodsBefore) throws IllegalAccessException, InvocationTargetException {
        // Проверка единственности аннотации BeforeSuite. Если одна - запускаем, иначе бросаем exception
        if (methodsBefore.size() == 1) {
            methodsBefore.get(0).setAccessible(true);
            methodsBefore.get(0).invoke(c);
        } else if (methodsBefore.size() != 0) {
            throw new RuntimeException("More 1 methods with annotation BeforeSuite!");
        }
    }

    private static void extractBeforeSuiteMethods(List<Method> methodsBefore, Method o) {
        if (o.getAnnotation(BeforeSuite.class) != null) {
            methodsBefore.add(o);
        }
    }

    private static void extractAfterSuiteMethods(List<Method> methodsAfter, Method o) {
        if (o.getAnnotation(AfterSuite.class) != null) {
            methodsAfter.add(o);
        }
    }

    private static void extractTestMethods(Map<Integer, List<Method>> methodsTest, Method o) {
        if (o.getAnnotation(Test.class) != null) {
            // Проверяем приоритет методов-тестов на соответствие допустимым параметрам. Если ошибка - бросаем исключение
            if (o.getAnnotation(Test.class).priority() > o.getAnnotation(Test.class).maxPriority()
                    || o.getAnnotation(Test.class).priority() < o.getAnnotation(Test.class).minPriority()) {
                throw new RuntimeException("Error on priority in @Test!, method = " + o.getName());
            }
            // Обход коллизии с ключами в мапе
            if (methodsTest.get(o.getAnnotation(Test.class).priority()) == null){
                methodsTest.put(o.getAnnotation(Test.class).priority(), new LinkedList<>());
                methodsTest.get(o.getAnnotation(Test.class).priority()).add(o);
            } else {
                methodsTest.get(o.getAnnotation(Test.class).priority()).add(o);
            }
        }
    }

    private static void start(String className) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        start(Class.forName(className));
    }
}
