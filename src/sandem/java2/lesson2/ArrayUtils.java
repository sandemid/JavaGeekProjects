package sandem.java2.lesson2;

public class ArrayUtils {

    public static void sortBubble (int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    changeElements(array, j , j + 1 );
                }
            }
        }
    }

    public static void sortBubble (MyArray array){
        for (int i = 0; i < array.getSize() - 1; i++) {
            for (int j = 0; j < array.getSize() - 1 - i; j++) {
                if (array.getElement(j) > array.getElement(j + 1)) {
                    changeElements(array.getArray(), j , j + 1 );
                }
            }
        }
        array.setSorted(true);
    }

    public static void sortSelect (MyArray array){
        for (int i = 0; i < array.getSize() - 1; i++) {
            int minElemIndex = i;
            for (int j = i + 1; j < array.getSize(); j++) {
                if ( array.getElement(j) < array.getElement(minElemIndex) ) {
                    minElemIndex = j;
                }
            }
            changeElements(array.getArray(), i, minElemIndex);
        }
        array.setSorted(true);
    }

    public static void sortSelect (int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            int minElemIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if ( array[j] < array[minElemIndex] ) {
                    minElemIndex = j;
                }
            }
            changeElements(array, i, minElemIndex);
        }
    }

    public static void sortInsert (MyArray array){
        int in, out;
        for (out = 1; out < array.getSize(); out++) {
            int temp = array.getElement(out);
            in = out;
            while (in > 0 && array.getElement(in - 1) >= temp) {
                array.getArray()[in] = array.getElement(in - 1);
                --in;
            }
            array.getArray()[in] = temp;

        }
        array.setSorted(true);
    }

    public static void sortInsert (int[] array){
        int in, out;
        for (out = 1; out < array.length; out++) {
            int temp = array[out];
            in = out;
            while (in > 0 && array[in - 1] >= temp) {
                array[in] = array[in - 1];
                --in;
            }
            array[in] = temp;
        }
    }

    private static void changeElements (int[] array, int indexA, int indexB) {
        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }
}
