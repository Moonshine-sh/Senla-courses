package by.ginel.dao;

import by.ginel.entity.PersonCredentials;

import java.sql.SQLException;

public interface PersonCredentialsDao extends Dao<PersonCredentials> {
    PersonCredentials findByUsername(String username);
}
