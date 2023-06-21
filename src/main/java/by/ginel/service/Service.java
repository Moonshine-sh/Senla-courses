package by.ginel.service;

import by.ginel.dto.AbstractDto;
import by.ginel.util.Pageable;

import java.util.List;

public interface Service<D extends AbstractDto> {
    List<D> getAll(Pageable pageable);

    List<D> getAll();

    D getById(Long id);

    D save(D entityDto);

    void delete(Long id);

    D update(D entityDto);
}
