package org.EventModule.ListenerScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Discriptor {
    public Object target;
    public Method method;

    public Discriptor(Object target, Method method) {
        this.target = target;
        this.method = method;
    }
    public void excecute(Object Event) throws InvocationTargetException, IllegalAccessException {
        method.invoke(target,Event);
    }
}
