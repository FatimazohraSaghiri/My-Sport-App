package com.example.demo.web.dto;

import com.example.demo.enums.KategorieEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BeitragDto {
    private String titel;
    private String inhalt;
    private KategorieEnum kategorie;
}
