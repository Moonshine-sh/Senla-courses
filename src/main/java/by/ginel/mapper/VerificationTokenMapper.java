package by.ginel.mapper;

import by.ginel.dto.VerificationTokenDto;
import by.ginel.entity.VerificationToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VerificationTokenMapper {
    VerificationToken mapToVerificationToken(VerificationTokenDto verificationTokenDto);

    VerificationTokenDto mapToVerificationTokenDto(VerificationToken verificationToken);
}
