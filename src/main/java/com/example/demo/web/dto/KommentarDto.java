package com.example.demo.web.dto;

import com.example.demo.model.Beitrag;
import com.example.demo.model.Benutzer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KommentarDto {
    String inhalt;
    Date date;
    Beitrag beitrag;
    Benutzer benutzer;
}
