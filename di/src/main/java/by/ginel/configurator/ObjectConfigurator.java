package by.ginel.configurator;

import by.ginel.ApplicationContext;

import java.lang.reflect.InvocationTargetException;

public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context) throws InvocationTargetException, IllegalAccessException;
}
