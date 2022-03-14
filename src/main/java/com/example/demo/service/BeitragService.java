package com.example.demo.service;

import com.example.demo.enums.KategorieEnum;
import com.example.demo.model.Beitrag;
import com.example.demo.model.Benutzer;
import com.example.demo.repository.BeitragRepository;
import com.example.demo.repository.BenutzerRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Service
public class BeitragService {
    private final BeitragRepository beitragRepository;
    private final BenutzerRepository benutzerRepository;

    //Beitrag speichern
    public ResponseEntity<Beitrag> addBeitrag(String email, Beitrag beitrag) {
        Benutzer benutzer = benutzerRepository.findByAdresse(email);
        beitrag.setBenutzer(benutzer);
        this.beitragRepository.save(beitrag);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //beitrag aktualisieren
    public ResponseEntity<Beitrag> beitragAktualisieren(Beitrag beitrag, Long id) {
        Beitrag Newbeitrag = beitragRepository.findById(id).get();
        if (beitrag.getInhalt() != null && !beitrag.getInhalt().isBlank()) {
            Newbeitrag.setInhalt(beitrag.getInhalt());
        }
        if (beitrag.getTitel() != null && !beitrag.getTitel().isBlank()) {
            Newbeitrag.setTitel(beitrag.getTitel());
        }
        beitragRepository.save(Newbeitrag);
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

    //Kategorie Liste
    public List<KategorieEnum> kategorieList() {
        List<KategorieEnum> kategorieListe = new ArrayList();
        for (KategorieEnum kategorieEnum : KategorieEnum.values()) {
            kategorieListe.add(kategorieEnum);
        }
        return kategorieListe;
    }

    //Beitrag bewerten
    public int getAnzahlbewertung(Long id) {
        Beitrag beitrag = beitragRepository.findById(id).get();
        int count = 0;
        if (!beitrag.getBewertung().isEmpty()) {
            for (int i = 0; i < beitrag.getBewertung().size(); i++) {
                count += beitrag.getBewertung().get(i).getAnzahlStr();
            }
        }
        return count;
    }
}

