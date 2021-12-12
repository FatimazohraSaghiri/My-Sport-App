package com.example.demo.service;

import com.example.demo.model.Bewertung;
import com.example.demo.repository.BeitragRepository;
import com.example.demo.repository.BenutzerRepository;
import com.example.demo.web.dto.BewertungDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Service
public class BewertungService {
    private final BeitragRepository beitragRepository;
    private final BenutzerRepository benutzerRepository;

    public ResponseEntity<Bewertung> beitragBewerten(Long idBeitrag, BewertungDto bewertungDto) {

        beitragRepository.findById(idBeitrag).ifPresent(beitrag -> {
            Bewertung bewertung = new Bewertung();
            bewertung.setAnzahlStr(bewertungDto.getAnzahlStr());
            bewertung.setDatum(LocalDateTime.now());
            beitrag.setBewertung(bewertung);
            beitragRepository.save(beitrag);
        });

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
