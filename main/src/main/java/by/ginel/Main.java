package by.ginel;

import by.ginel.controller.ControllerImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Application.run("by.ginel");
        ControllerImpl controller = context.getObject(ControllerImpl.class);
        System.out.println(controller.execute());
    }
}
