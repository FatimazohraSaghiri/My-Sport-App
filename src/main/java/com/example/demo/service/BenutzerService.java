package com.example.demo.service;

import com.example.demo.model.Benutzer;
import com.example.demo.repository.BenutzerRepository;
import com.example.demo.web.dto.BeitragDto;
import com.example.demo.web.dto.BenutzerDto;
import com.example.demo.web.dto.KommentarDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Service
public class BenutzerService {

    private final BenutzerRepository benutzerRepository;
    private JavaMailSender mailSender;

    // Verificationsemail nach der Registrierung senden
    private void sendVerificationEmail(BenutzerDto benutzerDto, String siteURL) {
        String toAdress = benutzerDto.getEmail();
    }

    // Benutzer registration
    public ResponseEntity<Benutzer> registerBenutzer(BenutzerDto benutzerDto) {
        String randomCode = RandomString.make(64);
        Benutzer benutzer = Benutzer.builder()
                .vorname(benutzerDto.getVorname())
                .name(benutzerDto.getName())
                .passwort(benutzerDto.getPasswort())
                .adresse(benutzerDto.getAdresse())
                .beschreibung(benutzerDto.getBeschreibung())
                .verificationCode(randomCode)
                .enabled(true)
                .build();
        this.benutzerRepository.save(benutzer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Benutzer anmelden
    public ResponseEntity<Benutzer> anmelden(Long id, BenutzerDto benutzerDto) {
        Benutzer benutzer = benutzerRepository.findByAdresse(benutzerDto.getAdresse());
        if (benutzer.getAdresse().equals(benutzerDto.getAdresse()) && benutzer.getPasswort().equals(benutzerDto.getPasswort()) && benutzer.isEnabled()
        ) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
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


    private void sendVerificationEmail(Benutzer benutzer, String siteURL) {
        String toAddress = benutzer.getAdresse();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

    }

    public boolean verify(String verificationCode) {
        Benutzer benutzer = benutzerRepository.findByVerificationCode(verificationCode);

        if (benutzer == null || benutzer.isEnabled()) {
            return false;
        } else {
            benutzer.setVerificationCode(null);
            benutzer.setEnabled(true);
            benutzerRepository.save(benutzer);
            return true;
        }
    }

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
