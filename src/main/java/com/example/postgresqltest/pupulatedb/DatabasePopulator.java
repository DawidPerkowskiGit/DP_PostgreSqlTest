package com.example.postgresqltest.pupulatedb;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.postgresqltest.exchange.ExchangeRepository;
import com.example.postgresqltest.exchange.CurrencyRepository;
//import com.example.postgresqltest.exchange.ExchangeDateRepository;
import org.springframework.stereotype.Controller;

@Controller
public class DatabasePopulator {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

/*    @Autowired
    private ExchangeDateRepository exchangeDateRepository;*/

    public String addCategories() {


        return "OK";
    }

}
