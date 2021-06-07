package br.com.b2w.desafio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class StarWarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarWarsApplication.class, args);
	}

	@Autowired
	ObjectMapper ojectMapper;

	@Bean
	public OpenAPI customOpenAPI(@Value("") String appDesciption,
			@Value("1.0") String appVersion) {

		return new OpenAPI()
				.info(new Info()
						.title("Star Wars")
						.version(appVersion)
						.description(appDesciption)
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));

	}
}
