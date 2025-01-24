package com.example.insurance_calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.insurance_calculator.core.domain")
public class InsuranceCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceCalculatorApplication.class, args);
	}

}
