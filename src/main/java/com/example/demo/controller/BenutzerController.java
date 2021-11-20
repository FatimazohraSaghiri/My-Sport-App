package com.example.demo.controller;

import com.example.demo.model.Benutzer;
import com.example.demo.service.BenutzerService;
import com.example.demo.web.dto.BenutzerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BenutzerController {

    private final BenutzerService benutzerService;

    @PostMapping("/benutzer")
    public ResponseEntity<String> addBenutzer(@RequestBody BenutzerDto benutzerDto) {
        return benutzerService.registerBenutzer(benutzerDto);
    }

    @PostMapping("/benutzer/{id}")
    public ResponseEntity<Benutzer> updateBenutzer(@PathVariable long id, @RequestBody BenutzerDto benutzerDto) {
        return benutzerService.updateBenutzer(benutzerDto, id);
    }
}
