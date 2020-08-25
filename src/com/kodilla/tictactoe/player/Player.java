package com.kodilla.tictactoe.player;

import com.kodilla.tictactoe.Game;
import com.kodilla.tictactoe.Save;

import java.io.Serializable;

public abstract class Player implements Serializable {

    public static final long serialVersionUID = 11111111111L;

    private String name;
    private int points;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPoints(int pointsAdded) {
        this.points = this.points + pointsAdded;
    }

    public abstract void move(Game game, Save save);

    public int getPoints() {
        return points;
    }
}
