package dev.aung.app.service;

import dev.aung.app.game.PlayerType;
import dev.aung.app.rmi.GamePlayService;
import dev.aung.app.rmi.GameServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

@Service
@RequiredArgsConstructor
public class GameRmiService {

    private final GameServerImpl server;
    private final GamePlayService service;

    public void host(String ipAddress) {
        try {
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            server.join(PlayerType.You, ipAddress, service);
            Naming.rebind("rmi://%s:%s/server".formatted(ipAddress, 1099), server);
        } catch (RemoteException | MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    public void stop(String ipAddress) {
    }

    public void join(String ipAddress) {
        try {
            GameServer server = (GameServer) Naming.lookup("rmi://%s:%s/server".formatted(ipAddress, 1099));
            server.join(PlayerType.Opponent, ipAddress, service);
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
