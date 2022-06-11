package com.ssafy.ssafit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.ssafy.ssafit.property.FileUploadProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileUploadProperties.class
})

public class SsafitBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsafitBackApplication.class, args);
	}

}
