package dev.aung.rmi.chat.client;

import dev.aung.rmi.chat.commons.utils.ChatMessageForm;
import dev.aung.rmi.chat.commons.utils.Inputs;
import dev.aung.rmi.chat.commons.utils.NodeManager;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ChatClient {
    public static void run(String name, NodeManager manager) {
        System.out.println("=============================");
        System.out.println("Welcome to P2P Chat.");
        System.out.println("=============================");

        do {
            try {
                var node = manager.findNode();
                manager.addNode(node);

                System.out.println("++++++++++++++++++++++++++++++++++");
                System.out.println("You are talking with " + node.getId());
                System.out.println("++++++++++++++++++++++++++++++++++");

                do {
                    var message = Inputs.getString("You > ");
                    if(message.equalsIgnoreCase("/exit")) {
                        System.exit(0);
                    }
                    try {
                        node.onMessage(new ChatMessageForm(name, node.getId(), message));
                    } catch (RemoteException e) {
                        System.out.println(e.getMessage());
                    }
                } while (true);

            } catch (UnknownHostException e) {
                System.out.println(e.getMessage());
            } catch (MalformedURLException | RemoteException e) {
                System.out.println("Something went wrong.");
            } catch (NotBoundException e) {
                System.out.println("Node is not found.");
            }

        } while (true);
    }
}

