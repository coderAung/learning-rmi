package dev.aung.rmi.chat.commons.utils;

import dev.aung.rmi.chat.server.ChatNodeImpl;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface NodeManager {
    ChatNode findNode() throws UnknownHostException, MalformedURLException, NotBoundException, RemoteException;

    void registerNode(ChatNodeImpl node) throws UnknownHostException, RemoteException, MalformedURLException;
}
