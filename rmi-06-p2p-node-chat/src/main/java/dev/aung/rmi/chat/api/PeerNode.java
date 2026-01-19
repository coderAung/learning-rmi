package dev.aung.rmi.chat.api;

import dev.aung.rmi.chat.api.dto.MessageForm;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PeerNode extends Remote {

    String getId() throws RemoteException;

    void connect(PeerNode node) throws RemoteException;

    boolean isConnected(String nodeId) throws RemoteException;

    void disconnect(String nodeId) throws RemoteException;

    void onMessage(MessageForm form) throws RemoteException;

    PeerNode getNodeById(String nodeId) throws RemoteException;
}
