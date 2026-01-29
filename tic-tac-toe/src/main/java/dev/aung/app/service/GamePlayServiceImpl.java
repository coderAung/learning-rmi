package dev.aung.app.service;

import dev.aung.app.handler.event.GameStartEvent;
import dev.aung.app.handler.event.OnMoveEvent;
import dev.aung.app.rmi.GamePlayService;
import dev.aung.app.rmi.GameServer;
import dev.aung.app.ui.controller.Game;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Service
public class GamePlayServiceImpl extends UnicastRemoteObject implements GamePlayService {

    private final ApplicationEventPublisher publisher;

    public GamePlayServiceImpl(ApplicationEventPublisher publisher) throws RemoteException {
        super();
        this.publisher = publisher;
    }

    @Override
    public void startGame(GameStartEvent ev) throws RemoteException {
        publisher.publishEvent(ev);
    }

    @Override
    public void onMove(OnMoveEvent ev) throws RemoteException {
        publisher.publishEvent(ev);
    }
}
