package com.example.demo.controller;

import com.example.demo.enums.KategorieEnum;
import com.example.demo.model.Beitrag;
import com.example.demo.service.BeitragService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BeitragController {
    private final BeitragService beitragService;

    @PostMapping("/beitrag/add/{email}")
    public ResponseEntity<Beitrag> addBeitrag(@PathVariable("email") String email, @RequestBody Beitrag beitrag) {
        return beitragService.addBeitrag(email, beitrag);
    }

    @PutMapping("/beitrag/aktualisiren/{id}")
    public ResponseEntity<Beitrag> beitragAktualisieren(@PathVariable long id, @RequestBody Beitrag beitrag) {
        return beitragService.beitragAktualisieren(beitrag, id);
    }

    @DeleteMapping("/beitrag/{id}")
    public ResponseEntity<Beitrag> beitragloeschen(@PathVariable long id) {
        return beitragService.beitragloeschen(id);
    }

    @GetMapping("/beitraege")
    List<Beitrag> all() {
        return beitragService.all();
    }

    @GetMapping("/kategorieListe")
    public List<KategorieEnum> kategorieListe() {
        return beitragService.kategorieList();
    }

    @GetMapping("/bewerten/{id}")
    public int getAnzahlbewertung(@PathVariable Long id) {
        return beitragService.getAnzahlbewertung(id);
    }

    @GetMapping("/beitraegeList/{kategorie}")
    List<Beitrag> beitragsuchen(@PathVariable KategorieEnum kategorie) {
        return beitragService.beitragSuchen(kategorie);
    }
}
