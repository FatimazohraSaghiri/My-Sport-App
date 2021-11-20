package com.example.demo.service;

import com.example.demo.model.Beitrag;
import com.example.demo.model.Kommentar;
import com.example.demo.repository.BeitragRepository;
import com.example.demo.repository.BenutzerRepository;
import com.example.demo.repository.KommentarRepository;
import com.example.demo.web.dto.KommentarDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Builder
@Service
public class KommentarService {
    private final KommentarRepository kommentarRepository;
    private final BeitragRepository beitragRepository;
    private final BenutzerRepository benutzerRepository;

    //Kommentar verfassen
    public ResponseEntity<String> kommentarVerfassen(Long id, KommentarDto kommentarDto) {
        Optional<Beitrag> beitrag = beitragRepository.findById(id);
        Kommentar kommentar = new Kommentar();
        kommentar.setInhalt(kommentarDto.getInhalt());
        kommentar.setDate(kommentarDto.getDate());
        kommentar.setBeitrag(kommentarDto.getBeitrag());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
