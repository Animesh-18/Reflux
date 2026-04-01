package org.EventModule.ListenerScanner;

import org.EventModule.Publisher.RequestWrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class ListenerRegistry {
    private static final ListenerRegistry INSTANCE = new ListenerRegistry();

    private final ConcurrentHashMap<Class<?>,List<Discriptor>> registry=new ConcurrentHashMap<>();

    private ListenerRegistry() {}

    public static ListenerRegistry getInstance() {
        return INSTANCE;
    }

    public void addListener(Class<?> event,Discriptor discriptor){
        registry.computeIfAbsent(event,k->new ArrayList<>()).add(discriptor);
    }
    public List<Discriptor> getalllisteners(Class<?> event) throws InvocationTargetException, IllegalAccessException {
        return registry.getOrDefault(event, Collections.emptyList());
    }
    public boolean excecutealllisteners(RequestWrapper request) throws InvocationTargetException, IllegalAccessException {
        List<Discriptor> Listeners;
        if(request.retryCount==0) {
            Listeners = registry.getOrDefault(request.getEvent().getClass(), Collections.emptyList());
        }
        else{
            Listeners=request.failedEvents;
        }
        List<Discriptor> failed=new ArrayList<>();
        //boolean success=true;
        for(Discriptor d:Listeners){
            try {
                d.excecute(request.getEvent());
            }catch (Exception e){
                //success=false;
                failed.add(d);
            }
            request.failedEvents=failed;
        }
        return failed.isEmpty();
    }
}
