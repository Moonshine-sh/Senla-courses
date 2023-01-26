package by.ginel.dao.impl;

import by.ginel.dao.StatusDao;
import by.ginel.entity.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusDaoImpl extends DaoImpl<Status> implements StatusDao {
    @Override
    protected Class<Status> getEntityClass() {
        return Status.class;
    }
}
