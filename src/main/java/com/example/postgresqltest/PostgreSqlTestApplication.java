package com.example.postgresqltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileNotFoundException;

@SpringBootApplication
public class PostgreSqlTestApplication {

    public static void main(String[] args) throws FileNotFoundException {

/*        Parser parser = new Parser();
        parser.parse();*/
        SpringApplication.run(PostgreSqlTestApplication.class, args);
    }

}
