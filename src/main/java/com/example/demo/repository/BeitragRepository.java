package com.example.demo.repository;

import com.example.demo.model.Beitrag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeitragRepository extends JpaRepository<Beitrag, Long> {
    public Beitrag findByTitelAndInhalt(String titel, String inhalt);

    public Beitrag findByBenutzer(String email);

    public Beitrag findBeitragById(Long id);
}
