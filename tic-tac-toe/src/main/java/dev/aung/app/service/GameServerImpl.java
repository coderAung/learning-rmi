package dev.aung.app.service;

import dev.aung.app.game.*;
import dev.aung.app.handler.event.GameEndEvent;
import dev.aung.app.handler.event.GameStartEvent;
import dev.aung.app.handler.event.OnMoveEvent;
import dev.aung.app.rmi.GamePlayService;
import dev.aung.app.rmi.GameServer;
import dev.aung.app.utils.AppBusinessException;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class GameServerImpl extends UnicastRemoteObject implements GameServer {

    private final GameLogic logic;
    private final HashMap<GamePlayer, GamePlayService> map;

    public GameServerImpl() throws RemoteException {
        super();
        map = new HashMap<>();
        logic = new GameLogic(new ArrayList<>(9));
    }

    @Override
    public void join(PlayerType playerType, String ipAddress, GamePlayService service) throws RemoteException {
        if(map.size() == 2) {
            throw new AppBusinessException("Game room is full.");
        }
        GamePlayer player = new GamePlayer(playerType, ipAddress);
        if(map.containsKey(player)) {
            throw new AppBusinessException("Invalid player");
        }
        map.put(player, service);
        if(map.size() == 2) {
            map.forEach((p, s) -> {
                try {
                    s.startGame(new GameStartEvent(p.playerType(), p.ipAddress(), this));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    @Override
    public void onMove(PlayerType playerType, int row, int col) throws RemoteException {
        logic.move(new GameMove(playerType, new TicTacToeCell(row, col)));
        PlayerType winner = logic.getWinner();

        map.forEach((p,s) -> {
            try {
                s.onMove(new OnMoveEvent(row, col, playerType));
                if (winner != null) {
                    s.endGame(new GameEndEvent(winner, p.ipAddress()));
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
