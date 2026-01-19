package dev.aung.rmi.chat.app.core;

import dev.aung.rmi.chat.api.PeerNode;
import dev.aung.rmi.chat.api.dto.MessageForm;
import dev.aung.rmi.chat.app.utils.Inputs;
import dev.aung.rmi.chat.app.utils.NodeUtils;

import java.net.UnknownHostException;
import java.rmi.RemoteException;

import static dev.aung.rmi.chat.app.core.P2PGlobal.currentNodeId;
import static dev.aung.rmi.chat.app.core.P2PGlobal.operation;
import static dev.aung.rmi.chat.app.utils.NodeUtils.lookupNode;

public class P2PClient {

    private final PeerNode node;

    public P2PClient(PeerNode node) {
        this.node = node;
    }

    public void run() {
        while (true) {
            try {
                var input = getString(operation);
                if (input.equalsIgnoreCase("/exit")) {
                    if (operation.equals(P2PGlobal.Operation.Message)) {
                        currentNodeId = null;
                        operation = P2PGlobal.Operation.Connect;
                        continue;
                    } else {
                        var currentNode = this.node.getNodeById(currentNodeId);
                        if(currentNode != null) {
                            currentNode.disconnect(node.getId());
                            this.node.disconnect(currentNodeId);
                        }
                        NodeUtils.unbindNode(node);
                        System.exit(0);
                    }
                }
                if (operation.equals(P2PGlobal.Operation.Connect)) {
                    if (!NodeUtils.isNodeExist(input)) {
                        System.out.printf("Node with id : %s is not exist.%n", input);
                    } else {
                        this.node.connect(lookupNode(input));
                        currentNodeId = input;
                        System.out.println("===============================");
                        System.out.println("Current Chat : " + currentNodeId);
                        System.out.println("===============================");
                        operation = P2PGlobal.Operation.Message;
                    }
                } else {
                    var form = new MessageForm(this.node.getId(), currentNodeId, input);
                    this.node.getNodeById(currentNodeId).onMessage(form);
                }
            } catch (RemoteException | UnknownHostException e) {
                System.out.println("Something went wrong.");
                operation = P2PGlobal.Operation.Connect;
            }
        }
    }

    public String getString(P2PGlobal.Operation operation) throws RemoteException {
        return Inputs.getString(operation.equals(P2PGlobal.Operation.Message) ? "" : "Enter id to connect : ");
    }
}
