package com.example.Chiaradia1erparcial.repository;

import com.example.Chiaradia1erparcial.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {


}
