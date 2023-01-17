package by.ginel;

import by.ginel.config.impl.ObjectConfig;

public class Application {
    public static ApplicationContext run(String packageToScan) {
        ObjectConfig config = new ObjectConfig(packageToScan);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);
        context.initContext();
        return context;
    }
}
