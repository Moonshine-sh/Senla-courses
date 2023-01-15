package by.ginel;

import by.ginel.controller.ControllerInterface;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Application.run("by.ginel");
        ControllerInterface controller = context.getObject(ControllerInterface.class);
        System.out.println(controller.execute());
    }
}
