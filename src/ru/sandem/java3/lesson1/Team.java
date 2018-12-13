package ru.sandem.java3.lesson1;

import java.text.MessageFormat;

public class Team {

    private Player[] players ;
    private String teamName;
    private boolean[] passedDistance;
    private int teamSize;

    public Team(String teamName, Player ... players) {
        teamSize = players.length;
        this.players = new Player[teamSize];
        passedDistance = new boolean[teamSize];
        for (int i = 0; i < teamSize; i++) {
            this.players[i] = players[i];
            passedDistance[i] = false;
        }
        this.teamName = teamName;
    }

    public void display (){
        System.out.println("Команда: " + teamName);
        for (int i = 0; i < players.length; i++) {
            String s;
            s = MessageFormat.format("Игрок {1}, максимально преодолеваемый барьер {2}", i, players[i].getName(), players[i].getCanOvercomeBarrier());
            System.out.println(s);
        }
    }

    public void showResults(){
        System.out.println("Прошли дистанцию:");
        for (int i = 0; i < teamSize; i++) {
            if (passedDistance[i]){
                System.out.println(players[i].getName());
            }
        }
    }

    public void passDistance (int playerIndex){
        passedDistance[playerIndex] = true;
    }

    public void notPassDistance (int playerIndex){
        passedDistance[playerIndex] = false;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public Player getPlayer(int index) {
        return players[index];
    }
}
