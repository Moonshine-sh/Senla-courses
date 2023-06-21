package by.ginel.service.impl;

import by.ginel.dao.StatusDao;
import by.ginel.dto.StatusDto;
import by.ginel.mapper.StatusMapper;
import by.ginel.service.StatusService;
import by.ginel.util.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class StatusServiceImpl implements StatusService {
    private final StatusMapper statusMapper;
    private final StatusDao statusDao;

    @Override
    public List<StatusDto> getAll(Pageable pageable) {
        return statusDao.getAll(pageable).stream().map(statusMapper::mapToStatusDto).toList();
    }

    @Override
    public List<StatusDto> getAll() {
        return getAll(Pageable.maxPage());
    }

    @Override
    public StatusDto getById(Long id) {
        return statusMapper.mapToStatusDto(statusDao.getById(id));
    }

    @Override
    public StatusDto save(StatusDto entityDto) {
        return statusMapper.mapToStatusDto(statusDao.save(statusMapper.mapToStatus(entityDto)));
    }

    @Override
    public void delete(Long id) {
        statusDao.delete(id);
    }

    @Override
    public StatusDto update(StatusDto entityDto) {
        return statusMapper.mapToStatusDto(statusDao.update(statusMapper.mapToStatus(entityDto)));
    }
}
