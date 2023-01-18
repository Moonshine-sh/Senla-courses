package by.ginel.configurator.impl;

import by.ginel.ApplicationContext;
import by.ginel.annotation.Value;
import by.ginel.configurator.ObjectConfigurator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ValueAnnotationObjectConfigurator implements ObjectConfigurator {
    private final Map<String, String> propertiesMap;

    public ValueAnnotationObjectConfigurator() throws IOException {
        String path = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("application.properties")).getPath();
        Stream<String> lines = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            lines = reader.lines();
            propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
        }
    }

    @Override
    public void configure(Object t, ApplicationContext context) throws IllegalAccessException {
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            Value annotation = field.getAnnotation(Value.class);
            if (annotation != null) {
                if(annotation.value().isEmpty()){
                    throw new RuntimeException("Value annotation parameter value is null");
                }
                String value = propertiesMap.get(annotation.value());
                field.setAccessible(true);
                field.set(t,value);
            }
        }
    }
}
