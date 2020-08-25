package com.kodilla.tictactoe.screen;

import com.kodilla.tictactoe.Save;
import com.kodilla.tictactoe.TicTacToe;
import com.kodilla.tictactoe.player.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoresScreen extends AbstractScreen {

    public ScoresScreen(Save save) {
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

        gridPane.add(createText("Najlepsze wyniki"), 0, 0);
        gridPane.add(backButton, 0, 1);

        List<Player> sortedPlayers = save.getPlayers().stream()
                .sorted(Comparator.comparing(Player::getPoints).reversed())
                .collect(Collectors.toList());

        for (int i = 0; i < sortedPlayers.size(); i++) {
            Player player = sortedPlayers.get(i);
            gridPane.add(createText(player.getName() + ": " + player.getPoints()), 0, 2 + i);
        }

        return gridPane;
    }
}
