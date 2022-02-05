package com.example.demo.service;

import com.example.demo.model.Bewertung;
import com.example.demo.repository.BeitragRepository;
import com.example.demo.repository.BenutzerRepository;
import com.example.demo.repository.BewertungRepository;
import com.example.demo.web.dto.BewertungDto;
import javassist.NotFoundException;
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
    private final BewertungRepository bewertungRepository;

    public ResponseEntity<Bewertung> beitragBewerten(Long idBeitrag, BewertungDto bewertungDto) throws NotFoundException {
        if (bewertungDto.getAnzahlStr() > 0 && bewertungDto.getAnzahlStr() < 5) {
            throw new NotFoundException("die Anzahl sollte zwischen 1 und 5 sein ");
        } else {
            beitragRepository.findById(idBeitrag).ifPresent(beitrag -> {
                Bewertung bewertung = new Bewertung();
                bewertung.setAnzahlStr(bewertungDto.getAnzahlStr());
                bewertung.setDatum(LocalDateTime.now());
                bewertung.setBeitrag(beitrag);
                bewertungRepository.save(bewertung);
            });
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}


