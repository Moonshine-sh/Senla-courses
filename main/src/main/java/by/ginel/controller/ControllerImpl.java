package by.ginel.controller;

import by.ginel.annotation.Autowired;
import by.ginel.annotation.Component;
import by.ginel.service.ServiceInterface;

@Component
public class ControllerImpl {
    private final ServiceInterface service;

    @Autowired
    public ControllerImpl(ServiceInterface service) {
        this.service = service;
    }

    public String execute() {
        return service.execute();
    }
}
