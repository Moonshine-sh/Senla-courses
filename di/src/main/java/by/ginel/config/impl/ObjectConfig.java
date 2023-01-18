package by.ginel.config.impl;

import by.ginel.annotation.Component;
import by.ginel.config.Config;
import org.reflections.Reflections;

import java.util.Set;
import java.util.stream.Collectors;

public class ObjectConfig implements Config {
    private final Reflections scanner;

    public ObjectConfig(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc).stream().filter(x -> x.isAnnotationPresent(Component.class)).collect(Collectors.toSet());
        if (classes.size() != 1) {
            throw new RuntimeException(ifc + " has 0 or more than one impl please update your config");
        }
        return classes.iterator().next();
    }

    public Set<Class<?>> getAllComponents() {
        return scanner.getTypesAnnotatedWith(Component.class);
    }
}
