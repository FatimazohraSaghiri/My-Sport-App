package com.example.demo.controller;

import com.example.demo.model.Bewertung;
import com.example.demo.service.BewertungService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BewertungController {
    private final BewertungService bewertungService;

    @PostMapping("/bewertung/{idBeitrag}")
    public ResponseEntity<Bewertung> addBewertung(@PathVariable Long idBeitrag, @RequestBody Bewertung bewertung) throws NotFoundException {
        return bewertungService.beitragBewerten(idBeitrag, bewertung);
    }

}
