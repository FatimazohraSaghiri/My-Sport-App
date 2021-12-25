package com.example.demo.web.dto;

import com.example.demo.model.Beitrag;
import com.example.demo.model.Benutzer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KommentarDto {
    String inhalt;
    LocalDateTime erstellt_an;
    Beitrag beitrag;
    Benutzer benutzer;
}
