package br.com.gocharge.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "br.com.gocharge.model")
@EnableAutoConfiguration(exclude = DataSourceConfiguration.class)
class JpaConfiguration {
}
