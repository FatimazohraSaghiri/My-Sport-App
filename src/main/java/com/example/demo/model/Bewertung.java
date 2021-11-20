package com.example.demo.model;

import javax.xml.crypto.Data;

public class Bewertung {
    private String inhalt;
    private Data datum;

    public Bewertung(String inhalt, Data datum) {
        this.inhalt = inhalt;
        this.datum = datum;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public Data getDatum() {
        return datum;
    }

    public void setDatum(Data datum) {
        this.datum = datum;
    }
}
