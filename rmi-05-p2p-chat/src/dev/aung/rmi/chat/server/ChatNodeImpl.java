package dev.aung.rmi.chat.server;

import dev.aung.rmi.chat.commons.utils.ChatMessageForm;
import dev.aung.rmi.chat.commons.utils.ChatNode;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatNodeImpl extends UnicastRemoteObject implements ChatNode {

    private final String id;

    public ChatNodeImpl(String id) throws RemoteException {
        super();
        this.id = id;
    }
    @Override
    public String getId() throws RemoteException {
        return this.id;
    }

    @Override
    public void onMessage(ChatMessageForm message) throws RemoteException {
        System.out.printf("%n%s > %s %n", message.getFromId(), message.getMessage());
        System.out.print("You > ");

    }

}
