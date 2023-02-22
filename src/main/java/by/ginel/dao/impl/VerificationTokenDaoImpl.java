package by.ginel.dao.impl;

import by.ginel.dao.VerificationTokenDao;
import by.ginel.entity.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificationTokenDaoImpl extends AbstractDaoImpl<VerificationToken> implements VerificationTokenDao {

    @Override
    protected Class<VerificationToken> getEntityClass() {
        return VerificationToken.class;
    }
}
