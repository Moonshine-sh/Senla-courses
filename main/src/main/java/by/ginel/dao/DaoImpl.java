package by.ginel.dao;

import by.ginel.Autowired;
import by.ginel.utils.ParametersHolder;

public class DaoImpl implements DaoInterface {
    @Autowired
    private ParametersHolder parametersHolder;

    public String execute() {
        return parametersHolder.getSomeText();
    }
}
