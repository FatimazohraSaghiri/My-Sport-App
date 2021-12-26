package com.example.demo.web.dto;

import com.example.demo.enums.KategorieEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeitragDto {
    private Long idBeitrag;
    private String titel;
    private String inhalt;
    private KategorieEnum kategorie;
}
