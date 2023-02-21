package by.ginel.service.impl;

import by.ginel.dao.StatusDao;
import by.ginel.dto.StatusDto;
import by.ginel.mapper.StatusMapper;
import by.ginel.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class StatusServiceImpl implements StatusService {
    private final StatusMapper statusMapper;
    private final StatusDao statusDao;

    @Override
    public List<StatusDto> getAll() throws SQLException, InterruptedException {
        return statusDao.getAll().stream().map(statusMapper::mapToStatusDto).toList();
    }

    @Override
    public StatusDto getById(Long id) throws SQLException, InterruptedException {
        return statusMapper.mapToStatusDto(statusDao.getById(id));
    }

    @Override
    public Long save(StatusDto entityDto) throws SQLException, InterruptedException {
        statusDao.save(statusMapper.mapToStatus(entityDto));
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        statusDao.delete(id);
    }

    @Override
    public void update(StatusDto entityDto) throws SQLException, InterruptedException {
        statusDao.update(statusMapper.mapToStatus(entityDto));
    }
}
