package dev.aung.rmi.chat.app.core;

import dev.aung.rmi.chat.api.PeerNode;
import dev.aung.rmi.chat.api.dto.MessageForm;
import dev.aung.rmi.chat.api.RMIConsumer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class PeerNodeImpl extends UnicastRemoteObject implements PeerNode {

    private final String id;
    private final Map<String, PeerNode> nodes;
    private final RMIConsumer<MessageForm> func;

    public PeerNodeImpl(String id, RMIConsumer<MessageForm> func) throws RemoteException {
        super();
        this.id = id;
        this.func = func;
        this.nodes = new HashMap<>();
    }

    @Override
    public String getId() throws RemoteException {
        return this.id;
    }

    @Override
    public void connect(PeerNode node) throws RemoteException {
        this.nodes.put(node.getId(), node);
    }

    @Override
    public boolean isConnected(String nodeId) throws RemoteException {
        return this.nodes.containsKey(nodeId);
    }

    @Override
    public void disconnect(String nodeId) throws RemoteException {
        this.nodes.remove(nodeId);
    }

    @Override
    public void onMessage(MessageForm form) throws RemoteException {
        if(isConnected(form.from()) && form.from().equals(P2PGlobal.currentNodeId)) {
            this.func.accept(form);
        } else {
            System.out.printf("%n%nMessage request from %s : '%s'%n", form.from(), form.message());
            var output = P2PGlobal.operation.equals(P2PGlobal.Operation.Message) ? "Continue chatting with %s or /exit to connect with %s ...%n%n".formatted(P2PGlobal.currentNodeId, form.from()) : "Enter node id to connect : ";
            System.out.print(output);
        }
    }

    @Override
    public PeerNode getNodeById(String nodeId) throws RemoteException {
        return this.nodes.get(nodeId);
    }
}
