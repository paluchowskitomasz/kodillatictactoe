package com.kodilla.tictactoe.screen;

import com.kodilla.tictactoe.Game;
import com.kodilla.tictactoe.Save;
import com.kodilla.tictactoe.TicTacToe;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.stream.Collectors;

public class SavedGamesScreen extends AbstractScreen {

    public SavedGamesScreen(Save save) {
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

        gridPane.add(createText("Wczytaj zapisaną grę"), 0, 0);
        gridPane.add(backButton, 0, 1);

        List<Game> nonFinishedGames = save.getGames().stream()
                .filter(game -> game.isStarted())
                .collect(Collectors.toList());

        for (int i = 0; i < nonFinishedGames.size(); i++) {
            Game game = nonFinishedGames.get(i);
            if (game.isStarted()) {
                Button loadGameButton = new Button(game.printResults());
                loadGameButton.setOnMouseClicked(event -> {
                    TicTacToe.setGameScreen(game);
                });
                gridPane.add(loadGameButton, 0, 2 + i);
            }
        }

        return gridPane;
    }
}
