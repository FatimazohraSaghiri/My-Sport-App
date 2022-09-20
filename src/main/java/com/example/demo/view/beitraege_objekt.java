package com.example.demo.view;

import com.example.demo.enums.KategorieEnum;
import com.example.demo.model.Benutzer;
import com.example.demo.model.Bewertung;
import com.example.demo.model.Kommentar;

import java.util.ArrayList;
import java.util.List;

public class beitraege_objekt {
    private Long id;
    private Benutzer benutzer;
    private List<Kommentar> kommentar = new ArrayList<>();
    private List<Bewertung> bewertung = new ArrayList<>();
    private KategorieEnum kategorie;
}
