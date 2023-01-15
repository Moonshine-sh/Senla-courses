package by.ginel;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ObjectFactory {
    private final ApplicationContext context;
    private final List<ObjectConfigurator> configurators = new ArrayList<>();

    public ObjectFactory(ApplicationContext context) {
        this.context= context;
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            try {
                configurators.add(aClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T createObject(Class<T> implClass) {

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
        configure(t);

        try {
            invokeInit(implClass, t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return t;
    }

    private <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(Autowired.class)) {
                List<Object> args = Arrays.stream(method.getParameterTypes()).map(context::getObject).collect(Collectors.toList());
                method.invoke(t,args.toArray());
            }
        }
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t,context));
    }

    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        //НАПИСАТЬ НОРМАЛЬНО
        Set<Constructor<?>> constructors = Arrays.stream(implClass.getDeclaredConstructors()).filter(constructor -> constructor.isAnnotationPresent(Autowired.class)).collect(Collectors.toSet());
        if(constructors.size()>1){
            throw new RuntimeException("There are more than 1 constractor annotated with Autowired");
        }
        else if(constructors.size()==1){
            Constructor<?> constructor = constructors.iterator().next();
            List<Object> args = Arrays.stream(constructor.getParameterTypes()).map(context::getObject).collect(Collectors.toList());
            return (T) constructor.newInstance(args.toArray());
        }
        return implClass.getDeclaredConstructor().newInstance();
    }
}
