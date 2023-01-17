package by.ginel.config;

import org.reflections.Reflections;

import java.util.Set;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> ifc);

    Reflections getScanner();
    Set<Class<?>> getAllComponents();
}
