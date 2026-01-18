package dev.aung.rmi.chat.commons.utils;


import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

public class NodeByNameManager implements NodeManager {

    private final Map<String, ChatNode> nodes;

    public NodeByNameManager() {
        this.nodes = new HashMap<>();
    }

    @Override
    public void addNode(ChatNode node) throws RemoteException {
        this.nodes.put(node.getId(), node);
    }

    @Override
    public void removeNode(String id) throws RemoteException {
        this.nodes.remove(id);
    }

    @Override
    public ChatNode findNode() throws UnknownHostException, MalformedURLException, NotBoundException, RemoteException {
        System.out.println("Enter /exit to exit.");
        var input = Inputs.getString("Enter name to connect : ");
        if(input.equalsIgnoreCase("/exit")) {
            System.exit(0);
        }
        var host = InetAddress.getLocalHost().getHostAddress();
        return (ChatNode) Naming.lookup("rmi://%s:%s/%s".formatted(host, Constants.PORT, input));
    }

    @Override
    public void registerNode(ChatNode node) throws UnknownHostException, RemoteException, MalformedURLException {
        try {
            LocateRegistry.createRegistry(Constants.PORT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        var host = InetAddress.getLocalHost().getHostAddress();
        Naming.rebind("rmi://%s:%s/%s".formatted(host, Constants.PORT, node.getId()), node);
    }

    @Override
    public boolean isConnected(String id) {
        return this.nodes.containsKey(id);
    }
}

