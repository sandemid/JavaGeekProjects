package ru.sandem.java4.lesson7;

import ru.sandem.java4.lesson7.annotations.AfterSuite;
import ru.sandem.java4.lesson7.annotations.BeforeSuite;
import ru.sandem.java4.lesson7.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ExampleTestOne {

    private static int successTest;
    private static int failedTest;

    @Test(priority = 10, methodName = "checkStringsLength", classForTest = ClassForTest.class)
    private static boolean checkIntegerTest1() throws NoSuchMethodException, RuntimeException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Constructor constructor = ExampleTestOne.class.getDeclaredMethod("checkIntegerTest1")
                .getAnnotation(Test.class).classForTest().getConstructor(String.class);
        ClassForTest objectForTest = (ClassForTest) constructor.newInstance("");
        Method method = objectForTest.getClass().getDeclaredMethod(ExampleTestOne.class
                .getDeclaredMethod("checkIntegerTest1").getAnnotation(Test.class).methodName(),String[].class);
        String[] strings = {"qwertyu", "asdfghjkl", "zxcvbnm", "pokiujhytgfreds sefda", "rfvedcwsxyhnujm", "sdflsdkf"};
        method.setAccessible(true);
        if ((Boolean)method.invoke(objectForTest, new Object[]{strings})) {
            successTest++;
            return true;
        } else {
            failedTest++;
            return false;
        }
    }

    @Test(priority = 6, methodName = "checkStringsLength", classForTest = ClassForTest.class)
    private static boolean checkIntegerTest2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = ExampleTestOne.class.getDeclaredMethod("checkIntegerTest2")
                .getAnnotation(Test.class).classForTest().getConstructor(String.class);
        ClassForTest objectForTest = (ClassForTest) constructor.newInstance("");
        Method method = objectForTest.getClass().getDeclaredMethod(ExampleTestOne.class
                .getDeclaredMethod("checkIntegerTest2").getAnnotation(Test.class).methodName(), String[].class);
        String[] strings = {"qwertyu", "asdfghjkl", "zxcvbnm", "pokiujhytgfreds sefda", "rfvedcwsxyhnujm", "sdflsdkf"};
        method.setAccessible(true);
        if (!(Boolean)method.invoke(objectForTest, new Object[]{strings})) {
            successTest++;
            return true;
        } else {
            failedTest++;
            return false;
        }
    }

    @Test(priority = 2, methodName = "getFactorial", classForTest = ClassForTest.class)
    private static boolean getFactorialTest1() throws NoSuchMethodException {
        Constructor constructor = ExampleTestOne.class.getDeclaredMethod("getFactorialTest1")
                .getAnnotation(Test.class).classForTest().getConstructor(String.class);
        ClassForTest objectForTest = null;
        try {
            objectForTest = (ClassForTest) constructor.newInstance("");

        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        Method method = objectForTest.getClass().getDeclaredMethod(ExampleTestOne.class.getDeclaredMethod("getFactorialTest1")
                .getAnnotation(Test.class).methodName(), int.class);
        method.setAccessible(true);
        try {
            method.invoke(objectForTest, 21);
        } catch (InvocationTargetException | IllegalAccessException e) {
            if (e.getCause().getClass().equals(IllegalArgumentException.class)){
                successTest++;
                return true;
            } else {
                e.printStackTrace();
            }
        }
        failedTest++;
        return false;
    }

    @Test(priority = 0, methodName = "getFactorial", classForTest = ClassForTest.class)
    private static boolean getFactorialTest2() throws NoSuchMethodException {
        Constructor constructor = ExampleTestOne.class.getDeclaredMethod("getFactorialTest2").getAnnotation(Test.class)
                .classForTest().getConstructor(String.class);
        ClassForTest objectForTest = null;
        try {
            objectForTest = (ClassForTest) constructor.newInstance("");

        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        Method method = objectForTest.getClass().getDeclaredMethod(ExampleTestOne.class.getDeclaredMethod("getFactorialTest2")
                .getAnnotation(Test.class).methodName(), int.class);
        method.setAccessible(true);
        try {
            method.invoke(objectForTest, -1);
        } catch (InvocationTargetException | IllegalAccessException e) {
            if (e.getCause().getClass().equals(IllegalArgumentException.class)){
                successTest++;
                return true;
            } else {
                e.printStackTrace();
            }
        }
        failedTest++;
        return false;
    }

    @Test(priority = 0, methodName = "getFactorial", classForTest = ClassForTest.class)
    private static boolean getFactorialTest3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = ExampleTestOne.class.getDeclaredMethod("getFactorialTest3").getAnnotation(Test.class).classForTest().getConstructor(String.class);
        ClassForTest objectForTest = (ClassForTest) constructor.newInstance("");
        Method method = objectForTest.getClass().getDeclaredMethod(ExampleTestOne.class.getDeclaredMethod("getFactorialTest3" +
                "").getAnnotation(Test.class).methodName(), int.class);
        method.setAccessible(true);
        if ((Integer) method.invoke(objectForTest, 10) == 3628800) {
            successTest++;
            return true;
        }
        failedTest++;
        return false;
    }

    @AfterSuite
    private static void shutdown() {
        System.out.println();
        System.out.println("**********");
        System.out.println("AfterSuite");
        System.out.println("Successful test count = " + successTest);
        System.out.println("Failed test count = " + failedTest);
        System.out.println("**********");
        System.out.println();
    }

    @BeforeSuite
    private static void init() {
        successTest = 0;
        failedTest = 0;
        System.out.println();
        System.out.println("**********");
        System.out.println("BeforeSuite");
        System.out.println("Initialize counters");
        System.out.println("**********");
        System.out.println();
    }
//    Если раскомментировать, при запуске класса-теста выбросится Exception, т.к. будет два метода с аннотацией BeforeSuite
//    @BeforeSuite
//    private static void init1() {
//        successTest = 0;
//        failedTest = 0;
//        System.out.println();
//        System.out.println("**********");
//        System.out.println("BeforeSuite");
//        System.out.println("Initialize counters");
//        System.out.println("**********");
//        System.out.println();
//    }
}
