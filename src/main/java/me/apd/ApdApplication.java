package me.apd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ApdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApdApplication.class, args);
	}

}
