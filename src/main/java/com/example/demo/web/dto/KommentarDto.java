package com.example.demo.web.dto;

import com.example.demo.model.Beitrag;
import com.example.demo.model.Benutzer;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KommentarDto {
    private Long idKommentar;
    String inhalt;
    LocalDateTime erstellt_an;
    Beitrag beitrag;
    Benutzer benutzer;
}
