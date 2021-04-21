package com.example.Chiaradia1erparcial.controller;

import com.example.Chiaradia1erparcial.model.Currency;
import com.example.Chiaradia1erparcial.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAll() {

        return currencyService.getAll();
    }

    @GetMapping("/{id}")
    public Currency getCurrencyById(@PathVariable Integer id) {

        return currencyService.getByID(id);
    }

    @PostMapping
    public void addCurrency(@RequestBody Currency currency) {

        currencyService.add(currency);
    }

    @DeleteMapping("/{id}")
    public void deleteCurrency(@PathVariable Integer id) {
        currencyService.deleteById(id);
    }
}
