package ru.unibell.dtos.responses;

import lombok.Data;

@Data
public class ContactResponse {

    private EmailResponse email;

    private PhoneResponse phone;
}
