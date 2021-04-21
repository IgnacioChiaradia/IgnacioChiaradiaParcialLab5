package com.example.Chiaradia1erparcial.repository;

import com.example.Chiaradia1erparcial.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
