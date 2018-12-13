package ru.sandem.java3.lesson1;

public class Main {

    public static void main(String[] args) {

        int maxBarrier = 100;
        Player player1 = new Player("Alex", Math.round(maxBarrier * 0.7f));
        Player player2 = new Player("John", Math.round(maxBarrier * 0.5f));
        Player player3 = new Player("Victor", Math.round(maxBarrier * 0.3f));
        Player player4 = new Player("Gendalf", maxBarrier);
        Player player5 = new Player("Bilbo", Math.round(maxBarrier * 0.9f));

        Team team = new Team("GoldCobra", player1, player2, player3, player4, player5);
        team.display();

        for (int i = 0; i < 5; i++) {
            Course c = new Course(maxBarrier / (i + 1) * 2);
            c.doIt(team);
            team.showResults();
        }

    }
}
