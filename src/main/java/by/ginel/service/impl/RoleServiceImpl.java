package by.ginel.service.impl;

import by.ginel.dao.RoleDao;
import by.ginel.dto.RoleDto;
import by.ginel.mapper.RoleMapper;
import by.ginel.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final RoleDao roleDao;

    @Override
    public List<RoleDto> getAll() throws SQLException, InterruptedException {
        return roleDao.getAll().stream().map(roleMapper::mapToRoleDto).toList();
    }

    @Override
    public RoleDto getById(Long id) throws SQLException, InterruptedException {
        return roleMapper.mapToRoleDto(roleDao.getById(id));
    }

    @Override
    public Long save(RoleDto entityDto) throws SQLException, InterruptedException {
        roleDao.save(roleMapper.mapToRole(entityDto));
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        roleDao.delete(id);
    }

    @Override
    public void update(RoleDto entityDto) throws SQLException, InterruptedException {
        roleDao.update(roleMapper.mapToRole(entityDto));
    }
}
