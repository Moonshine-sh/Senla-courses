package by.ginel;

import by.ginel.controller.ControllerImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = Application.run("by.ginel");
        ControllerImpl controller = context.getObject(ControllerImpl.class);
        System.out.println(controller.execute());
    }
}
