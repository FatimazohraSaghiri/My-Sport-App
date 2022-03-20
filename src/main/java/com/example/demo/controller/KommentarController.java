package com.example.demo.controller;

import com.example.demo.model.Kommentar;
import com.example.demo.service.KommentarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class KommentarController {
    private final KommentarService kommentarService;

    @PostMapping("/kommentar/add/{idBeitrag}")
    public ResponseEntity<String> KommentarEinfuegen(@PathVariable Long idBeitrag, @RequestBody Kommentar kommentar) {
        return kommentarService.kommentarVerfassen(idBeitrag, kommentar);
    }

    @PostMapping("kommentar/aktualisieren/{id}")
    public ResponseEntity<Kommentar> kommentarBearbeiten(@PathVariable Long id, @RequestBody Kommentar kommentar) {
        return kommentarService.kommentarBearbeiten(id, kommentar);
    }

    @DeleteMapping("/kommentar/delete/{id}")
    public ResponseEntity<Kommentar> kommentarLoeschen(@PathVariable Long id) {
        return kommentarService.kommentarLoeschen(id);
    }

}
