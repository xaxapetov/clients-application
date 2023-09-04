package ru.unibell.converters;

import org.springframework.core.convert.converter.Converter;
import ru.unibell.dtos.requests.RequestContactType;

public class StringToEnumConverter implements Converter<String, RequestContactType> {
    @Override
    public RequestContactType convert(String source) {
        return RequestContactType.valueOf(source.toUpperCase());
    }
}