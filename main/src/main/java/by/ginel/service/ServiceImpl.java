package by.ginel.service;

import by.ginel.annotation.Autowired;
import by.ginel.annotation.Component;
import by.ginel.dao.DaoInterface;

@Component
public class ServiceImpl implements ServiceInterface {
    private DaoInterface dao;

    public String execute() {
        return dao.execute();
    }

    @Autowired
    public void setDao(DaoInterface dao) {
        this.dao = dao;
    }
}
