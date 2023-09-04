package ru.unibell.mappers;

import org.mapstruct.Mapper;
import ru.unibell.dtos.responses.EmailResponse;
import ru.unibell.models.Email;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    EmailResponse emailToEmailResponse(Email email);
}
