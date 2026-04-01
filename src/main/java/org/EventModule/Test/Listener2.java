package org.EventModule.Test;

import org.EventModule.ListenerScanner.Subscribe;

public class Listener2 {

    @Subscribe
    public void handle(UserLogin event) {
        System.out.println("ListenerTwo processed: " + event.getUsername());
    }
}
