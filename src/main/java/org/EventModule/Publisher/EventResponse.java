package org.EventModule.Publisher;

import org.EventModule.ListenerScanner.Discriptor;

import java.util.List;

public class EventResponse {
    public boolean status;
    public List<Discriptor> FailedEvents;
    public Object event;
}
