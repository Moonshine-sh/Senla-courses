package by.ginel.dao.impl;

import by.ginel.dao.StatusDao;
import by.ginel.dao.datasource.MockDataSource;
import by.ginel.entity.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusDaoImpl extends DaoImpl<Status> implements StatusDao {
    public StatusDaoImpl(MockDataSource mockDataSource) {
        super(mockDataSource);
    }

    @Override
    protected Class<Status> getEntityClass() {
        return Status.class;
    }
}
