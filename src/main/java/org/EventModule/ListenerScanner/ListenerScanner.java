package org.EventModule.ListenerScanner;

import java.lang.reflect.Method;

public class ListenerScanner {
    private ListenerRegistry listenerRegistry;

    public ListenerScanner(ListenerRegistry listenerRegistry) {
        this.listenerRegistry = listenerRegistry;
    }

    public void scan(Object target) {
        Class<?> eventtype = target.getClass();
        for (Method method : eventtype.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] param = method.getParameterTypes();
                if(param.length!=1){
                    throw new RuntimeException("The listener must have only one input parameter");
                }
                Discriptor discriptor=new Discriptor(target,method);
                listenerRegistry.addListener(param[0],discriptor);
            }
        }

    }
}