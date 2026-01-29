package dev.aung.app.ui.controller;

import dev.aung.app.service.GameRmiService;
import dev.aung.app.utils.AppBusinessException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.rmi.UnknownHostException;

@Controller
@RequiredArgsConstructor
public class Join {

    @FXML
    private TextField ipAddress;
    @FXML
    private Label message;
    @FXML
    private Button cancel;

    private final GameRmiService service;

    @FXML
    void initialize() {
        MainFrame.getController().pageHandler(cancel);
    }

    @FXML
    void join() {
        try {
            if (!StringUtils.hasLength(ipAddress.getText())) {
                throw new AppBusinessException("IP address cannot be empty.");
            }
            service.join(ipAddress.getText());
        } catch (AppBusinessException e) {
            message.setText(e.getMessage());
        } catch (RuntimeException e) {
            if(e.getCause() instanceof UnknownHostException ue) {
                message.setText(e.getMessage());
            }
        }
    }
}
