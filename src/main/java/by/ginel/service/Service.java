package by.ginel.service;

import by.ginel.dto.AbstractDto;

import java.util.List;

public interface Service<D extends AbstractDto> {
    List<D> getAll();
    D getById(Long id);
    D save(D entityDto);
    Long delete(Long id);
    D update(D entityDto);
}
