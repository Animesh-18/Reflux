package org.EventModule.Publisher;

import org.EventModule.EventDispatcher.EventDispatcher;
import org.EventModule.EventDispatcher.EventStore;

import java.lang.reflect.InvocationTargetException;

public class Publisher {
    private final EventDispatcher eventDispatcher=EventDispatcher.getINSTANCE();
    private final EventStore eventWorker=EventStore.getInstance();

    public void Publish(Object event, Integer priority) throws InvocationTargetException, IllegalAccessException, InterruptedException {
        if(priority==null){
            priority=1;
        }
        RequestWrapper wrapper=new RequestWrapper(event,priority);
        wrapper.ack.thenAccept(success->{
            System.out.println("Status = "+success);
        });
        eventWorker.AddtoStore(wrapper);
        eventDispatcher.Publish(wrapper);

    }
    public void FastPublish(RequestWrapper wrapper) throws InvocationTargetException, IllegalAccessException {
        eventDispatcher.fastPublish(wrapper);
    }
}
