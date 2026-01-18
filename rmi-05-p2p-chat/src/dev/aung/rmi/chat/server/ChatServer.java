package dev.aung.rmi.chat.server;

import dev.aung.rmi.chat.commons.utils.NodeManager;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

public class ChatServer {
    public static void run(String name, NodeManager manager) throws RemoteException {
        try {
            var node = new ChatNodeImpl(name, form -> {
                try {
                    if(!manager.isConnected(form.getFromId())) {
                        System.out.println("\n" + form.getFromId() + " is trying to send message.\n");
                    } else {
                        System.out.printf("%n%s > %s %n", form.getFromId(), form.getMessage());
                        System.out.print("You > ");
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
            manager.registerNode(node);
        } catch (UnknownHostException | MalformedURLException e) {
            System.out.println("Something went wrong.");
            System.out.println(e.getMessage());
        }
    }
}
