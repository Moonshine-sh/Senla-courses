package by.ginel.service;

import by.ginel.dto.AbstractDto;

import java.sql.SQLException;
import java.util.List;

public interface Service<D extends AbstractDto> {
    List<D> getAll() throws SQLException, InterruptedException;
    D getById(Long id) throws SQLException, InterruptedException;
    Long save(D entityDto) throws SQLException, InterruptedException;
    void delete(Long id) throws SQLException, InterruptedException;
    void update(D entityDto) throws SQLException, InterruptedException;
}
