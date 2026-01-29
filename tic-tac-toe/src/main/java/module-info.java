module dev.aung.app.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires static lombok;
    requires java.rmi;

    exports dev.aung.app;
    exports dev.aung.app.rmi;
    exports dev.aung.app.game;
    exports dev.aung.app.handler;
    exports dev.aung.app.handler.event;
    exports dev.aung.app.ui.controller;

    opens dev.aung.app to spring.core, spring.beans, spring.context;
    opens dev.aung.app.service to spring.core, spring.beans, spring.context;
    opens dev.aung.app.handler to spring.core, spring.beans, spring.context;
    opens dev.aung.app.ui.controller to spring.core, spring.beans, spring.context,
            javafx.controls, javafx.fxml;
}