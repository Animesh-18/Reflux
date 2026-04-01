package org.EventModule.PackageScanner;

import javax.naming.Context;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class PackageScaner {
    public static List<Class<?>> scan(String PackageName) throws URISyntaxException {
        List<Class<?>> AllClass = new ArrayList<>();
        String path=PackageName.replace('.','/');
        ClassLoader loader= Thread.currentThread().getContextClassLoader();
        URL resource= loader.getResource(path);
        if (resource == null) {
            return AllClass;
        }
        File Directory= new File(resource.toURI());
        scanDirectory(Directory,PackageName,AllClass);
        return AllClass;
    }
    private static void scanDirectory(File directory, String packageName, List<Class<?>> classes) {
        File[] files = directory.listFiles();
        if (files == null) {
            System.out.println("No files found in: " + directory.getAbsolutePath());
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                scanDirectory(
                        file,
                        packageName + "." + file.getName(),
                        classes
                );
            } else if (file.getName().endsWith(".class")) {

                String className =
                        packageName + "." + file.getName().replace(".class", "");
                try {
                    Class<?> clazz = Class.forName(className);
                    classes.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
