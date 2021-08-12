package org.beckn.bpp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BecknBppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BecknBppApplication.class, args);
	}

}
