package com.example.demo.controller;

import com.example.demo.model.Kommentar;
import com.example.demo.service.KommentarService;
import com.example.demo.web.dto.KommentarDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class KommentarController {
    private final KommentarService kommentarService;

    @PostMapping("/kommentar/add/{idBeitrag}")
    public ResponseEntity<String> KommentarEinfuegen(@PathVariable Long idBeitrag, @RequestBody KommentarDto kommentarDto) {
        return kommentarService.kommentarVerfassen(idBeitrag, kommentarDto);
    }

    @PostMapping("kommentar/aktualisieren/{id}")
    public ResponseEntity<Kommentar> kommentarBearbeiten(@PathVariable Long id, @RequestBody KommentarDto kommentarDto) {
        return kommentarService.kommentarBearbeiten(id, kommentarDto);
    }

    @DeleteMapping("/kommentar/delete/{id}")
    public ResponseEntity<Kommentar> kommentarLoeschen(@PathVariable Long id) {
        return kommentarService.kommentarLoeschen(id);
    }

}
