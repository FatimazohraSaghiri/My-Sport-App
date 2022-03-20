package com.example.demo.service;

import com.example.demo.model.Kommentar;
import com.example.demo.repository.BeitragRepository;
import com.example.demo.repository.BenutzerRepository;
import com.example.demo.repository.KommentarRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Service
public class KommentarService {
    private final KommentarRepository kommentarRepository;
    private final BeitragRepository beitragRepository;
    private final BenutzerRepository benutzerRepository;

    //Kommentar verfassen
    public ResponseEntity<String> kommentarVerfassen(Long BeitragId, Kommentar kommentar) {
        beitragRepository.findById(BeitragId).ifPresent(beitrag -> {
            Kommentar neukommentar = new Kommentar();
            neukommentar.setInhalt(kommentar.getInhalt());
            neukommentar.setErstellt_an(LocalDateTime.now());
            neukommentar.setBeitrag(beitrag);
            kommentarRepository.save(neukommentar);
        });
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Kommentar bearbeiten
    public ResponseEntity<Kommentar> kommentarBearbeiten(Long id, Kommentar kommentar) {
        Kommentar neukommentar = kommentarRepository.findKommentarById(id);
        if (kommentar.getInhalt() != null && !kommentar.getInhalt().isBlank()) {
            neukommentar.setInhalt(kommentar.getInhalt());
        }
        kommentarRepository.save(neukommentar);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //kommentar löschen
    public ResponseEntity<Kommentar> kommentarLoeschen(Long id) {
        Kommentar kommentar = kommentarRepository.findKommentarById(id);
        kommentarRepository.delete(kommentar);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
