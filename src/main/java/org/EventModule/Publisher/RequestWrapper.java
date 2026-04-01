package org.EventModule.Publisher;

import org.EventModule.ListenerScanner.Discriptor;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
public class RequestWrapper {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private Object event;
    private Integer priority;
    public CompletableFuture<Boolean> ack;
    public int retryCount;
    public Integer eventId;
    public List<Discriptor> failedEvents;

    public RequestWrapper(Object event, int priority) {
        this.event = event;
        this.priority = priority;
        this.ack=new CompletableFuture<>();
        this.retryCount=0;
        this.eventId=ID_GENERATOR.incrementAndGet();
    }

    public Object getEvent() {
        return event;
    }

    public void setEvent(Object event) {
        this.event = event;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
