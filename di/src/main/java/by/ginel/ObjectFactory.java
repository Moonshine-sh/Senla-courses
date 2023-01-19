package by.ginel;

import by.ginel.annotation.Autowired;
import by.ginel.ApplicationContext;
import by.ginel.configurator.ObjectConfigurator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;


public class ObjectFactory {
    private final ApplicationContext context;
    private final List<ObjectConfigurator> configurators;

    public ObjectFactory(ApplicationContext context, List<ObjectConfigurator> configurators) {
        this.context= context;
        this.configurators = configurators;
    }

    public void createObjects(Set<Class<?>> components){
        components.forEach(this::createObject);
        Set<?> objects = components.stream().map(context::getObject).collect(Collectors.toSet());
        objects.forEach(this::configure);
    }

    public <T> T createObject(Class<T> implClass) {
        T impl = context.getObject(implClass);

        if(Objects.nonNull(impl)){
            return impl;
        }

        T t = null;
        try {
            t = create(implClass);
        } catch (InstantiationException e) {
            System.out.println("InstantiationException");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("InvocationTargetException");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException");
            e.printStackTrace();
        }

        context.putObject(implClass,t);
        return t;
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> {
            try {
                objectConfigurator.configure(t,context);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        Constructor<?> constructor = getConstructor(implClass);
        List<Object> args = Arrays.stream(constructor.getParameterTypes()).map(arg -> {
            Class<?> a = arg;
            if(arg.isInterface()){
                a = context.getImplClass(arg);
            }
            return createObject(a);
        }).collect(Collectors.toList());
        return (T) constructor.newInstance(args.toArray());
    }

    private Constructor<?> getConstructor(Class<?> implClass) throws NoSuchMethodException {
        Set<Constructor<?>> constructors = Arrays.stream(implClass.getDeclaredConstructors()).filter(constructor -> constructor.isAnnotationPresent(Autowired.class)).collect(Collectors.toSet());
        if(constructors.size()>1){
            throw new RuntimeException("There are more than 1 constractor annotated with Autowired");
        }
        else if(constructors.size()==1){
            return constructors.iterator().next();
        }
        return implClass.getDeclaredConstructor();
    }
}
