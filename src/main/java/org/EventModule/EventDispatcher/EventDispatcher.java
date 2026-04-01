package org.EventModule.EventDispatcher;

import org.EventModule.ListenerScanner.ListenerRegistry;
import org.EventModule.Publisher.RequestWrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class EventDispatcher {
 private final static EventDispatcher INSTANCE= new EventDispatcher();
 private final PriorityBlockingQueue<RequestWrapper> EventQueue=new PriorityBlockingQueue<>( 50,Comparator.comparingInt(RequestWrapper::getPriority).reversed());

   private EventDispatcher(){
       startworker();
   }
    public static EventDispatcher getINSTANCE() {
        return INSTANCE;
    }

    public void Publish(RequestWrapper wrapper) throws InvocationTargetException, IllegalAccessException, InterruptedException {
        EventQueue.put(wrapper);
    }
    public void startworker(){
       Thread startThread= new Thread(new EventWorker(EventQueue));
       startThread.start();
    }
    public void fastPublish(RequestWrapper wrapper) throws InvocationTargetException, IllegalAccessException {
        ListenerRegistry listenerRegistry=ListenerRegistry.getInstance();
        listenerRegistry.excecutealllisteners(wrapper);
    }

}
