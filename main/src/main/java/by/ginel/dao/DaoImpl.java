package by.ginel.dao;

import by.ginel.annotation.Autowired;
import by.ginel.annotation.Component;
import by.ginel.utils.ParametersHolder;

@Component
public class DaoImpl implements DaoInterface {
    @Autowired
    private ParametersHolder parametersHolder;

    public String execute() {
        return parametersHolder.getSomeText();
    }
}
