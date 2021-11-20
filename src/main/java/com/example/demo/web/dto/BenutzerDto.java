package com.example.demo.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BenutzerDto {
    private String name;
    private String vorname;
    private String email;
    private String passwort;
    private String adresse;
    private String beschreibung;
}
