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
@Table(name = "benutzer", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Benutzer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benutzer_id", insertable = false, updatable = true)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "benutzer")
    private List<Beitrag> beitraege = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "benutzer")
    private List<Kommentar> kommentars = new ArrayList<>();

    private String name;

    private String vorname;

    //@Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwort;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String beschreibung;

    private String verificationCode;

    private boolean enabled;


}
