package com.example.demo.controller;

import com.example.demo.model.Benutzer;
import com.example.demo.service.BenutzerService;
import com.example.demo.web.dto.BenutzerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BenutzerController {

    private final BenutzerService benutzerService;

    @PostMapping("/benutzer")
    public ResponseEntity<Benutzer> addBenutzer(@RequestBody BenutzerDto benutzerDto) {
        return benutzerService.registerBenutzer(benutzerDto);
    }

    @PostMapping("/benutzer/{idBenutzer}")
    public ResponseEntity<Benutzer> updateBenutzer(@PathVariable long id, @RequestBody BenutzerDto benutzerDto) {
        return benutzerService.updateBenutzer(benutzerDto, id);
    }

    @PostMapping("/anmelden/{idBenutzer}")
    public ResponseEntity<Benutzer> anmelden(@PathVariable long id, @RequestBody BenutzerDto benutzerDto) {
        return benutzerService.anmelden(id, benutzerDto);
    }

    @GetMapping("/{email}")
    public BenutzerDto getBenutzer(@PathVariable String email) {
        return benutzerService.getBenutzer(email);
    }

}
