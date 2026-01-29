package dev.aung.app.ui.controller;

import dev.aung.app.TicTacToeApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainFrame {

    @FXML
    private StackPane pane;

    private static MainFrame controller;

    public static MainFrame getController() {
        if (controller == null) {
            throw new RuntimeException();
        }
        return controller;
    }

    public MainFrame() {
        controller = this;
    }

    @FXML
    void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Page.Home.getFxml()));
            loader.setControllerFactory(TicTacToeApp::getBean);
            Node node = loader.load();
            pane.getChildren().clear();
            pane.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showPage(Node node) {
        pane.getChildren().clear();
        pane.getChildren().add(node);
    }

    public void pageHandler(Button btn) {
        btn.setOnAction(ev -> {
            try {
                Page page = Page.valueOf(btn.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource(page.getFxml()));
                loader.setControllerFactory(TicTacToeApp::getBean);
                Node node = loader.load();
                pane.getChildren().clear();
                pane.getChildren().add(node);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
