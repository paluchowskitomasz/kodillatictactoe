package com.kodilla.tictactoe;

public enum Result {
    STARTED("W trakcie"),
    CIRCLE_WON("Kółko wygrało"),
    CROSS_WON("Krzyżyk wygrał"),
    DRAW("Remis");

    private String text;

    Result(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
