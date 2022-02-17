package com.coolbook.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication(scanBasePackages={"com.coolbook.erp","com.coolbook.erp.rest.assembler.ProductMapper"})
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
		System.out.println(BCrypt.hashpw("furnico", BCrypt.gensalt()));
        

    }
}
