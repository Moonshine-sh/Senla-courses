package by.ginel.configurator.impl;

import by.ginel.ApplicationContext;
import by.ginel.annotation.Autowired;
import by.ginel.configurator.ObjectConfigurator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AutowiredAnnotationObjectConfigurator implements ObjectConfigurator {
    @Override
    public void configure(Object t, ApplicationContext context) throws InvocationTargetException, IllegalAccessException {
        injectMethod(t, context);
        injectFields(t, context);
    }

    private <T> void injectMethod(T t, ApplicationContext context) throws IllegalAccessException, InvocationTargetException {
        for (Method method : t.getClass().getMethods()) {
            if (method.isAnnotationPresent(Autowired.class)) {
                List<Object> args = Arrays.stream(method.getParameterTypes()).map(context::getObject).collect(Collectors.toList());
                method.invoke(t, args.toArray());
            }
        }
    }

    private <T> void injectFields(T t, ApplicationContext context) throws IllegalAccessException {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(t, object);
            }
        }
    }

}
