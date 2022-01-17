package com.example.demo.service;

import com.example.demo.enums.KategorieEnum;
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

import java.util.List;

@Builder
@AllArgsConstructor
@Service
public class BeitragService {
    private final BeitragRepository beitragRepository;
    private final BenutzerRepository benutzerRepository;

    //Beitrag speichern
    public ResponseEntity<String> addBeitrag(String email, BeitragDto beitragDto) {
        Beitrag beitrag = new Beitrag();
        beitrag.setTitel(beitragDto.getTitel());
        beitrag.setInhalt(beitragDto.getInhalt());
        beitrag.setKategorie(beitragDto.getKategorie());
        Benutzer benutzer = benutzerRepository.findByAdresse(email);
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

    //Beitrag nach kategorie suchen
    public ResponseEntity<Beitrag> beitragSuchen(String kategorie) {
        boolean gefunden = true;
        for (KategorieEnum kategorieEnum : KategorieEnum.values()) {
            if (KategorieEnum.values().equals(kategorie)) {
                gefunden = true;
                break;
            } else {
                gefunden = false;
            }
        }
        if (gefunden == true) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public List<Beitrag> all() {
        return beitragRepository.findAll();
    }
}

