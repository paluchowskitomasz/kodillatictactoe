package com.kodilla.tictactoe.screen;

import com.kodilla.tictactoe.Save;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public abstract class AbstractScreen {

    protected Save save;

    public AbstractScreen(Save save) {
        this.save = save;
    }

    abstract public GridPane createGridPane();

    protected Background createBackground() {
        Image starsImage = new Image("file:resources/gwiazdy.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(starsImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);
    }

    protected Text createText(String string) {
        Text text = new Text(string);
        text.setFill(Color.WHITE);
        return text;
    }
}
