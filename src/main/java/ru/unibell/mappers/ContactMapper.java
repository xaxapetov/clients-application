package ru.unibell.mappers;

import org.mapstruct.Mapper;
import ru.unibell.dtos.responses.ContactResponse;
import ru.unibell.dtos.responses.ContactsResponse;
import ru.unibell.models.Email;
import ru.unibell.models.Phone;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EmailMapper.class, PhoneMapper.class})
public interface ContactMapper {

    ContactResponse emailAndPhoneToContactResponse(Email email, Phone phone);

    ContactsResponse emailsAndPhonesToContactsResponse(List<Email> emails, List<Phone> phones);
}

