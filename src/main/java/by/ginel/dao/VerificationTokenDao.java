package by.ginel.dao;

import by.ginel.entity.VerificationToken;
import jakarta.persistence.NoResultException;

public interface VerificationTokenDao extends Dao<VerificationToken> {
    VerificationToken findByToken(String token) throws NoResultException;
}
