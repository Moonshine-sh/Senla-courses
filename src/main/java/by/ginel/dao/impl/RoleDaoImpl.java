package by.ginel.dao.impl;

import by.ginel.dao.RoleDao;
import by.ginel.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleDaoImpl extends AbstractDaoImpl<Role> implements RoleDao {

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}
