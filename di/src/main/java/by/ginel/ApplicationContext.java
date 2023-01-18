package by.ginel;

import by.ginel.config.Config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private final Map<Class, Object> cache;
    private final Config config;

    public ApplicationContext(Config config) {
        this.config = config;
        cache = new ConcurrentHashMap<>();
    }

    public <T> T getObject(Class<T> type) {
        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        if (cache.containsKey(implClass)) {
            return (T) cache.get(implClass);
        }
        return null;
    }
    public <T> void putObject(Class<T> tClass, Object o) {
        cache.put(tClass, o);
    }

    public Class<?> getImplClass(Class<?> ifc) {
        return config.getImplClass(ifc);
    }
}
