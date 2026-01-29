package dev.aung.app.ui.controller;

public enum Page {
    Home, Host, Join, Game;

    public String getFxml() {
        return "%s.fxml".formatted(name());
    }
}
