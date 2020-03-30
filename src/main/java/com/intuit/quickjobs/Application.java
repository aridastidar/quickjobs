package com.intuit.quickjobs;

import com.intuit.entity.Buyer;
import com.intuit.entity.Seller;
import com.intuit.repository.BuyerRepository;
import com.intuit.repository.ProjectRepository;
import com.intuit.repository.SellerRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.intuit.service")
@EntityScan("com.intuit.entity")
@EnableJpaRepositories("com.intuit.repository")
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
    public CommandLineRunner setup(SellerRepository sellerRepository, BuyerRepository buyerRepository, ProjectRepository projectRepository) {
        return (args) -> {
            sellerRepository.save(new Seller("Intuit"));
            buyerRepository.save(new Buyer("John"));
        };
    }

}
