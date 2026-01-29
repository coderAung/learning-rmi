package dev.aung.app.handler;

import dev.aung.app.TicTacToeApp;
import dev.aung.app.handler.event.GameStartEvent;
import dev.aung.app.handler.event.OnMoveEvent;
import dev.aung.app.ui.controller.Game;
import dev.aung.app.ui.controller.MainFrame;
import dev.aung.app.ui.controller.Page;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GameEventListener {

    private final Game game;

    @EventListener
    void handle(OnMoveEvent ev) {
        Platform.runLater(() -> {
            game.setTurn(!ev.type().equals(game.getPlayerType()));
            game.refresh(ev.row(), ev.col(), ev.type());
        });
    }

    @EventListener
    void handle(GameStartEvent ev) {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(Game.class.getResource(Page.Game.getFxml()));
                loader.setControllerFactory(TicTacToeApp::getBean);
                Node node = loader.load();
                Game controller = loader.getController();
                controller.setPlayerType(ev.playerType());
                controller.setServer(ev.server());
                MainFrame.getController().showPage(node);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
