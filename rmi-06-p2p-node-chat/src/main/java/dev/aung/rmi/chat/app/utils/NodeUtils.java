package dev.aung.rmi.chat.app.utils;

import dev.aung.rmi.chat.api.PeerNode;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;

public abstract class NodeUtils {

    public static PeerNode lookupNode(String nodeId) throws UnknownHostException, RemoteException {
        try {
            return (PeerNode) Naming.lookup("rmi://%s:%s/%s".formatted(Constants.getHost(), Constants.PORT, nodeId));
        } catch (NotBoundException | MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean isNodeExist(String nodeId) {
        try {
            var url = "//%s".formatted(Constants.getHost());
            var names = Naming.list("rmi:%s".formatted(url));
//            for(var n : names) System.out.println(n);
            var count = Arrays.stream(names).filter(name -> name.equals("%s:%s/%s".formatted(url, Constants.PORT, nodeId))).count();
            if (count > 0) {
                return true;
            }
        } catch (RemoteException | MalformedURLException | UnknownHostException e) {
            System.out.println("Connection timeout.");
        }
        return false;
    }

    public static void unbindNode(PeerNode node) throws RemoteException, UnknownHostException {
        try {
            Naming.unbind("rmi://%s:%s/%s".formatted(Constants.getHost(), Constants.PORT, node.getId()));
        } catch (NotBoundException | MalformedURLException e) {
            System.out.println("Connection timeout.");
        }
    }
}
