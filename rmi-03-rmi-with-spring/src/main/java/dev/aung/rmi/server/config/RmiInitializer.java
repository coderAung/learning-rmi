package dev.aung.rmi.server.config;

import dev.aung.rmi.server.utils.RmiObj;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

@Configuration
@PropertySource("classpath:/host.properties")
public class RmiInitializer {

    @Value("${rmi.host.url}")
    private String hostUrl;

    @Value("${rmi.host.port}")
    private int port;

    private final ConfigurableApplicationContext ctx;

    public RmiInitializer(ConfigurableApplicationContext ctx) {
        this.ctx = ctx;
    }

    @PostConstruct
    void bindRmiObjects() throws RemoteException {

        System.out.println("""
                ================================================
                |
                | Binding RMI Objects
                |
                ================================================
                """);

        LocateRegistry.createRegistry(this.port);

        this.ctx.getBeansWithAnnotation(RmiObj.class).forEach((k, v) -> {
            if(!(v instanceof Remote r)) {
                throw new IllegalArgumentException("RmiObj annotated class must be on Remote object.");
            }
            try {
                Naming.rebind("%s/%s".formatted(this.hostUrl, v.getClass().getDeclaredAnnotation(RmiObj.class).name()), r);
            } catch (RemoteException | MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @PreDestroy
    void unbindRmiObjects() {
        System.out.println("""
                ================================================
                |
                | Unbinding RMI Objects
                |
                ================================================
                """);
        this.ctx.getBeansWithAnnotation(RmiObj.class).forEach((k, v) -> {
            if(!(v instanceof Remote)) {
                throw new IllegalArgumentException("RmiObj annotated class must be on Remote object.");
            }
            try {
                Naming.unbind("%s/%s".formatted(this.hostUrl, v.getClass().getDeclaredAnnotation(RmiObj.class).name()));
            } catch (RemoteException | MalformedURLException | NotBoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
