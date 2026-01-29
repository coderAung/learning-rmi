package dev.aung.app.ui.controller;


import dev.aung.app.game.PlayerType;
import dev.aung.app.game.TicTacToeCell;
import dev.aung.app.rmi.GameServer;
import dev.aung.app.utils.AppBusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Game {

    @FXML
    private GridPane gameBoard;

    @Setter
    @Getter
    private PlayerType playerType;
    @Setter
    private GameServer server;
    @Setter
    private boolean isTurn = true;

    private final List<Button> cells = new ArrayList<>();

    @FXML
    void initialize() {
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setUserData(new TicTacToeCell(row, col));
                button.getStyleClass().add("cell");
                button.setOnAction(this::addCellActionListener);
                cells.add(button);
                gameBoard.add(button, row, col);
            }
        }
    }

    private void addCellActionListener(ActionEvent ev) {
        if(isTurn) {
            try {
                Button button = (Button) ev.getSource();
                TicTacToeCell cell = (TicTacToeCell) button.getUserData();
                server.onMove(playerType, cell.row(), cell.col());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void refresh(int row, int col, PlayerType type) {
        Button btn = cells.stream().filter(a -> cellFilter(a, row, col)).findFirst().orElseThrow(() -> new AppBusinessException("Invalid cell input."));
        btn.setText(type.symbol());
        btn.setOnAction(null);
    }

    private boolean cellFilter(Button btn, int row, int col) {
        var cell = (TicTacToeCell) btn.getUserData();
        return cell.row() == row && cell.col() == col;
    }
}
