package com.example.postgresqltest.parser;

import com.example.postgresqltest.exchange.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Service
public class Parser {

    private final boolean DEBUG = false;
    private final boolean DEBUG_STRICT = true;

    private final ExchangeRepository exchangeRepository;

    private final CurrencyRepository currencyRepository;

    @Autowired
    public Parser(ExchangeRepository constructorExchangeRepository, CurrencyRepository constructorCurrencyRepository) {
        this.exchangeRepository = constructorExchangeRepository;
        this.currencyRepository = constructorCurrencyRepository;
    }


    public void parse() throws Exception {
        addCurrencyTypesToDb();
        parseJson();


    }

    public String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public void parseJson() throws Exception {
        String jsonStr = readFileAsString("src/main/resources/database/data.json");

        JSONObject jsonFileStart = new JSONObject(jsonStr);
        int numberOfDays = jsonFileStart.length();

        for (int iteratorOverTheDays = 1; iteratorOverTheDays <= numberOfDays ; iteratorOverTheDays++) {
            JSONObject dailyExchangeEntry = (JSONObject) jsonFileStart.get(""+iteratorOverTheDays);
            String date = dailyExchangeEntry.getString("date");
            int[] dates = convertStringDateToInts(date);
            LocalDate exchangeDate = LocalDate.of(dates[0], dates[1], dates[2]);
            if (exchangeRepository.existsByExchangeDate(exchangeDate)) {
                if (DEBUG_STRICT) {
                    System.out.println("Skipped adding exchanges from day :" + exchangeDate.toString());
                }
                continue;
            }

            JSONObject rates = (JSONObject) dailyExchangeEntry.get("rates");
            String base = dailyExchangeEntry.getString("base");
            addNewExchange(base, exchangeDate, "1.00");
            Map<String, Object> mapOfRates = rates.toMap();

            for (String mapKey: mapOfRates.keySet()
                 ) {
                if (currencyRepository.existsByCurrencyIsoName(mapKey)) {
                    if (DEBUG) {
                        System.out.println(mapKey);
                        System.out.println(mapOfRates.get(mapKey).toString());
                    }
                    if (mapOfRates.get(mapKey).toString().equals("N/A")) {
                    } else {
                        addNewExchange(mapKey, exchangeDate, mapOfRates.get(mapKey).toString());
                    }
                }
            }
            if (DEBUG) {
                System.out.println("Proccessed exchanges from day " + date);
            }
        }
        if (DEBUG_STRICT) {
            System.out.println("FINISHED POPULATING DATABASE");
        }
    }

    public void addCurrencyTypesToDb() {


        for (ExchangeCurrencyTypes entry: ExchangeCurrencyTypes.values()
             ) {
            if (currencyRepository.existsByCurrencyIsoName(entry.toString())) {
                System.out.println(entry.toString() + " exists");
                continue;
            }
            Currency currency = new Currency();
            currency.setCurrencyIsoName((entry.toString()));
            System.out.println("Added currency " + entry.toString());
            currencyRepository.save(currency);
        }
    }

    public void addNewExchange(String currencyName, LocalDate exchangeDate, String value) {
        Currency currency = currencyRepository.findCurrencyByCurrencyIsoName(currencyName);
        Exchange exchange = new Exchange();
        exchange.setCurrency(currency);
        exchange.setValue(Double.parseDouble(value));

        exchange.setExchangeDate(exchangeDate);
        exchangeRepository.save(exchange);
        if (DEBUG) {
            System.out.println(exchange.toString());
        }
    }

    public int[] convertStringDateToInts(String date) {
        String[] strings = date.split("-");
        int arr[] = new int[strings.length];
        for (int iterator = 0; iterator < strings.length; iterator++) {
            arr[iterator] = Integer.valueOf(strings[iterator]);
        }
        return arr;
    }
}
