package com.gai_app.gai_owners.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class RepositoryConfig {

    @Value("${repository.type}")
    private String repositoryType;

    @Autowired
    private OwnerJpaRepository ownerJpaRepository;
    @Autowired
    private OwnerJdbcRepository ownerJdbcRepository;

    @Bean
    @Primary
    public OwnerRepository ownerRepository() {
        if ("jdbc".equalsIgnoreCase(repositoryType)) {
            System.out.println("JDBC Owner Repository selected.");
            return ownerJdbcRepository;
        } else {
            System.out.println("JPA Owner Repository selected.");
            return ownerJpaRepository;
        }
    }
}