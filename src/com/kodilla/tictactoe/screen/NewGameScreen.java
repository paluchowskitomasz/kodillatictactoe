package com.kodilla.tictactoe.screen;

import com.kodilla.tictactoe.Game;
import com.kodilla.tictactoe.Save;
import com.kodilla.tictactoe.Sign;
import com.kodilla.tictactoe.TicTacToe;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class NewGameScreen extends AbstractScreen {

    public NewGameScreen(Save save) {
        super(save);
    }

    @Override
    public GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setBackground(createBackground());
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Button backButton = new Button("Powrót");
        backButton.setOnMouseClicked(event -> {
            TicTacToe.setScreen("mainMenu");
        });

        Button playAsRandomButton = new Button("Graj losowym znakiem");
        playAsRandomButton.setOnMouseClicked(event -> {
            Random random = new Random();

            if (random.nextBoolean()) {
                playAsCross();
            } else {
                playAsCircle();
            }
        });

        Button playAsCrossButton = new Button("Graj jako krzyżyk");
        playAsCrossButton.setOnMouseClicked(event -> {
            playAsCross();
        });

        Button playAsCircleButton = new Button("Graj jako kółko");
        playAsCircleButton.setOnMouseClicked(event -> {
            playAsCircle();
        });

        gridPane.add(createText("Witaj " + save.getHumanPlayer().getName()), 0, 0);
        gridPane.add(createText("Nowa gra"), 0, 1);
        gridPane.add(playAsRandomButton, 0, 2);
        gridPane.add(playAsCrossButton, 0, 3);
        gridPane.add(playAsCircleButton, 0, 4);
        gridPane.add(backButton, 0, 5);

        return gridPane;
    }

    private void playAsCircle() {
        Game game = new Game(save.getAIPlayer(), save.getHumanPlayer(), Sign.CROSS);
        save.getGames().add(game);
        TicTacToe.setGameScreen(game);
        game.next(save);
    }

    private void playAsCross() {
        Game game = new Game(save.getHumanPlayer(), save.getAIPlayer(), Sign.CROSS);
        save.getGames().add(game);
        TicTacToe.setGameScreen(game);
        game.next(save);
    }
}
