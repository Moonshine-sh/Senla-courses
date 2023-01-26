package by.ginel.dao.impl;

import by.ginel.dao.VerificationTokenDao;
import by.ginel.entity.VerificationToken;
import org.springframework.stereotype.Component;

@Component
public class VerificationTokenDaoImpl extends DaoImpl<VerificationToken> implements VerificationTokenDao {
    @Override
    protected Class<VerificationToken> getEntityClass() {
        return VerificationToken.class;
    }
}
