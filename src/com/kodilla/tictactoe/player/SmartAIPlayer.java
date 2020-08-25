package com.kodilla.tictactoe.player;

import com.kodilla.tictactoe.Game;
import com.kodilla.tictactoe.Save;
import com.kodilla.tictactoe.TicTacToe;
import javafx.util.Pair;

import java.util.List;
import java.util.Random;

public class SmartAIPlayer extends Player {

    public static final long serialVersionUID = 22222222222L;

    private Random random = new Random();

    public SmartAIPlayer(String name) {
        super(name);
    }

    @Override
    public void move(Game game, Save save) {
        Pair<Integer, Integer> move = getMove(game);
        game.put(move.getKey(), move.getValue(), game.getTurn());
        TicTacToe.setGameScreen(game);
        game.next(save);
    }

    private Pair<Integer, Integer> getMove(Game game) {
        Pair<Integer, Integer> move = checkColumns(game);
        if (move != null) {
            return move;
        }
        move = checkRows(game);
        if (move != null) {
            return move;
        }
        move = checkDiagonals(game);
        if (move != null) {
            return move;
        }

        if (game.canPut(1, 1)) {
            return pair(1, 1);
        } else if (game.canPut(0, 0)) {
            return pair(0, 0);
        } else if (game.canPut(2, 2)) {
            return pair(2, 2);
        } else if (game.canPut(2, 0)) {
            return pair(2, 0);
        } else if (game.canPut(0, 2)) {
            return pair(0, 2);
        }

        List<Pair<Integer, Integer>> availableMoves = game.createAvailableMoves();

        int index = random.nextInt(availableMoves.size());
        return availableMoves.get(index);
    }

    private Pair<Integer, Integer> checkColumns(Game game) {
        Pair<Integer, Integer> move = checkColumn(game, 0);
        if (move != null) {
            return move;
        }

        move = checkColumn(game, 1);
        if (move != null) {
            return move;
        }

        move = checkColumn(game, 2);
        if (move != null) {
            return move;
        }
        return null;
    }

    private Pair<Integer, Integer> checkRows(Game game) {
        Pair<Integer, Integer> move = checkRow(game, 0);
        if (move != null) {
            return move;
        }

        move = checkRow(game, 1);
        if (move != null) {
            return move;
        }

        move = checkRow(game, 2);
        if (move != null) {
            return move;
        }
        return null;
    }

    private Pair<Integer, Integer> checkColumn(Game game, int column) {
        if (game.hasSign(column, 0) && game.hasSign(column, 1) && game.canPut(column, 2)) {
            return pair(column, 2);
        }
        if (game.hasSign(column, 0) && game.canPut(column, 1) && game.hasSign(column, 2)) {
            return pair(column, 1);
        }
        if (game.canPut(column, 0) && game.hasSign(column, 1) && game.hasSign(column, 2)) {
            return pair(column, 0);
        }
        return null;
    }

    private Pair<Integer, Integer> checkRow(Game game, int row) {
        if (game.hasSign(0, row) && game.hasSign(1, row) && game.canPut(2, row)) {
            return pair(2, row);
        }
        if (game.hasSign(0, row) && game.canPut(1, row) && game.hasSign(2, row)) {
            return pair(1, row);
        }
        if (game.canPut(0, row) && game.hasSign(1, row) && game.hasSign(2, row)) {
            return pair(0, row);
        }
        return null;
    }

    private Pair<Integer, Integer> checkDiagonals(Game game) {
        if (game.hasSign(0, 0) && game.hasSign(1, 1) && game.canPut(2, 2)) {
            return pair(2, 2);
        }
        if (game.hasSign(0, 0) && game.canPut(1, 1) && game.hasSign(2, 2)) {
            return pair(1, 1);
        }
        if (game.canPut(0, 0) && game.hasSign(1, 1) && game.hasSign(2, 2)) {
            return pair(0, 0);
        }
        if (game.hasSign(2, 0) && game.hasSign(1, 1) && game.canPut(0, 2)) {
            return pair(0, 2);
        }
        if (game.hasSign(2, 0) && game.canPut(1, 1) && game.hasSign(0, 2)) {
            return pair(1, 1);
        }
        if (game.canPut(2, 0) && game.hasSign(1, 1) && game.hasSign(0, 2)) {
            return pair(2, 0);
        }
        return null;
    }

    private Pair<Integer, Integer> pair(int x, int y) {
        return new Pair<>(x, y);
    }
}
