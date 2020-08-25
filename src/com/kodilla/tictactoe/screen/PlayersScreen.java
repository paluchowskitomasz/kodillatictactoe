package com.kodilla.tictactoe.screen;

import com.kodilla.tictactoe.Save;
import com.kodilla.tictactoe.TicTacToe;
import com.kodilla.tictactoe.player.HumanPlayer;
import com.kodilla.tictactoe.player.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.stream.Collectors;

public class PlayersScreen extends AbstractScreen {

    public PlayersScreen(Save save) {
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

        gridPane.add(createText("Gracze"), 0, 0);
        gridPane.add(backButton, 0, 1);

        List<Player> humanPlayers = save.getPlayers().stream()
                .filter(player -> player instanceof HumanPlayer)
                .collect(Collectors.toList());

        for (int i = 0; i < humanPlayers.size(); i++) {
            Player player = humanPlayers.get(i);
            Button button = new Button(player.getName());
            button.setOnMouseClicked(event -> {
                save.setHumanPlayer(player);
                TicTacToe.setScreen("mainMenu");
            });
            gridPane.add(button, 0, 2 + i);
        }

        return gridPane;
    }
}
