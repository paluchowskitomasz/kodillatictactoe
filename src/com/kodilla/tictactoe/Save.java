package com.kodilla.tictactoe;

import com.kodilla.tictactoe.player.AIPlayer;
import com.kodilla.tictactoe.player.HumanPlayer;
import com.kodilla.tictactoe.player.Player;
import com.kodilla.tictactoe.player.SmartAIPlayer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Save implements Serializable {

    public static final long serialVersionUID = -4676605060238924992L;

    private Player humanPlayer;
    private Player aiPlayer;
    private List<Game> games;
    private List<Player> players;

    public Save() {
        this.humanPlayer = new HumanPlayer("Graczu");
        this.aiPlayer = new AIPlayer("AI");
        this.games = new LinkedList<>();
        this.players = new LinkedList<>();
        this.players.add(this.humanPlayer);
        this.players.add(new HumanPlayer("Gracz 2"));
        this.players.add(new HumanPlayer("Gracz 3"));
        this.players.add(this.aiPlayer);
        this.players.add(new SmartAIPlayer("SmartAI"));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setHumanPlayer(Player humanPlayer) {
        this.humanPlayer = humanPlayer;
    }

    public Player getHumanPlayer() {
        return humanPlayer;
    }

    public Player getAIPlayer() {
        return aiPlayer;
    }

    public void save() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("saveFile"));
            output.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAIPlayer(Player player) {
        this.aiPlayer = player;
    }
}
