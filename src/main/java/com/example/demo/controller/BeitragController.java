package com.example.demo.controller;

import com.example.demo.model.Beitrag;
import com.example.demo.service.BeitragService;
import com.example.demo.web.dto.BeitragDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BeitragController {
    private final BeitragService beitragService;

    @PostMapping("/beitrag/add/{email}")
    public ResponseEntity<String> addBeitrag(@PathVariable("email") String email, @RequestBody BeitragDto beitragDto) {
        return beitragService.beitragSpeichern(email, beitragDto);
    }

    @PostMapping("/beitrag/aktualisiren/{id}")
    public ResponseEntity<Beitrag> beitragAktualisieren(@PathVariable long id, @RequestBody BeitragDto beitragDto) {
        return beitragService.beitragAktualisieren(beitragDto, id);
    }

    @DeleteMapping("/beitrag/{id}")
    public ResponseEntity<Beitrag> beitragloeschen(@PathVariable long id) {

        return beitragService.beitragloeschen(id);
    }
}
