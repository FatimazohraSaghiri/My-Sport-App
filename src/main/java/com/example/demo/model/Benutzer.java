package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "benutzer", uniqueConstraints = @UniqueConstraint(columnNames = "adresse"))
public class Benutzer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "benutzer_id", insertable = false, updatable = true)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "benutzer")
    private List<Beitrag> beitraege = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "benutzer")
    private List<Kommentar> kommentars = new ArrayList<>();

    private String vorname;

    private String nachname;

    @Column(nullable = false)
    private String passwort;

    @Column(nullable = false, unique = true)
    private String adresse;

    private String beschreibung;

    private boolean enabled;
}



