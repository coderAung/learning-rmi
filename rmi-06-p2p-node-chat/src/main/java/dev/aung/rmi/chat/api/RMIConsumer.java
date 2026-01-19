package dev.aung.rmi.chat.api;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIConsumer<T> extends Remote, Serializable {
    void accept(T t) throws RemoteException;

    static <T> RMIConsumer<T> of(RMIConsumer<T> consumer) {
        return consumer;
    }

}
