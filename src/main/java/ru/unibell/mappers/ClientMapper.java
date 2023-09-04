package ru.unibell.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.unibell.dtos.responses.ClientFullResponse;
import ru.unibell.dtos.responses.ClientResponse;
import ru.unibell.models.Client;

@Mapper(componentModel = "spring", uses = {EmailMapper.class, PhoneMapper.class})
public interface ClientMapper {

    ClientResponse clientToClientResponse(Client client);

    @Mapping(target="contacts.emails", source="client.emails")
    @Mapping(target="contacts.phones", source="client.phones")
    ClientFullResponse clientToClientFullResponse(Client client);
}
