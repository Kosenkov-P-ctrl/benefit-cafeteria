package com.kosenkovps.benefitcafeteria.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.kosenkovps.benefitcafeteria.repository"})
@EnableTransactionManagement
@EntityScan(basePackages="com.kosenkovps.benefitcafeteria.model")
public class ApplicationConfig {
}
