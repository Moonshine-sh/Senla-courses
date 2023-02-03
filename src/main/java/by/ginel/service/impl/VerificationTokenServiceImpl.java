package by.ginel.service.impl;

import by.ginel.dao.VerificationTokenDao;
import by.ginel.dto.VerificationTokenDto;
import by.ginel.mapper.VerificationTokenMapper;
import by.ginel.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenMapper tokenMapper;
    private final VerificationTokenDao tokenDao;

    @Override
    public List<VerificationTokenDto> getAll() throws SQLException, InterruptedException {
        return tokenDao.getAll().stream().map(tokenMapper::mapToVerificationTokenDto).toList();
    }

    @Override
    public VerificationTokenDto getById(Long id) throws SQLException, InterruptedException {
        return tokenMapper.mapToVerificationTokenDto(tokenDao.getById(id));
    }

    @Override
    public Long save(VerificationTokenDto entityDto) throws SQLException, InterruptedException {
        tokenDao.save(tokenMapper.mapToVerificationToken(entityDto));
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        tokenDao.delete(id);
    }

    @Override
    public void update(VerificationTokenDto entityDto) throws SQLException, InterruptedException {
        tokenDao.update(tokenMapper.mapToVerificationToken(entityDto));
    }
}
