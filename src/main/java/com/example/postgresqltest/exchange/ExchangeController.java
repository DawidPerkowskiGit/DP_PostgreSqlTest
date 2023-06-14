package com.example.postgresqltest.exchange;

import com.example.postgresqltest.parser.Parser;
import com.example.postgresqltest.parser.PrintBeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@Service
@RestController
public class ExchangeController {

    private final ExchangeRepository exchangeRepository;

    private final CurrencyRepository currencyRepository;

    @Autowired
    public ExchangeController(ExchangeRepository constructorExchangeRepository, CurrencyRepository constructorCurrencyRepository) {
        this.exchangeRepository = constructorExchangeRepository;
        this.currencyRepository = constructorCurrencyRepository;
    }

    @GetMapping("/parse")
    public void performDatabasePopulate() throws Exception {
        Parser parser = new Parser(exchangeRepository, currencyRepository);
        parser.parse();
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/currencies")
    public String getCurrencies() {
        return currencyRepository.findAll().toString();
    }

}
