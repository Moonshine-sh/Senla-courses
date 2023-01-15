package by.ginel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ValueAnnotationObjectConfigurator implements ObjectConfigurator{
    private final Map<String, String> propertiesMap;

    public ValueAnnotationObjectConfigurator() {
        String path = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("application.properties")).getPath();
        Stream<String> lines = null;
        try {
            lines = new BufferedReader(new FileReader(path)).lines();
        } catch (FileNotFoundException e) {
            System.out.println("property file not found");
            e.printStackTrace();
        }
        propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));

    }

    @Override
    public void configure(Object t,ApplicationContext context) {
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            Value annotation = field.getAnnotation(Value.class);
            if (annotation != null) {
                if(annotation.value().isEmpty()){
                    throw new RuntimeException("Value annotation parameter value is null");
                }
                String value = propertiesMap.get(annotation.value());
                field.setAccessible(true);
                try {
                    field.set(t,value);
                } catch (IllegalAccessException e) {
                    System.out.println("field \'"+field.getName()+"\' is inaccessible");
                    e.printStackTrace();
                }
            }
        }
    }
}
