package by.ginel.service.impl;

import by.ginel.dao.VerificationTokenDao;
import by.ginel.dto.VerificationTokenDto;
import by.ginel.mapper.VerificationTokenMapper;
import by.ginel.service.VerificationTokenService;
import by.ginel.util.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenMapper tokenMapper;
    private final VerificationTokenDao tokenDao;

    @Override
    public List<VerificationTokenDto> getAll(Pageable pageable) {
        return tokenDao.getAll(pageable).stream().map(tokenMapper::mapToVerificationTokenDto).toList();
    }

    @Override
    public List<VerificationTokenDto> getAll() {
        return getAll(Pageable.maxPage());
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
    public void delete(Long id) {
        tokenDao.delete(id);
    }

    @Override
    public VerificationTokenDto update(VerificationTokenDto entityDto) {
        return tokenMapper.mapToVerificationTokenDto(tokenDao.update(tokenMapper.mapToVerificationToken(entityDto)));
    }
}
