package dev.aung.app.rmi;

import dev.aung.app.game.PlayerType;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameServer extends Remote {

    void join(PlayerType playerType, String ipAddress, GamePlayService service) throws RemoteException;

    void onMove(PlayerType playerType, int row, int col) throws RemoteException;
}
