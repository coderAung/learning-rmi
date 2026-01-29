package dev.aung.app.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

@Controller
public class Home {

    @FXML
    private Button host;
    @FXML
    private Button join;
    @FXML
    private Label message;

    @FXML
    void initialize() {
        try {
            MainFrame.getController().pageHandler(host);
            MainFrame.getController().pageHandler(join);
        } catch (RuntimeException e) {
            message.setText("Something went wrong.");
        }
    }

}
