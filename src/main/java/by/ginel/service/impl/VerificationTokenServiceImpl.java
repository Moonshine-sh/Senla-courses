package by.ginel.service.impl;

import by.ginel.dao.VerificationTokenDao;
import by.ginel.dto.VerificationTokenDto;
import by.ginel.mapper.VerificationTokenMapper;
import by.ginel.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenMapper tokenMapper;
    private final VerificationTokenDao tokenDao;

    @Override
    public List<VerificationTokenDto> getAll() {
        return tokenDao.getAll().stream().map(tokenMapper::mapToVerificationTokenDto).toList();
    }

    @Override
    public VerificationTokenDto getById(Long id) {
        return tokenMapper.mapToVerificationTokenDto(tokenDao.getById(id));
    }

    @Override
    public VerificationTokenDto save(VerificationTokenDto entityDto) {
        return tokenMapper.mapToVerificationTokenDto(tokenDao.save(tokenMapper.mapToVerificationToken(entityDto)));
    }

    @Override
    public Long delete(Long id) {
        return tokenDao.delete(id);
    }

    @Override
    public VerificationTokenDto update(VerificationTokenDto entityDto) {
        return tokenMapper.mapToVerificationTokenDto(tokenDao.update(tokenMapper.mapToVerificationToken(entityDto)));
    }
}
