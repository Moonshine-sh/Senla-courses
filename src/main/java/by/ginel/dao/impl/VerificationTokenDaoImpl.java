package by.ginel.dao.impl;

import by.ginel.dao.VerificationTokenDao;
import by.ginel.entity.VerificationToken;
import by.ginel.entity.VerificationToken_;
import jakarta.persistence.NoResultException;
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
public class VerificationTokenDaoImpl extends AbstractDaoImpl<VerificationToken> implements VerificationTokenDao {

    @Override
    protected Class<VerificationToken> getEntityClass() {
        return VerificationToken.class;
    }

    @Override
    public VerificationToken findByToken(String token) throws NoResultException {
        log.info("Executing method findByToken()");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VerificationToken> cq = cb.createQuery(getEntityClass());
        Root<VerificationToken> root = cq.from(getEntityClass());
        cq.select(root).where(cb.equal(root.get(VerificationToken_.TOKEN), token));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
