package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
