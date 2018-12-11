package ru.sandem.java1.lesson4;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Подкласс ru.sandem.java_1.lesson4_2.Cat
 * Устанавливаем константами ограничения на прыжки, бег и плавание для котов в целом
 * Если в конструктор не подаются параметры бега, прыжков и плавания, то ограничения остаются дефолтными для экземпляра
 * Если передаются - то ограничения устанавливаются на переданные, но с условием, что они лежат в интервале от 0 до дефолтного ограничения
 * В методах прыжков, бега и плавания сначала выполняется метод суперкласса getInfo(), который выводит в консоль инфо об экзепляре
 *
 */

public class Cat extends Animal {

    private final int catSwimLimit = 0;
    private final double catJumpLimit = 2;
    private final int catRunLimit = 200;


    public Cat (String _name) {
        super(_name);
        setSwimLimit(catSwimLimit);
        setJumpLimit(catJumpLimit);
        setRunLimit(catRunLimit);
    }

    public Cat (String _name, int _swimLimit, double _jumpLimit, int _runLimit) {
        super(_name);
        if(_swimLimit > 0 && _swimLimit < catSwimLimit) {
            setSwimLimit(_swimLimit);
        }else {
            setSwimLimit(catSwimLimit);
        }
        if(_jumpLimit > 0 && _jumpLimit < catJumpLimit) {
            setJumpLimit(_jumpLimit);
        }else {
            setJumpLimit(catJumpLimit);
        }
        if(_runLimit > 0 && _runLimit < catRunLimit) {
            setRunLimit(_runLimit);
        }else {
            setRunLimit(catRunLimit);
        }
    }

    public Cat () {
        super();
        setSwimLimit(catSwimLimit);
        setJumpLimit(catJumpLimit);
        setRunLimit(catRunLimit);
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.print(" - is ru.sandem.java_1.lesson4_2.Cat");
    }

    @Override
    public void swim(int distance){
        getInfo();
        if (distance <= getSwimLimit() && distance > 0) {
            System.out.println(" - can swim " + distance);
        } else {
            System.out.println(" - can't swim " + distance);
        }
    }

    @Override
    public void run(int distance) {
        getInfo();
        if (distance <= getRunLimit() && distance > 0) {
            System.out.println(" - can run " + distance);
        } else {
            System.out.println(" - can't run " + distance);
        }
    }

    @Override
    public void jump(double distance) {
        getInfo();
        BigDecimal a = new BigDecimal(distance);
        if (distance <= getJumpLimit() && distance > 0) {
            System.out.println(" - can jump " + a.setScale(2, RoundingMode.DOWN).toString());
        } else {
            System.out.println(" - can't jump " + a.setScale(2, RoundingMode.DOWN).toString());
        }
    }
}
