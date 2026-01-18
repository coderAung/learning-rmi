package dev.aung.rmi.chat.commons.utils;

import dev.aung.rmi.chat.server.ChatNodeImpl;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class NodeByNameManager implements NodeManager {

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
    public void registerNode(ChatNodeImpl node) throws UnknownHostException, RemoteException, MalformedURLException {
        try {
            LocateRegistry.createRegistry(Constants.PORT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        var host = InetAddress.getLocalHost().getHostAddress();
        Naming.rebind("rmi://%s:%s/%s".formatted(host, Constants.PORT, node.getId()), node);
    }
}
