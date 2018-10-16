package sandem.java_1.lesson4_2;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Абстрактный класс sandem.java_1.lesson4_2.Animal
 */
public abstract class Animal {
    private String name;
    private int swimLimit;
    private double jumpLimit;
    private int runLimit;

    public Animal(String _name){
        this.name = _name;
    }

    public Animal(){
        this.name = "Unnamed";
    }

    public void setSwimLimit(int swimLimit) {
        this.swimLimit = swimLimit;
    }

    public void setJumpLimit(double jumpLimit) {
        this.jumpLimit = jumpLimit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRunLimit(int runLimit) {
        this.runLimit = runLimit;
    }

    public double getJumpLimit() {
        return jumpLimit;
    }

    public int getRunLimit() {
        return runLimit;
    }

    public int getSwimLimit() {
        return swimLimit;
    }

    public String getName() {
        return name;
    }

    public abstract void swim(int distance);
    public abstract void run(int distance);
    public abstract void jump(double distance);

    public void getInfo() {
        BigDecimal a = new BigDecimal(jumpLimit);
        System.out.print(this.name + ", max swim = " + swimLimit + ", max jump = " + a.setScale(2, RoundingMode.DOWN).toString() + ", max run = " + runLimit);
    }
}
