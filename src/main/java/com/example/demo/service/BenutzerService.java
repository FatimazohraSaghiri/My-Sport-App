package com.example.demo.service;

import com.example.demo.model.Benutzer;
import com.example.demo.repository.BenutzerRepository;
import com.example.demo.web.dto.BeitragDto;
import com.example.demo.web.dto.BenutzerDto;
import com.example.demo.web.dto.KommentarDto;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Service
public class BenutzerService {

    private final BenutzerRepository benutzerRepository;

    // Benutzer registration
    public ResponseEntity<Benutzer> registerBenutzer(BenutzerDto benutzerDto) {
        String randomCode = RandomString.make(64);
        Benutzer benutzer = Benutzer.builder()
                .vorname(benutzerDto.getVorname())
                .name(benutzerDto.getName())
                .passwort(benutzerDto.getPasswort())
                .adresse(benutzerDto.getAdresse())
                .beschreibung(benutzerDto.getBeschreibung())
                .enabled(true)
                .build();
        this.benutzerRepository.save(benutzer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Benutzer anmelden
    public ResponseEntity<Benutzer> anmelden(BenutzerDto benutzerDto) throws NotFoundException {
        Benutzer benutzer = benutzerRepository.findByAdresse(benutzerDto.getAdresse());
        if (benutzer == null) {
            throw new NotFoundException("Benutzer existiert nicht " + benutzerDto.getAdresse());
        } else if (!benutzer.getAdresse().equals(benutzerDto.getAdresse()) || !benutzer.getPasswort().
                equals(benutzerDto.getPasswort())) {
            throw new NotFoundException("Email oder Passwort ist falsch");
        }
        return new ResponseEntity<>(benutzer, HttpStatus.OK);
    }

    // ProfilBenutzer bearbeitens
    public ResponseEntity<Benutzer> updateBenutzer(BenutzerDto benutzerDto, Long id) {
        Benutzer benutzer = this.benutzerRepository.findById(id).get();
        if (benutzerDto.getName() != null) {
            benutzer.setName(benutzerDto.getName());
        }
        if (benutzerDto.getVorname() != null && !benutzerDto.getVorname().isBlank()) {
            benutzer.setVorname(benutzerDto.getVorname());
        }
        if (benutzerDto.getPasswort() != null && !benutzerDto.getPasswort().isBlank()) {
            benutzer.setPasswort(benutzerDto.getPasswort());
        }
        if (benutzerDto.getAdresse() != null && !benutzerDto.getAdresse().isBlank()) {
            benutzer.setAdresse(benutzerDto.getAdresse());
        }
        if (benutzerDto.getBeschreibung() != null && !benutzerDto.getBeschreibung().isBlank()) {
            benutzer.setBeschreibung(benutzerDto.getBeschreibung());
        }
        this.benutzerRepository.save(benutzer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Output für alle Daten von Benutzer
    public BenutzerDto getBenutzer(String email) {
        Benutzer benutzer = benutzerRepository.findByAdresse(email);
        BenutzerDto benutzerDto = new BenutzerDto();
        benutzerDto.setIdBenutzer(benutzer.getId());
        benutzerDto.setName(benutzer.getName());
        benutzerDto.setVorname(benutzer.getVorname());
        benutzerDto.setAdresse(benutzer.getAdresse());
        benutzerDto.setPasswort(benutzer.getPasswort());
        benutzerDto.setBeschreibung(benutzer.getBeschreibung());
        List<KommentarDto> kommentarDtoList = benutzer.getKommentars().stream().map(kommentar ->
                KommentarDto.builder()
                        .idKommentar(kommentar.getId())
                        .inhalt(kommentar.getInhalt())
                        .erstellt_an(kommentar.getErstellt_an())
                        .build()
        ).collect(Collectors.toList());
        benutzerDto.setKommentarList(kommentarDtoList);
        List<BeitragDto> beitragDtoList = benutzer.getBeitraege().stream().map(beitrag ->
                BeitragDto.builder()
                        .idBeitrag(beitrag.getId())
                        .inhalt(beitrag.getInhalt())
                        .kategorie(beitrag.getKategorie())
                        .titel(beitrag.getTitel())
                        .build()
        ).collect(Collectors.toList());
        benutzerDto.setBeitragList(beitragDtoList);
        return benutzerDto;
    }


}
