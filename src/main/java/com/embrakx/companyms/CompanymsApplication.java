package com.embrakx.companyms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CompanymsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanymsApplication.class, args);
		System.out.println("Company_service");
	}

}
