package com.example.demo.service;

import com.example.demo.model.Bewertung;
import com.example.demo.repository.BeitragRepository;
import com.example.demo.repository.BenutzerRepository;
import com.example.demo.repository.BewertungRepository;
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

    public ResponseEntity<Bewertung> beitragBewerten(Long idBeitrag, Bewertung bewertung) throws NotFoundException {
        if (bewertung.getAnzahlStr() < 0 && bewertung.getAnzahlStr() > 5) {
            throw new NotFoundException("die Anzahl sollte zwischen 1 und 5 sein ");
        } else {
            beitragRepository.findById(idBeitrag).ifPresent(beitrag -> {
                Bewertung neubewertung = new Bewertung();
                neubewertung.setAnzahlStr(bewertung.getAnzahlStr());
                neubewertung.setDatum(LocalDateTime.now());
                neubewertung.setBeitrag(beitrag);
                bewertungRepository.save(neubewertung);
            });
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}


