package com.example.demo.web.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BenutzerDto {
    private Long idBenutzer;
    private String name;
    private String vorname;
    private String email;
    private String passwort;
    private String adresse;
    private String beschreibung;
    private List<KommentarDto> kommentarList;
    private List<BeitragDto> beitragList;
}
