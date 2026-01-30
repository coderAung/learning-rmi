package dev.aung.app.rmi;

import dev.aung.app.handler.event.GameEndEvent;
import dev.aung.app.handler.event.GameStartEvent;
import dev.aung.app.handler.event.OnMoveEvent;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GamePlayService extends Remote {

    void startGame(GameStartEvent ev) throws RemoteException;

    void onMove(OnMoveEvent ev) throws RemoteException;

    void endGame(GameEndEvent ev) throws RemoteException;
}