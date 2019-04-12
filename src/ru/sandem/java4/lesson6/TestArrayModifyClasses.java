package ru.sandem.java4.lesson6;

public class TestArrayModifyClasses {

    public static void main(String[] args) {
        System.out.println("Исходный массив");
        printArray(1, 2, 3, 4, 5);
        System.out.println("Извлекаем подмассив после последней четверки элементов");
        try {
            printArray(new ExtractSubArray().getSubArray(1, 2, 3, 4, 5));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        System.out.println();
        printArray(1,1,1,1,1,1,4);
        System.out.println("Проверка массива на 1 и 4, результат = " + new CheckArray().checkArray(1,1,1,1,1,1,4));
    }

    private static void printArray(int... subArray) {
        System.out.print("Массив = {");
        if (subArray != null) {
            for (int i = 0; i < subArray.length; i++) {
                System.out.print(subArray[i]);
                if (i != subArray.length - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.print("}");
        System.out.println();
    }

}
