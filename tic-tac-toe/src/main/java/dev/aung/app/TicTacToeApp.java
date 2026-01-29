package dev.aung.app;

import dev.aung.app.ui.controller.MainFrame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;

@SpringBootApplication
public class TicTacToeApp extends Application {

    private static ConfigurableApplicationContext context;

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(Class<T> clz) {
        return context.getBean(clz);
    }

    @Override
    public void stop() {
        if(context != null) {
            context.close();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent view = FXMLLoader.load(Objects.requireNonNull(MainFrame.class.getResource("MainFrame.fxml")));
        stage.setScene(new Scene(view));
        stage.show();
    }

    public static void main(String[] args) {
        context = SpringApplication.run(TicTacToeApp.class, args);
        launch(args);
    }
}
