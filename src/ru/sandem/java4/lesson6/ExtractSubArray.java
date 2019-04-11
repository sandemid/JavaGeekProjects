package ru.sandem.java4.lesson6;

import java.util.ArrayList;
import java.util.List;

public class ExtractSubArray {

    public ExtractSubArray() {
        super();
    }

    public int[] getSubArray(int... arrayInput) throws RuntimeException {

        List<List<Integer>> highLevelList = new ArrayList<>();
        final int separator = 4;
        for (int i = 0; i < arrayInput.length; i++) {
            List<Integer> lowLevelList = new ArrayList<>();
            for (int j = 0; j < separator && i + j < arrayInput.length; j++) {
                lowLevelList.add(arrayInput[i + j]);
            }
            highLevelList.add(lowLevelList);
            i += lowLevelList.size() - 1;
        }
        if (highLevelList.size() == 1 && highLevelList.get(highLevelList.size() - 1).size() < separator) {
            throw new RuntimeException("В массиве должно быть больше 4 элементов!");
        }
        if (highLevelList.get(highLevelList.size() - 1).size() == separator) {
            return null;
        }
        int[] arrayOutput = new int[highLevelList.get(highLevelList.size() - 1).size()];
        for (int i = 0; i < arrayOutput.length; i++) {
            arrayOutput[i] =  highLevelList.get(highLevelList.size() - 1).get(i);
        }
        return arrayOutput;
    }

}
