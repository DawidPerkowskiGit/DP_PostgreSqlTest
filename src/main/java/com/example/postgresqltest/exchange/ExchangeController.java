package com.example.postgresqltest.exchange;

import com.example.postgresqltest.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("")
    public String home() {
        return "Welcome to Exchange currency app developed with Java 17, Maven 3.9, Spring 3.1.0 and Render";
    }

}
