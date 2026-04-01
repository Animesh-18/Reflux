package org.EventModule;

import org.EventModule.EventDispatcher.EventStore;
import org.EventModule.Publisher.Publisher;
import org.EventModule.Test.UserLogin;

import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws Exception {

        EventBus.init("org.EventModule");


        EventBus.publish(new UserLogin("Animesh"),4);
        Thread.sleep(3000);

        System.out.println("\n===== REPLAY FAILED EVENTS =====\n");

        // 🔁 Replay failed
        EventBus.RunAllFailedEvents();

        // ⏳ Wait again
        Thread.sleep(3000);

        System.out.println("\n===== END =====");
    }
}