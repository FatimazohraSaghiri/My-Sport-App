package com.example.demo.service;

import com.example.demo.enums.KategorieEnum;
import com.example.demo.model.Beitrag;
import com.example.demo.model.Benutzer;
import com.example.demo.model.Kommentar;
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

    //Beitrag verfassen
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
        if (beitrag.getKategorie() != null) {
            Newbeitrag.setKategorie(beitrag.getKategorie());
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
    public List<Beitrag> beitragSuchen(String kategorie) {
        List<Beitrag> list = all();
        List<Beitrag> neuList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKategorie().name().equals(kategorie)) {
                Beitrag beitrag = list.get(i);
                neuList.add(beitrag);
            }
        }
        return neuList;
    }


    public List<Beitrag> all() {
        List<Beitrag> liste = new ArrayList<>();
        liste.addAll(beitragRepository.findAll());
        return liste;
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

    public List<Kommentar> kommentars(Long id) {
        Beitrag beitrag = beitragRepository.findById(id).get();
        List<Kommentar> kommentars = new ArrayList<>();
        for (Kommentar kommentar : beitrag.getKommentar()) {
            kommentars.add(kommentar);
        }
        return kommentars;
    }
}

