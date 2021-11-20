package com.example.demo.service;

import com.example.demo.model.Benutzer;
import com.example.demo.repository.BenutzerRepository;
import com.example.demo.web.dto.BenutzerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

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

    // Benutzer registrieren
    public ResponseEntity<String> registerBenutzer(BenutzerDto benutzerDto) {
        String randomCode = RandomString.make(64);
        Benutzer benutzer = Benutzer.builder()
                .vorname(benutzerDto.getVorname())
                .name(benutzerDto.getName())
                .email(benutzerDto.getEmail())
                .passwort(benutzerDto.getPasswort())
                .adresse(benutzerDto.getAdresse())
                .beschreibung(benutzerDto.getBeschreibung())
                .verificationCode(randomCode)
                .enabled(false)
                .build();
        this.benutzerRepository.save(benutzer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Benutzer aktualisieren
    public ResponseEntity<Benutzer> updateBenutzer(BenutzerDto benutzerDto, Long id) {
        Benutzer benutzer = this.benutzerRepository.findById(id).get();
        if (benutzerDto.getName() != null) {
            benutzer.setName(benutzerDto.getName());
        }
        if (benutzerDto.getVorname() != null && !benutzerDto.getVorname().isBlank()) {
            benutzer.setVorname(benutzerDto.getVorname());
        }
        if (benutzerDto.getEmail() != null && !benutzerDto.getEmail().isBlank()) {
            benutzer.setEmail(benutzerDto.getEmail());
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
        String toAddress = benutzer.getEmail();
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


}
