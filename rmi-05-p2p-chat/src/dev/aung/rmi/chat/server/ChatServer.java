package dev.aung.rmi.chat.server;

import dev.aung.rmi.chat.commons.utils.Constants;
import dev.aung.rmi.chat.commons.utils.NodeManager;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ChatServer {
    public static void run(String name, NodeManager manager) throws RemoteException {
        try {
            var node = new ChatNodeImpl(name);
            manager.registerNode(node);
        } catch (UnknownHostException | MalformedURLException e) {
            System.out.println("Something went wrong.");
            System.out.println(e.getMessage());
        }
    }
}
