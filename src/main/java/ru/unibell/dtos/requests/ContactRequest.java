package ru.unibell.dtos.requests;

import lombok.Data;

@Data
public class ContactRequest {

    private Long clientId;

    private String phoneNumber;

    private String emailName;
}
