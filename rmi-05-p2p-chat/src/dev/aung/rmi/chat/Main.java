package dev.aung.rmi.chat;


import dev.aung.rmi.chat.client.ChatClient;
import dev.aung.rmi.chat.commons.utils.Inputs;
import dev.aung.rmi.chat.commons.utils.NodeByNameManager;
import dev.aung.rmi.chat.server.ChatServer;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException {
        var name = Inputs.getString("Enter you name : ");
        var manager = new NodeByNameManager();
        ChatServer.run(name, manager);
        ChatClient.run(name, manager);
    }
}
