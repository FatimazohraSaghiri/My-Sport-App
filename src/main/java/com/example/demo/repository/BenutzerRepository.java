package com.example.demo.repository;

import com.example.demo.model.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {

    public Benutzer findByEmail(String email);

    Benutzer findByVerificationCode(String verificationCode);

}
