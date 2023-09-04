package ru.unibell.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactsResponse {

    List<EmailResponse> emails;

    List<PhoneResponse> phones;
}
