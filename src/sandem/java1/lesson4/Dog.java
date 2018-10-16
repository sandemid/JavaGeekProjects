package sandem.java_1.lesson4_2;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Подкласс sandem.java_1.lesson4_2.Dog
 * Устанавливаем константами ограничения на прыжки, бег и плавание для котов в целом
 * Если в конструктор не подаются параметры бега, прыжков и плавания, то ограничения остаются дефолтными для экземпляра
 * Если передаются - то ограничения устанавливаются на переданные, но с условием, что они лежат в интервале от 0 до дефолтного ограничения
 * В методах прыжков, бега и плавания сначала выполняется метод суперкласса getInfo(), который выводит в консоль инфо об экзепляре
 *
 */

public class Dog extends Animal {

    private final int dogSwimLimit = 10;
    private final double dogJumpLimit = 0.5;
    private final int dogRunLimit = 500;


    public Dog (String _name) {
        super(_name);
        setSwimLimit(dogSwimLimit);
        setJumpLimit(dogJumpLimit);
        setRunLimit(dogRunLimit);
    }

    public Dog (String _name, int _swimLimit, double _jumpLimit, int _runLimit) {
        super(_name);
        if(_swimLimit > 0 && _swimLimit < dogSwimLimit) {
            setSwimLimit(_swimLimit);
        }else {
            setSwimLimit(dogSwimLimit);
        }
        if(_jumpLimit > 0 && _jumpLimit < dogJumpLimit) {
            setJumpLimit(_jumpLimit);
        }else {
            setJumpLimit(dogJumpLimit);
        }
        if(_runLimit > 0 && _runLimit < dogRunLimit) {
            setRunLimit(_runLimit);
        }else {
            setRunLimit(dogRunLimit);
        }
    }

    public Dog () {
        super();
        setSwimLimit(dogSwimLimit);
        setJumpLimit(dogJumpLimit);
        setRunLimit(dogRunLimit);
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.print(" - is sandem.java_1.lesson4_2.Dog");
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
