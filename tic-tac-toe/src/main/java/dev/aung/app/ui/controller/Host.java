package dev.aung.app.ui.controller;

import dev.aung.app.TicTacToeApp;
import dev.aung.app.service.GameRmiService;
import dev.aung.app.utils.RMIUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class Host {

    @FXML
    private Label ipAddress;

    private final GameRmiService rmiService;

    @FXML
    void initialize() {
        String ipAddress = RMIUtils.getLocalIpAddress();
        rmiService.host(ipAddress);
        this.ipAddress.setText(ipAddress);
    }

    @FXML
    void cancel() {
        try {

            rmiService.stop(ipAddress.getText());

            FXMLLoader loader = new FXMLLoader(Home.class.getResource(Page.Home.getFxml()));
            loader.setControllerFactory(TicTacToeApp::getBean);
            MainFrame.getController().showPage(loader.load());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
