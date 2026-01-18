package dev.aung.rmi.chat.commons.utils;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface NodeManager {
    ChatNode findNode() throws UnknownHostException, MalformedURLException, NotBoundException, RemoteException;

    void registerNode(ChatNode node) throws UnknownHostException, RemoteException, MalformedURLException;

    void addNode(ChatNode node) throws RemoteException;

    void removeNode(String id) throws RemoteException;

    boolean isConnected(String id) throws RemoteException;
}
