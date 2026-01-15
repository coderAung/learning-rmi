package dev.aung.rmi.server.utils;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RmiObj {
    String name();
}
