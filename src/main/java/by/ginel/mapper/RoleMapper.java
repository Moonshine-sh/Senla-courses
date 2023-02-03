package by.ginel.mapper;

import by.ginel.dto.RoleDto;
import by.ginel.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role mapToRole(RoleDto roleDto);

    RoleDto mapToRoleDto(Role role);
}
