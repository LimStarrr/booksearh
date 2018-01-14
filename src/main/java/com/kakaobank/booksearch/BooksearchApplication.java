package com.kakaobank.booksearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
		basePackageClasses = {BooksearchApplication.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class BooksearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksearchApplication.class, args);
	}

}
