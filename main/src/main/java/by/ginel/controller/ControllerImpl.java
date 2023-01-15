package by.ginel.controller;

import by.ginel.Autowired;
import by.ginel.Component;
import by.ginel.service.ServiceInterface;

@Component
public class ControllerImpl implements ControllerInterface {
    private final ServiceInterface service;

    @Autowired
    public ControllerImpl(ServiceInterface service) {
        this.service = service;
    }

    public String execute() {
        return service.execute();
    }
}
