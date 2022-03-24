package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bewertung")
public class Bewertung implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "bewertung_id", insertable = false, updatable = true)
    private Long id;
    private int anzahlStr;
    private LocalDateTime datum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "beitrag_id", nullable = false)
    @JsonIgnore
    private Beitrag beitrag;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "benutzer_id", nullable = false)
    private Benutzer benutzer;
}
