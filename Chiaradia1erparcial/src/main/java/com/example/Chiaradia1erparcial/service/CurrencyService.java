package com.example.Chiaradia1erparcial.service;

import com.example.Chiaradia1erparcial.model.Currency;
import com.example.Chiaradia1erparcial.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class CurrencyService {
    
    @Autowired
    CurrencyRepository currencyRepository;

    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

    public Currency getByID(Integer id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void add(Currency currency) {
        currencyRepository.save(currency);
    }

    public void deleteById(Integer id) {
        currencyRepository.deleteById(id);
    }
}
