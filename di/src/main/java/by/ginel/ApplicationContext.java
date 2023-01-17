package by.ginel;

import by.ginel.config.Config;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private ObjectFactory factory;
    private final Map<Class, Object> cache;
    private final Config config;

    public ApplicationContext(Config config) {
        this.config = config;
        cache = new ConcurrentHashMap<>();
    }

    public void initContext() {
        Set<Class<?>> components = config.getAllComponents();
        for (Class<?> component : components) {
            cache.put(component, factory.createObject(component));
        }
    }

    public <T> T getObject(Class<T> type) {
        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        if (cache.containsKey(implClass)) {
            return (T) cache.get(type);
        }

        T t = factory.createObject(implClass);

        return t;
    }

    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }

    public Config getConfig() {
        return config;
    }
}
