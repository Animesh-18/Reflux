package org.EventModule.Test;

import org.EventModule.ListenerScanner.Subscribe;

public class LoginListener {

    @Subscribe
    public void handle(UserLogin event) {

        System.out.println("ListenerOne processed: " + event.getUsername());
    }
}
