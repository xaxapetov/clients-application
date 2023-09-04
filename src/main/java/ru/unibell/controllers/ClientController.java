package ru.unibell.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.unibell.dtos.requests.ClientRequest;
import ru.unibell.dtos.requests.ContactRequest;
import ru.unibell.dtos.requests.RequestContactType;
import ru.unibell.dtos.responses.ClientFullResponse;
import ru.unibell.dtos.responses.ClientResponse;
import ru.unibell.dtos.responses.ContactResponse;
import ru.unibell.dtos.responses.ContactsResponse;
import ru.unibell.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавление нового клиента")
    public ClientResponse createClient(@RequestBody ClientRequest createClientRequest) {
        return clientService.createClient(createClientRequest);
    }

    @PostMapping("/{id}/contacts/phones")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавление нового контакта клиента (телефон или email)")
    public ContactResponse createContact(@RequestBody ContactRequest contactRequest) {
        return clientService.createContact(contactRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение списка клиентов")
    public List<ClientFullResponse> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение информации по заданному клиенту(по id)")
    public ClientFullResponse getClient(@PathVariable("id") Long id) {
        return clientService.getClient(id);
    }

    @GetMapping("/{id}/contacts")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение списка контактов заданного клиента")
    public ContactsResponse getContactsByClient(@PathVariable("id") Long clientId) {
        return clientService.getContactsByClient(clientId);
    }

    @GetMapping("/{id}/contacts/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение списка контактов заданного типа заданного клиента")
    public ContactsResponse getContactsByTypeAndClient(@PathVariable("id") Long clientId,
                                                       @RequestParam RequestContactType type) {
        return clientService.getContactsByTypeAndClient(clientId, type);
    }
}
