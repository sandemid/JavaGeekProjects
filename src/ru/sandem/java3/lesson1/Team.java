package ru.sandem.java3.lesson1;

public class Team {

    private final int TEAM_SIZE = 4;
    private Player[] players = new Player[TEAM_SIZE];
    private String teamName;
    private int[] passedDistance = new int[TEAM_SIZE];

    public Team(String teamName, Player ... players) {
        for (int i = 0; i < TEAM_SIZE; i++) {
            this.players[i] = players[i];
        }
        this.teamName = teamName;
    }

    public void showTeam (){

    }

    public void showResults(){

    }
}
