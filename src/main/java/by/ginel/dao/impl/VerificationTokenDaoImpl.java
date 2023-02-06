package by.ginel.dao.impl;

import by.ginel.config.ConnectionHandler;
import by.ginel.dao.VerificationTokenDao;
import by.ginel.entity.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VerificationTokenDaoImpl implements VerificationTokenDao {
    private static final String SELECT_ALL = "SELECT * FROM verification_token";
    private static final String SELECT_BY_ID = "SELECT * FROM verification_token WHERE id = ?";
    private static final String INSERT = "INSERT INTO verification_token (token, person_id, expiry_date) VALUES (?)";
    private static final String DELETE = "DELETE FROM verification_token WHERE id = ?";
    private static final String UPDATE = "UPDATE verification_token SET token = ?, person_id = ?, expiry_date = ? WHERE id = ?";

    private final ConnectionHandler connectionHandler;

    @Override
    public List<VerificationToken> getAll() {
        return null;
    }

    @Override
    public VerificationToken getById(Long id) {
        return null;
    }

    @Override
    public Long save(VerificationToken entity) {

        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(VerificationToken entity) {

    }
}
