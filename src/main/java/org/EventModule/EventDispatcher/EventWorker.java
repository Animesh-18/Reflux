package org.EventModule.EventDispatcher;

import org.EventModule.ListenerScanner.ListenerRegistry;
import org.EventModule.Publisher.RequestWrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;


public class EventWorker implements Runnable {

    private final PriorityBlockingQueue<RequestWrapper> queue;

    public EventWorker(PriorityBlockingQueue<RequestWrapper> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        ListenerRegistry listenerRegistry=ListenerRegistry.getInstance();
        while (true) {
            try {
                RequestWrapper request = queue.take();
                    boolean success=listenerRegistry.excecutealllisteners(request);
                    if(!success) {
                        if (request.retryCount < 3) {
                            request.retryCount++;
                            queue.put(request);
                        }
                        else {
                            request.ack.complete(false);
                        }
                    }else {
                        request.ack.complete(true);
                    }
                }catch (Exception e){
                throw new RuntimeException("Worker failed");
            }
                }
            }
            }



