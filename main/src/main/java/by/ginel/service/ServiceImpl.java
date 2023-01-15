package by.ginel.service;

import by.ginel.Autowired;
import by.ginel.dao.DaoInterface;

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
