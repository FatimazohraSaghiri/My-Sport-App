package com.example.demo.service;

import com.example.demo.model.Bewertung;
import com.example.demo.repository.BeitragRepository;
import com.example.demo.repository.BenutzerRepository;
import com.example.demo.repository.BewertungRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Service
public class BewertungService {
    private final BeitragRepository beitragRepository;
    private final BenutzerRepository benutzerRepository;
    private final BewertungRepository bewertungRepository;


    public ResponseEntity<String> beitragBewerten(Long idBeitrag, Long idBenutzer, Bewertung bewertung) {
        boolean gefunden;
        beitragRepository.findById(idBeitrag).ifPresent(beitrag -> {

            List<Bewertung> bewertungList = beitrag.getBewertung();
            for (Bewertung bewertung1 : bewertungList) {
                if (bewertung.getBenutzer().equals(benutzerRepository.getById(idBenutzer))) {
                    //gefunden = false;
                    break;
                    //return new ResponseEntity<String>("Sie haben schon diesen Beitrag bewertet ", HttpStatus.NOT_ACCEPTABLE);
                }
            }
            Bewertung neubewertung = new Bewertung();
            neubewertung.setAnzahlStr(bewertung.getAnzahlStr());
            neubewertung.setDatum(LocalDateTime.now());
            neubewertung.setBeitrag(beitrag);
            neubewertung.setBenutzer(benutzerRepository.findById(idBenutzer).get());
            bewertungRepository.save(neubewertung);
            //gefunden = true;
        });
        /*if () {
            return new ResponseEntity<String>("Bewertung ist Erfolgreich ", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<String>("Sie haben an diesem Beitrag schon bewertet", HttpStatus.NOT_ACCEPTABLE);
        }*/
        return null;
    }
}


