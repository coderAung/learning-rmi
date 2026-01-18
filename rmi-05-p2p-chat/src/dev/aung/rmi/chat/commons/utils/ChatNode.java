package dev.aung.rmi.chat.commons.utils;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatNode extends Remote {

    String getId() throws RemoteException;

    void onMessage(ChatMessageForm message) throws RemoteException;
}
