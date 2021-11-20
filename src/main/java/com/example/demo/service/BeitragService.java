package com.example.demo.service;

import com.example.demo.model.Beitrag;
import com.example.demo.model.Benutzer;
import com.example.demo.repository.BeitragRepository;
import com.example.demo.repository.BenutzerRepository;
import com.example.demo.web.dto.BeitragDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Builder
@AllArgsConstructor
@Service
public class BeitragService {
    private final BeitragRepository beitragRepository;
    private final BenutzerRepository benutzerRepository;

    //Beitrag speichern
    public ResponseEntity<String> beitragSpeichern(String email, BeitragDto beitragDto) {
        Beitrag beitrag = new Beitrag();
        beitrag.setTitel(beitragDto.getTitel());
        beitrag.setInhalt(beitragDto.getInhalt());
        Benutzer benutzer = benutzerRepository.findByEmail(email);
        beitrag.setBenutzer(benutzer);
        beitragRepository.save(beitrag);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //beitrag aktualisieren
    public ResponseEntity<Beitrag> beitragAktualisieren(BeitragDto beitragDto, Long id) {
        Beitrag beitrag = beitragRepository.findById(id).get();
        if (beitragDto.getInhalt() != null && !beitragDto.getInhalt().isBlank()) {
            beitrag.setInhalt(beitragDto.getInhalt());
        }
        if (beitragDto.getTitel() != null && !beitragDto.getTitel().isBlank()) {
            beitrag.setTitel(beitragDto.getTitel());
        }
        beitragRepository.save(beitrag);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Beitrag löschen
    public ResponseEntity<Beitrag> beitragloeschen(Long id) {
        Beitrag beitrag = beitragRepository.findById(id).get();
        beitragRepository.delete(beitrag);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
