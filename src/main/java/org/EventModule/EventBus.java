package org.EventModule;

import org.EventModule.EventDispatcher.EventDispatcher;
import org.EventModule.EventDispatcher.EventStore;
import org.EventModule.Publisher.Publisher;
import org.EventModule.Publisher.RequestWrapper;

import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

public class EventBus {

    private static Publisher publisher=new Publisher();


    public static void init(String basePackage) throws URISyntaxException, InvocationTargetException, IllegalAccessException {
        EventBusInitializer.initialize(basePackage);
    }

    public static void publish(Object event, int priority) throws InterruptedException, InvocationTargetException, IllegalAccessException {
        publisher.Publish(event,priority);
    }

    public static void RunAllFailedEvents() throws Exception {
        EventStore.getInstance().RunAllFailedEvents();
    }
}
