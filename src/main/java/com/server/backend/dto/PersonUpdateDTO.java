package com.server.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonUpdateDTO {
    private String name;
    private String username;
    private String surname;
    private String email;
    private String password;


}
