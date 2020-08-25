package com.kodilla.tictactoe;

import com.kodilla.tictactoe.player.AIPlayer;
import com.kodilla.tictactoe.player.Player;
import com.kodilla.tictactoe.screen.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class TicTacToe extends Application {

    public static Player aiPlayer = new AIPlayer("Robot AI");

    public static Map<String, AbstractScreen> screens = new HashMap<>();
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        File saveFile = new File("saveFile");

        Save save;
        if (saveFile.exists()) {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(saveFile));
            save = (Save) input.readObject();
        } else {
            save = new Save();
            save.save();
        }

        screens.put("mainMenu", new MainMenuScreen(save));
        screens.put("newGame", new NewGameScreen(save));
        screens.put("archive", new ArchiveScreen(save));
        screens.put("savedGames", new SavedGamesScreen(save));
        screens.put("scores", new ScoresScreen(save));
        screens.put("players", new PlayersScreen(save));
        screens.put("difficulty", new DifficultyScreen(save));

        GameScreen gameScreen = new GameScreen(save);
        screens.put("game", gameScreen);

        scene = new Scene(screens.get("mainMenu").createGridPane(), 900, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Kółko i Krzyżyk");
        primaryStage.show();
    }

    public static void setScreen(String screenName) {
        AbstractScreen screen = screens.get(screenName);
        scene.setRoot(screen.createGridPane());
    }

    public static void setGameScreen(Game game) {
        GameScreen screen = (GameScreen) screens.get("game");
        screen.refresh(game);
        scene.setRoot(screen.createGridPane());
    }
}
