package com.kodilla.tictactoe.player;

import com.kodilla.tictactoe.Game;
import com.kodilla.tictactoe.Save;
import com.kodilla.tictactoe.TicTacToe;

public class HumanPlayer extends Player {

    public static final long serialVersionUID = 777777777L;

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void move(Game game, Save save) {
        TicTacToe.setGameScreen(game);
    }
}
