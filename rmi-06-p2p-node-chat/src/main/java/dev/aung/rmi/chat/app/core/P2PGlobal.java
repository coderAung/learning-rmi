package dev.aung.rmi.chat.app.core;

import java.io.Serial;
import java.io.Serializable;

public class P2PGlobal {

    public static String currentNodeId;
    public static Operation operation = Operation.Connect;

    public enum Operation implements Serializable {
        Connect, Message;
        @Serial
        private static final long serialVersionUID = 1L;
    }

}
