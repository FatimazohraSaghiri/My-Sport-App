package com.example.demo.service;

import com.example.demo.model.Kommentar;
import com.example.demo.repository.BeitragRepository;
import com.example.demo.repository.BenutzerRepository;
import com.example.demo.repository.KommentarRepository;
import com.example.demo.web.dto.KommentarDto;
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
    public ResponseEntity<String> kommentarVerfassen(Long BeitragId, Long BenutzerId, KommentarDto kommentarDto) {
        beitragRepository.findById(BeitragId).ifPresent(beitrag -> {
            benutzerRepository.findById(BenutzerId).ifPresent(benutzer -> {
                Kommentar kommentar = new Kommentar();
                kommentar.setInhalt(kommentarDto.getInhalt());
                kommentar.setErstellt_an(LocalDateTime.now());
                kommentar.setBeitrag(beitrag);
                kommentar.setBenutzer(benutzer);
                kommentarRepository.save(kommentar);
            });
        });


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Kommentar bearbeiten
    public ResponseEntity<Kommentar> kommentarBearbeiten(Long id, KommentarDto kommentarDto) {
        Kommentar kommentar = kommentarRepository.findKommentarById(id);
        if (kommentarDto.getInhalt() != null && !kommentarDto.getInhalt().isBlank()) {
            kommentar.setInhalt(kommentarDto.getInhalt());
        }
        kommentarRepository.save(kommentar);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //kommentar löschen
    public ResponseEntity<Kommentar> kommentarLoeschen(Long id) {
        Kommentar kommentar = kommentarRepository.findKommentarById(id);
        kommentarRepository.delete(kommentar);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
