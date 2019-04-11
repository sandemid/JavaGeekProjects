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
public class CheckArrayTest {

    private static CheckArray checkArray = null;
    private int[] array;
    private boolean result;

    public CheckArrayTest(int[] array, boolean result) {
        this.array = array;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        int[] arrayInputTest1 = {1,1,1,4,1};
        int[] arrayInputTest2 = {1,1,1,4,1,1,1,1,4,4,4};
        int[] arrayInputTest3 = {1,1,1,4,1,6,1,1,4,4,4};
        int[] arrayInputTest4 = {4,4,4,4,4,4};
        int[] arrayInputTest5 = {1,1,1,1,1,1,1};

        return Arrays.asList(
                new Object[][]{
                        {arrayInputTest1, true},
                        {arrayInputTest2, true},
                        {arrayInputTest3, false},
                        {arrayInputTest4, false},
                        {arrayInputTest5, false},
                }
        );
    }

    @Test
    public void checkArray() {
        Assert.assertEquals("Fail test!", checkArray.checkArray(array),result);
    }

    @Before
    public void init() {
        System.out.println("init checkArray");
        checkArray = new CheckArray();
    }

    @After
    public void tearDown() {
        checkArray = null;
    }
}