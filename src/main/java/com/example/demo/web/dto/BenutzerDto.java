package com.example.demo.web.dto;

import com.example.demo.model.Beitrag;
import com.example.demo.model.Kommentar;
import lombok.*;

import java.util.List;

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
    private List<Kommentar> kommentarList;
    private List<Beitrag> beitragList;
}
