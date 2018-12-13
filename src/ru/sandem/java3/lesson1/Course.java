package ru.sandem.java3.lesson1;

import java.util.Random;

public class Course {

    private static Random random = new Random();
    private final int COURSE_SIZE = 5;
    private int[] barrierLine = new int[COURSE_SIZE];

    public Course(int maxBarrierSize) {
        for (int i = 0; i < COURSE_SIZE; i++) {
            this.barrierLine[i] = random.nextInt(maxBarrierSize);
        }
    }

    public void doIt (Team team) {

        for (int i = 0; i < team.getTeamSize(); i++) {
            int c = 0;
            team.notPassDistance(i);
            for (int j = 0; j < barrierLine.length ; j++) {
                if (team.getPlayer(i).getCanOvercomeBarrier() >= barrierLine[j]) {
                    c++;
                }
            }
            if (c == barrierLine.length) {
                team.passDistance(i);
            }
        }

    }
}
