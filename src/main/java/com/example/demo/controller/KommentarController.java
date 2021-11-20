package com.example.demo.controller;

import com.example.demo.service.KommentarService;
import com.example.demo.web.dto.KommentarDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class KommentarController {
    private final KommentarService kommentarService;

    @PostMapping("kommentar/add/{id}")
    public ResponseEntity<String> addKommentar(@PathVariable Long id, @RequestBody KommentarDto kommentarDto) {
        return kommentarService.kommentarVerfassen(id, kommentarDto);
    }
}
