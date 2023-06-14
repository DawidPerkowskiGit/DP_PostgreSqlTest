package com.example.postgresqltest.exchange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Integer> {

    Currency save(Currency currency);

    boolean existsByCurrencyIsoName(String name);
    boolean existsByCurrencyId(Long id);

    Currency findCurrencyByCurrencyIsoName(String name);



}
