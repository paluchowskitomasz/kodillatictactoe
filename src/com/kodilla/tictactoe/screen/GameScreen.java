package com.kodilla.tictactoe.screen;

import com.kodilla.tictactoe.Game;
import com.kodilla.tictactoe.Save;
import com.kodilla.tictactoe.Sign;
import com.kodilla.tictactoe.TicTacToe;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class GameScreen extends AbstractScreen {

    private GridPane gridPane = new GridPane();

    public GameScreen(Save save) {
        super(save);
    }

    @Override
    public GridPane createGridPane() {
        return gridPane;
    }

    public void refresh(Game game) {
        gridPane.getChildren().clear();
        gridPane.setBackground(createBackground());
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        drawSign(game, 0, 0);
        drawSign(game, 0, 1);
        drawSign(game, 0, 2);

        drawSign(game, 1, 0);
        drawSign(game, 1, 1);
        drawSign(game, 1, 2);

        drawSign(game, 2, 0);
        drawSign(game, 2, 1);
        drawSign(game, 2, 2);

        Button newGameButton = new Button("Nowa gra");
        newGameButton.setOnMouseClicked(event -> {
            TicTacToe.setScreen("newGame");
        });

        Button backButton = new Button("Powrót");
        backButton.setOnMouseClicked(event -> {
            TicTacToe.setScreen("mainMenu");
        });

        gridPane.add(createText(
                "O - " + game.getCirclePlayer().getName() + "\n" +
                "X - " + game.getCrossPlayer().getName() +"\n" +
                "Stan gry: " + game.getResult().getText())
                , 3, 0);
        gridPane.add(newGameButton, 3, 1);
        gridPane.add(backButton, 3, 2);
    }

    private void drawSign(Game game, int column, int row) {
        Sign sign = game.getState(column, row);

        if (game.getVictoriousList().contains(new Pair<>(column, row))) {
            if (sign == Sign.CROSS) {
                gridPane.add(createGreenCrossImage(), column, row);
            } else if (sign == Sign.CIRCLE) {
                gridPane.add(createGreenCircleImage(), column, row);
            }
        } else {
            if (sign == Sign.CROSS) {
                gridPane.add(createCrossImage(), column, row);
            } else if (sign == Sign.CIRCLE) {
                gridPane.add(createCircleImage(), column, row);
            } else {
                gridPane.add(createEmptyImage(game, column, row), column, row);
            }
        }
    }

    private ImageView createEmptyImage(Game game, int column, int row) {
        ImageView circleImage = new ImageView("file:resources/empty.png");
        circleImage.setFitWidth(100);
        circleImage.setFitHeight(100);
        circleImage.setBlendMode(BlendMode.OVERLAY);
        circleImage.setOnMouseClicked(event -> {
            game.put(column, row, game.getTurn());
            TicTacToe.setGameScreen(game);
            game.next(save);

        });
        return circleImage;
    }

    private ImageView createCrossImage() {
        ImageView circleImage = new ImageView("file:resources/x.jpg");
        circleImage.setFitWidth(100);
        circleImage.setFitHeight(100);
        circleImage.setBlendMode(BlendMode.OVERLAY);
        return circleImage;
    }

    private ImageView createCircleImage() {
        ImageView circleImage = new ImageView("file:resources/o.jpg");
        circleImage.setFitWidth(100);
        circleImage.setFitHeight(100);
        circleImage.setBlendMode(BlendMode.OVERLAY);
        return circleImage;
    }

    private ImageView createGreenCrossImage() {
        ImageView circleImage = new ImageView("file:resources/x.jpg");
        circleImage.setFitWidth(100);
        circleImage.setFitHeight(100);
        circleImage.setBlendMode(BlendMode.GREEN);
        return circleImage;
    }

    private ImageView createGreenCircleImage() {
        ImageView circleImage = new ImageView("file:resources/o.jpg");
        circleImage.setFitWidth(100);
        circleImage.setFitHeight(100);
        circleImage.setBlendMode(BlendMode.GREEN);
        return circleImage;
    }
}
