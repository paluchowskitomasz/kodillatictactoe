package com.kodilla.tictactoe.player;

import com.kodilla.tictactoe.Game;
import com.kodilla.tictactoe.Save;
import com.kodilla.tictactoe.TicTacToe;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class AIPlayer extends Player implements Serializable {

    public static final long serialVersionUID = 6666666666L;

    private Random random = new Random();

    public AIPlayer(String name) {
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
        List<Pair<Integer, Integer>> availableMoves = game.createAvailableMoves();
        int index = random.nextInt(availableMoves.size());
        return availableMoves.get(index);
    }
}
