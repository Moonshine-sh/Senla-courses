package by.ginel.mapper;

import by.ginel.dto.StatusDto;
import by.ginel.entity.Status;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    Status mapToStatus(StatusDto statusDto);

    StatusDto mapToStatusDto(Status status);
}
