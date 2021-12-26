package com.example.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BewertungDto {
    private Long idbewertung;
    private int anzahlStr;
    private LocalDateTime datum;

}
