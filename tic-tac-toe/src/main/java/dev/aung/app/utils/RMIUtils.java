package dev.aung.app.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public interface RMIUtils {

    static String getLocalIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
