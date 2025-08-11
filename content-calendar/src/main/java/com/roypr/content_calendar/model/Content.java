package com.roypr.content_calendar.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

public record Content(
        @Id
        Integer id,
        @NotBlank //Validation for at least 1 non-white space char
        String title,
        @Column(value = "DESCRIPTION") //map to db column 'DESCRIPTION' instead of 'desc'
        String desc,
        Status status,
        Type contentType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url
        )
{
}
