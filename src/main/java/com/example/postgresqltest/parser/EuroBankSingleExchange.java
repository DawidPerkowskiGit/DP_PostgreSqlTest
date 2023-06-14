package com.example.postgresqltest.parser;

public class EuroBankSingleExchange {

    String pattern = "yyyy-MM-dd";
    //private SimpleDateFormat date = new SimpleDateFormat(pattern);
    private String date;
    private String base;

    private Rates rates;


    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }
}
