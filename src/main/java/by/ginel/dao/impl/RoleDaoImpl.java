package by.ginel.dao.impl;

import by.ginel.dao.RoleDao;
import by.ginel.entity.Role;
import by.ginel.entity.Role_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RoleDaoImpl extends AbstractDaoImpl<Role> implements RoleDao {

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    public Role findByName(String name) {
        log.info("Executing method findByName()");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(getEntityClass());
        Root<Role> root = cq.from(getEntityClass());
        cq.select(root).where(cb.equal(root.get(Role_.NAME), name));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
