package dev.aung.rmi.utils.impl;

import dev.aung.rmi.utils.RMIFunction;
import dev.aung.rmi.utils.RMIGateWay;
import kotlin.TypeCastException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class RMIGateWayImpl extends UnicastRemoteObject implements RMIGateWay {

    private final Map<String, Object> container;

    public RMIGateWayImpl(Map<String, Object> container) throws RemoteException {
        super();
        this.container = container;
    }

    @Override
    public <T, R> R execute(Class<T> clz, RMIFunction<T, R> func) throws RemoteException {
        try {
            var obj = (T) container.get(clz.getSimpleName());
            return func.apply(obj);
        } catch (TypeCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
