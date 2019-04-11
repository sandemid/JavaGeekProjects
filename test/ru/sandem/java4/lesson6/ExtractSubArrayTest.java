package ru.sandem.java4.lesson6;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class ExtractSubArrayTest {

    private static ExtractSubArray extractSubArray = null;
    private int[] arrayInput;
    private int[] arrayOutput;

    public ExtractSubArrayTest(int[] arrayInput, int[] arrayOutput) {
        this.arrayInput = arrayInput;
        this.arrayOutput = arrayOutput;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        int[] arrayInputTest1 = {1,2,3,4,5};
        int[] arrayOutputTest1 = {5};
        int[] arrayInputTest2 = {1,2,3,4,5,6,7};
        int[] arrayOutputTest2 = {5,6,7};
        int[] arrayInputTest3 = {1,2,3,4,5,6,7,8,9,10};
        int[] arrayOutputTest3 = {9,10};
        int[] arrayInputTest4 = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        int[] arrayOutputTest4 = {13};
        int[] arrayInputTest5 = {1,2,3,4};
        int[] arrayOutputTest5 = null;

        return Arrays.asList(
                new Object[][]{
                        {arrayInputTest1, arrayOutputTest1},
                        {arrayInputTest2, arrayOutputTest2},
                        {arrayInputTest3, arrayOutputTest3},
                        {arrayInputTest4, arrayOutputTest4},
                        {arrayInputTest5, arrayOutputTest5},
                }
        );
    }

    @Test
    public void getSubArrayTest() {
        Assert.assertArrayEquals("Fail",extractSubArray.getSubArray(arrayInput), arrayOutput);
    }

    @Test(expected = RuntimeException.class)
    public void getSubArrayTestException() {
        Assert.assertArrayEquals("Fail",extractSubArray.getSubArray(1,2,3), null);
    }

    @Before
    public void init() {
        System.out.println("init extractSubArray");
        extractSubArray = new ExtractSubArray();
    }

    @After
    public void tearDown() {
        extractSubArray = null;
    }
}
