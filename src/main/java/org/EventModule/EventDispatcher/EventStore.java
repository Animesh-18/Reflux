package org.EventModule.EventDispatcher;

import org.EventModule.ListenerScanner.ListenerRegistry;
import org.EventModule.Publisher.RequestWrapper;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventStore {
    private static final EventStore INSTANCE=new EventStore();
    private final Map<Integer,RequestWrapper> eventStore=new HashMap<>();
    private final EventDispatcher eventDispatcher=EventDispatcher.getINSTANCE();

    public static EventStore getInstance(){
        return INSTANCE;
    }

    public void AddtoStore(RequestWrapper wrapper){
        eventStore.putIfAbsent(wrapper.eventId,wrapper);
    }

    public void RunAllFailedEvents() throws InvocationTargetException, IllegalAccessException, InterruptedException {
        List<RequestWrapper> events = eventStore.values().stream().filter(e -> e.ack.isDone() && !e.ack.join()).toList();
        for(RequestWrapper wrapper:events){
            eventDispatcher.Publish(wrapper);
        }
    }
}
