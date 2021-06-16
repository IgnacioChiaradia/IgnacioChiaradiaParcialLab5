package com.example.Chiaradia1erparcial.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Chiaradia1erparcial.model.Cumpleanito;

@Repository
public interface CumpleanitoRepository extends JpaRepository<Cumpleanito, Integer> {

}