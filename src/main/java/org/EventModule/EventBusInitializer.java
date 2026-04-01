package org.EventModule;

import org.EventModule.ListenerScanner.Discriptor;
import org.EventModule.ListenerScanner.ListenerRegistry;
import org.EventModule.ListenerScanner.ListenerScanner;
import org.EventModule.PackageScanner.PackageScaner;

import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.List;

public class EventBusInitializer {
    public static void initialize(String basePackage) throws URISyntaxException, InvocationTargetException, IllegalAccessException {
        ListenerRegistry registry = ListenerRegistry.getInstance();
        ListenerScanner scanner = new ListenerScanner(registry);

        List<Class<?>> classes = PackageScaner.scan(basePackage);

        for (Class<?> clazz : classes) {

            try {

                Object instance = clazz.getDeclaredConstructor().newInstance();

                scanner.scan(instance);

            } catch (Exception ignored) {
            }

        }


    }
}
