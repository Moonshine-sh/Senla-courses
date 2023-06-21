package by.ginel.dao;

import by.ginel.entity.PersonCredentials;

import java.sql.SQLException;
import java.util.Optional;

public interface PersonCredentialsDao extends Dao<PersonCredentials> {
    Optional<PersonCredentials> findByUsername(String username);
}
