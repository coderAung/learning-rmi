package dev.aung.rmi.client.utils;

import dev.aung.rmi.api.utils.Constants;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.function.Function;

public abstract class RmiUtils {

//    private RmiUtils() {}
//
//    private static RmiUtils utils;
//
//    public static RmiUtils getInstance() {
//        if (utils == null) {
//            utils = new RmiUtils();
//        }
//        return utils;
//    }

    public static  <T extends Remote> T lookup(Class<T> clz) {
        try {
            return (T) Naming.lookup("%s/%s".formatted(Constants.RMI_URL, clz.getSimpleName()));
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static  <T extends Remote, R> R safecall(Class<T> clz, RMIFunction<T, R> func) {
        try {
            var t = (T) Naming.lookup("%s/%s".formatted(Constants.RMI_URL, clz.getSimpleName()));
            try {
                return func.apply(t);
            } catch (RemoteException e) {
                e.printStackTrace();
                throw new RuntimeException("RMI operation fails.");
            }
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
