package ru.unibell.services;

import ru.unibell.dtos.requests.ClientRequest;
import ru.unibell.dtos.requests.ContactRequest;
import ru.unibell.dtos.requests.RequestContactType;
import ru.unibell.dtos.responses.ClientFullResponse;
import ru.unibell.dtos.responses.ClientResponse;
import ru.unibell.dtos.responses.ContactResponse;
import ru.unibell.dtos.responses.ContactsResponse;

import java.util.List;

public interface ClientService {

    ClientResponse createClient(ClientRequest createClientRequest);

    ContactResponse createContact(ContactRequest contactRequest);

    List<ClientFullResponse> getAllClients();

    ClientFullResponse getClient(Long id);

    ContactsResponse getContactsByClient(Long clientId);

    ContactsResponse getContactsByTypeAndClient(Long clientId, RequestContactType type);
}
