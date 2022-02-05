package com.example.demo.model;

import com.example.demo.enums.KategorieEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "beitrag")
public class Beitrag {
    private String titel;
    private String inhalt;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", insertable = false, updatable = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "benutzer_id", nullable = false)
    private Benutzer benutzer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "beitrag")
    private List<Kommentar> kommentar = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bewertung> bewertung = new ArrayList<>();
    @Column
    @Enumerated(EnumType.STRING)
    @NonNull
    private KategorieEnum kategorie;

}
