package ru.unibell.dtos.responses;


import lombok.Data;

@Data
public class ClientFullResponse {

    private Long id;

    private String name;

    private ContactsResponse contacts;
}
