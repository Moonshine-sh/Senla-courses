package by.ginel.service.impl;

import by.ginel.dao.RoleDao;
import by.ginel.dto.RoleDto;
import by.ginel.mapper.RoleMapper;
import by.ginel.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final RoleDao roleDao;

    @Override
    public List<RoleDto> getAll() {
        return roleDao.getAll().stream().map(roleMapper::mapToRoleDto).toList();
    }

    @Override
    public RoleDto getById(Long id) {
        return roleMapper.mapToRoleDto(roleDao.getById(id));
    }

    @Override
    public RoleDto save(RoleDto entityDto) {
        return roleMapper.mapToRoleDto(roleDao.save(roleMapper.mapToRole(entityDto)));
    }

    @Override
    public Long delete(Long id) {
        return roleDao.delete(id);
    }

    @Override
    public RoleDto update(RoleDto entityDto) {
        return roleMapper.mapToRoleDto(roleDao.update(roleMapper.mapToRole(entityDto)));
    }
}
