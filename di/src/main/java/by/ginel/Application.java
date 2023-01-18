package by.ginel;

import by.ginel.config.impl.ObjectConfig;
import by.ginel.configurator.ObjectConfigurator;
import by.ginel.configurator.impl.AutowiredAnnotationObjectConfigurator;
import by.ginel.configurator.impl.ValueAnnotationObjectConfigurator;

import java.io.IOException;
import java.util.List;

public class Application {
    public static ApplicationContext run(String packageToScan) throws IOException {
        ObjectConfig config = new ObjectConfig(packageToScan);
        ApplicationContext context = new ApplicationContext(config);
        List<ObjectConfigurator> configurators = List.of(new AutowiredAnnotationObjectConfigurator(), new ValueAnnotationObjectConfigurator());
        ObjectFactory objectFactory = new ObjectFactory(context, configurators);
        objectFactory.createObjects(config.getAllComponents());
        return context;
    }
}
