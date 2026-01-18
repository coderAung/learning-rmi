package dev.aung.rmi.chat.server;

import dev.aung.rmi.chat.commons.utils.ChatMessageForm;
import dev.aung.rmi.chat.commons.utils.ChatNode;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Consumer;

public class ChatNodeImpl extends UnicastRemoteObject implements ChatNode {

    private final String id;
    private Consumer<ChatMessageForm> func;

    public ChatNodeImpl(String id, Consumer<ChatMessageForm> func) throws RemoteException {
        super();
        this.id = id;
        this.func = func;
    }

    @Override
    public String getId() throws RemoteException {
        return this.id;
    }

    @Override
    public void onMessage(ChatMessageForm message) throws RemoteException {
        this.func.accept(message);
    }
}
