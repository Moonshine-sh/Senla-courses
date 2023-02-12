package by.ginel.dao.impl;

import by.ginel.dao.StatusDao;
import by.ginel.entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatusDaoImpl extends AbstractDaoImpl<Status> implements StatusDao {

    @Override
    protected Class<Status> getEntityClass() {
        return Status.class;
    }
}
