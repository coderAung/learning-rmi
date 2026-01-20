package dev.aung.rmi.chat.app;

import dev.aung.rmi.chat.api.PeerNode;
import dev.aung.rmi.chat.api.dto.MessageForm;
import dev.aung.rmi.chat.app.core.P2PClient;
import dev.aung.rmi.chat.app.core.P2PGlobal;
import dev.aung.rmi.chat.app.core.PeerNodeImpl;
import dev.aung.rmi.chat.app.utils.Constants;
import dev.aung.rmi.chat.app.utils.Inputs;
import dev.aung.rmi.chat.api.RMIConsumer;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import static dev.aung.rmi.chat.app.utils.NodeUtils.isNodeExist;

public class P2PChatMain {

    public static void main(String[] args) throws UnknownHostException {
        try {
            LocateRegistry.createRegistry(Constants.PORT);
        } catch (RemoteException e) {
            System.out.println("Network is already ready ...");
        }

        try {
            var node = createNode(form -> {
                System.out.printf("\t%s : %s%n", form.from(), form.message());
                P2PGlobal.operation = P2PGlobal.Operation.Message;
            });
            Naming.rebind("rmi://%s:%s/%s".formatted(Constants.getHost(), Constants.PORT, node.getId()), node);

            new P2PClient(node).run();

        } catch (RemoteException | MalformedURLException e) {
            throw new RuntimeException();
        }
    }

    private static PeerNode createNode(RMIConsumer<MessageForm> func) throws RemoteException, UnknownHostException {
        var input = Inputs.getString("Enter node id : ");
        if (input.equalsIgnoreCase("/exit")) {
            System.exit(0);
        }
        if(isNodeExist(input, Constants.getHost())) {
            throw new IllegalArgumentException("Id : %s already exists.".formatted(input));
        }

        return new PeerNodeImpl(
                input,
                func
        );
    }

}
