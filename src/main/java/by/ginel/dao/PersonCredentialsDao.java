package by.ginel.dao;

import by.ginel.entity.PersonCredentials;

import java.sql.SQLException;

public interface PersonCredentialsDao extends Dao<PersonCredentials> {
    PersonCredentials getByPersonId(Long id) throws SQLException, InterruptedException;
}
