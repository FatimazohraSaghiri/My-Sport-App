package com.example.demo.controller;

import com.example.demo.enums.ProfissionEnum;
import com.example.demo.model.Benutzer;
import com.example.demo.service.BenutzerService;
import com.example.demo.web.dto.BenutzerDto;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BenutzerController {

    private final BenutzerService benutzerService;

    @PostMapping("/registerbenutzer")
    public ResponseEntity<Benutzer> registerBenutzer(@RequestBody BenutzerDto benutzerDto) {
        return benutzerService.registerBenutzer(benutzerDto);
    }

    @PostMapping("/benutzer/{idBenutzer}")
    public ResponseEntity<Benutzer> updateBenutzer(@PathVariable long idBenutzer, @RequestBody BenutzerDto benutzerDto) {
        return benutzerService.updateBenutzer(benutzerDto, idBenutzer);
    }

    @PostMapping("/anmelden")
    public ResponseEntity<Benutzer> anmelden(@RequestBody BenutzerDto benutzerDto) throws NotFoundException {
        return benutzerService.anmelden(benutzerDto);
    }

    @GetMapping("/{email}")
    public BenutzerDto getBenutzer(@PathVariable String email) {
        return benutzerService.getBenutzer(email);
    }

    @GetMapping("/profission")
    public List<ProfissionEnum> profissionEnumList() {
        return benutzerService.profissionEnumList();
    }
}
