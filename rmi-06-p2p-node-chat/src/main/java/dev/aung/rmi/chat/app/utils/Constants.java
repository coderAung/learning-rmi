package dev.aung.rmi.chat.app.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class Constants {

    public static final int PORT = 1099;

    public static String getHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
