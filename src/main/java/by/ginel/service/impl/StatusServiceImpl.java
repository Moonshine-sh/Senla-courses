package by.ginel.service.impl;

import by.ginel.dao.StatusDao;
import by.ginel.dto.StatusDto;
import by.ginel.mapper.StatusMapper;
import by.ginel.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusMapper statusMapper;
    private final StatusDao statusDao;

    @Override
    public List<StatusDto> getAll() {
        return statusDao.getAll().stream().map(statusMapper::mapToStatusDto).toList();
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
    public Long delete(Long id) {
        return statusDao.delete(id);
    }

    @Override
    public StatusDto update(StatusDto entityDto) {
        return statusMapper.mapToStatusDto(statusDao.update(statusMapper.mapToStatus(entityDto)));
    }
}
