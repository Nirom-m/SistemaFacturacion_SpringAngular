package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {

    private String Jwt;

}
