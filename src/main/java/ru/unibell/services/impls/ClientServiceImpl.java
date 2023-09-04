package ru.unibell.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.unibell.dtos.requests.ClientRequest;
import ru.unibell.dtos.requests.ContactRequest;
import ru.unibell.dtos.requests.RequestContactType;
import ru.unibell.dtos.responses.ClientFullResponse;
import ru.unibell.dtos.responses.ClientResponse;
import ru.unibell.dtos.responses.ContactResponse;
import ru.unibell.dtos.responses.ContactsResponse;
import ru.unibell.exeptions.ClientNotFoundException;
import ru.unibell.exeptions.ContactValidationException;
import ru.unibell.exeptions.EmailAlreadyExistException;
import ru.unibell.exeptions.NameAlreadyExistException;
import ru.unibell.exeptions.PhoneAlreadyExistException;
import ru.unibell.mappers.ClientMapper;
import ru.unibell.mappers.ContactMapper;
import ru.unibell.models.Client;
import ru.unibell.models.Email;
import ru.unibell.models.Phone;
import ru.unibell.repositories.ClientRepository;
import ru.unibell.repositories.EmailRepository;
import ru.unibell.repositories.PhoneRepository;
import ru.unibell.services.ClientService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final PhoneRepository phoneRepository;

    private final EmailRepository emailRepository;

    private final ContactMapper contactMapper;

    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientResponse createClient(ClientRequest createClientRequest) {
        boolean existClient = clientRepository.existsClientByName(createClientRequest.getName());
        if (existClient) {
            throw new NameAlreadyExistException("Name " + createClientRequest.getName() + " already exist!");
        }
        Client client = new Client();
        client.setName(createClientRequest.getName());
        return clientMapper.clientToClientResponse(clientRepository.save(client));
    }

    @Override
    @Transactional
    public ContactResponse createContact(ContactRequest contactRequest) {
        if (Objects.isNull(contactRequest.getEmailName()) &&
                Objects.isNull(contactRequest.getPhoneNumber())) {
            throw new ContactValidationException("Phone or email request are not found!");
        }
        Client client = clientRepository.findById(contactRequest.getClientId())
                .orElseThrow(() ->
                        new ClientNotFoundException("Client with id " + contactRequest.getClientId() + " not found!"));
        Phone phone = createPhoneForClient(contactRequest.getPhoneNumber(), client);
        Email email = createEmailForClient(contactRequest.getEmailName(), client);
        return contactMapper.emailAndPhoneToContactResponse(email, phone);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientFullResponse> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::clientToClientFullResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClientFullResponse getClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() ->
                new ClientNotFoundException("Client with id " + id + " not found!"));

        return clientMapper.clientToClientFullResponse(client);
    }

    @Override
    @Transactional(readOnly = true)
    public ContactsResponse getContactsByClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() ->
                new ClientNotFoundException("Client with id " + clientId + " not found!"));
        List<Email> emails = emailRepository.findAllByClient(client);
        List<Phone> phones = phoneRepository.findAllByClient(client);
        return contactMapper.emailsAndPhonesToContactsResponse(emails, phones);
    }

    @Override
    @Transactional(readOnly = true)
    public ContactsResponse getContactsByTypeAndClient(Long clientId, RequestContactType type) {
        Client client = clientRepository.findById(clientId).orElseThrow(() ->
                new ClientNotFoundException("Client with id " + clientId + " not found!"));
        switch (type) {
            case EMAIL:
                List<Email> emails = emailRepository.findAllByClient(client);
                return contactMapper.emailsAndPhonesToContactsResponse(emails, null);
            case PHONE:
                List<Phone> phones = phoneRepository.findAllByClient(client);
                return contactMapper.emailsAndPhonesToContactsResponse(null, phones);
            default:
                return null;
        }
    }

    private Phone createPhoneForClient(String number, Client client) {
        if (Objects.isNull(number)) {
            return null;
        }
        boolean existPhone = phoneRepository.existsPhoneByNumber(number);
        if (existPhone) {
            throw new PhoneAlreadyExistException("Phone number " + number + " already exist!");
        }
        return phoneRepository.save(Phone.builder().client(client).number(number).build());
    }

    private Email createEmailForClient(String name, Client client) {
        if (Objects.isNull(name)) {
            return null;
        }
        boolean existEmail = emailRepository.existsEmailByName(name);
        if (existEmail) {
            throw new EmailAlreadyExistException("Email " + name + " already exist!");
        }
        return emailRepository.save(Email.builder().client(client).name(name).build());
    }
}
