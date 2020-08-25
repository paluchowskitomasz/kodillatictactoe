package com.kodilla.tictactoe;

import com.kodilla.tictactoe.player.Player;
import javafx.util.Pair;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class Game implements Serializable {

    public static final long serialVersionUID = 8888888888L;

    private Player crossPlayer;
    private Player circlePlayer;
    private Sign turn;
    private Map<Pair<Integer, Integer>, Sign> stateMap = new HashMap<>();
    private List<Pair<Integer, Integer>> victoriousList = new LinkedList<>();
    private Result result = Result.STARTED;
    private Date date = new Date();

    public Game(Player crossPlayer, Player circlePlayer, Sign turn) {
        this.circlePlayer = circlePlayer;
        this.crossPlayer = crossPlayer;
        this.turn = turn;
    }

    public Sign getState(int column, int row) {
        Pair<Integer, Integer> key = new Pair<>(column, row);
        Sign sign = stateMap.get(key);

        if (sign == null) {
            return Sign.EMPTY;
        } else {
            return sign;
        }
    }

    public void put(int column, int row, Sign sign) {
        Pair<Integer, Integer> key = new Pair<>(column, row);
        stateMap.put(key, sign);

        if (turn == Sign.CROSS) {
            turn = Sign.CIRCLE;
        } else {
            turn = Sign.CROSS;
        }

        checkDraw();
        checkVictory(0, 0, 1, 0, 2, 0);
        checkVictory(0, 1, 1, 1, 2, 1);
        checkVictory(0, 2, 1, 2, 2, 2);
        checkVictory(0, 0, 0, 1, 0, 2);
        checkVictory(1, 0, 1, 1, 1, 2);
        checkVictory(2, 0, 2, 1, 2, 2);
        checkVictory(0, 0, 1, 1, 2, 2);
        checkVictory(0, 2, 1, 1, 2, 0);
    }

    public Player getCirclePlayer() {
        return circlePlayer;
    }

    public Player getCrossPlayer() {
        return crossPlayer;
    }

    public String printResults() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD/MM/YYYY HH:mm:SS");
        String formattedDate = simpleDateFormat.format(date);
        return formattedDate + " O - " + circlePlayer.getName() + ", X - " + crossPlayer.getName() + ", rezultat: " + result.getText();
    }

    public Result getResult() {
        return result;
    }

    public void next(Save save) {
        if (result == Result.STARTED) {
            if (turn == Sign.CROSS) {
                crossPlayer.move(this, save);
            } else {
                circlePlayer.move(this, save);
            }
        }
        save.save();
    }

    private void checkDraw() {
        if (createAvailableMoves().isEmpty()) {
            result = Result.DRAW;
        }
    }

    private void checkVictory(int x1, int y1, int x2, int y2, int x3, int y3) {
        Sign first = stateMap.get(new Pair<>(x1, y1));
        Sign second = stateMap.get(new Pair<>(x2, y2));
        Sign third = stateMap.get(new Pair<>(x3, y3));

        if (first == second && second == third) {
            if (first == Sign.CROSS) {
                result = Result.CROSS_WON;
                victoriousList.add(new Pair<>(x1, y1));
                victoriousList.add(new Pair<>(x2, y2));
                victoriousList.add(new Pair<>(x3, y3));
                crossPlayer.addPoints(10);
            } else if (first == Sign.CIRCLE) {
                result = Result.CIRCLE_WON;
                victoriousList.add(new Pair<>(x1, y1));
                victoriousList.add(new Pair<>(x2, y2));
                victoriousList.add(new Pair<>(x3, y3));
                circlePlayer.addPoints(10);
            }
        }
    }

    public Sign getTurn() {
        return turn;
    }

    public List<Pair<Integer, Integer>> createAvailableMoves() {
        List<Pair<Integer, Integer>> availableMoves = new LinkedList<>();

        addAvailableMove(availableMoves, 0, 0);
        addAvailableMove(availableMoves, 0, 1);
        addAvailableMove(availableMoves, 0, 2);

        addAvailableMove(availableMoves, 1, 0);
        addAvailableMove(availableMoves, 1, 1);
        addAvailableMove(availableMoves, 1, 2);

        addAvailableMove(availableMoves, 2, 0);
        addAvailableMove(availableMoves, 2, 1);
        addAvailableMove(availableMoves, 2, 2);
        return availableMoves;
    }

    private void addAvailableMove(List<Pair<Integer, Integer>> availableMoves, int column, int row) {
        Sign sign = getState(column, row);
        if (sign == Sign.EMPTY) {
            availableMoves.add(new Pair<>(column, row));
        }
    }

    public List<Pair<Integer, Integer>> getVictoriousList() {
        return victoriousList;
    }

    public boolean isFinished() {
        return result == Result.CROSS_WON || result == Result.CIRCLE_WON || result == Result.DRAW;
    }

    public boolean isStarted() {
        return result == Result.STARTED;
    }

    public boolean canPut(int x, int y) {
        return createAvailableMoves().contains(new Pair<>(x, y));
    }

    public boolean hasSign(int column, int row) {
        return getState(column, row) == turn;
    }
}
