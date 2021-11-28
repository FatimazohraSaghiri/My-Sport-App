package com.example.demo.service;

import com.example.demo.model.Beitrag;
import com.example.demo.model.Bewertung;
import com.example.demo.repository.BeitragRepository;
import com.example.demo.repository.BenutzerRepository;
import com.example.demo.web.dto.BewertungDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Builder
@AllArgsConstructor
@Service
public class BewertungService {
    private final BeitragRepository beitragRepository;
    private final BenutzerRepository benutzerRepository;

    public ResponseEntity<Bewertung> beitragBewerten(Long id, BewertungDto bewertungDto) {
        Beitrag beitrag = benutzerRepository.findBeitragId(id);
        if (bewertungDto.getAnzahlStr() > 1 && bewertungDto.getAnzahlStr() <= 3) {

        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
