package by.ginel.dao.impl;

import by.ginel.dao.RoleDao;
import by.ginel.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDaoImpl extends DaoImpl<Role> implements RoleDao {
    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}
