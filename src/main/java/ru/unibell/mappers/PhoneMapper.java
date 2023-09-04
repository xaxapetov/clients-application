package ru.unibell.mappers;

import org.mapstruct.Mapper;
import ru.unibell.dtos.responses.PhoneResponse;
import ru.unibell.models.Phone;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    PhoneResponse phoneToPhoneResponse(Phone phone);
}
