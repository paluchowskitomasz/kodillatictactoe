package com.kodilla.tictactoe.screen;

import com.kodilla.tictactoe.Save;
import com.kodilla.tictactoe.TicTacToe;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MainMenuScreen extends AbstractScreen {

    public MainMenuScreen(Save save) {
        super(save);
    }

    @Override
    public GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setBackground(createBackground());
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Button newGameButton = new Button("Nowa gra");
        newGameButton.setOnMouseClicked(event -> {
            TicTacToe.setScreen("newGame");
        });

        Button savedGamesButton = new Button("Wczytaj grę");
        savedGamesButton.setOnMouseClicked(event -> {
            TicTacToe.setScreen("savedGames");
        });

        Button bestScoresButton = new Button("Najlepsze wyniki");
        bestScoresButton.setOnMouseClicked(event -> {
            TicTacToe.setScreen("scores");
        });

        Button playersButton = new Button("Zmień gracza");
        playersButton.setOnMouseClicked(event -> {
            TicTacToe.setScreen("players");
        });

        Button archiveButton = new Button("Archiwum gier");
        archiveButton.setOnMouseClicked(event -> {
            TicTacToe.setScreen("archive");
        });

        Button difficultyButton = new Button("Poziom trudności");
        difficultyButton.setOnMouseClicked(event -> {
            TicTacToe.setScreen("difficulty");
        });

        gridPane.add(createText("Witaj " + save.getHumanPlayer().getName()), 0, 0);
        gridPane.add(createText("Menu główne"), 0, 1);
        gridPane.add(newGameButton, 0, 2);
        gridPane.add(savedGamesButton, 0, 3);
        gridPane.add(bestScoresButton, 0, 4);
        gridPane.add(playersButton, 0, 5);
        gridPane.add(archiveButton, 0, 6);
        gridPane.add(difficultyButton, 0, 7);

        return gridPane;
    }
}
