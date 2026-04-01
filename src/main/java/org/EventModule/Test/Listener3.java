package org.EventModule.Test;

import org.EventModule.ListenerScanner.Subscribe;

public class Listener3 {
@Subscribe
    public void handle(UserLogin event) {
        System.out.println("ListenerThree FAILED for: " + event.getUsername());
        throw new RuntimeException("Intentional failure in ListenerThree");
    }
}
