package br.com.gocharge.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("dev")
@ConditionalOnProperty(value = "h2.enabled", havingValue = "true")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.com.gocharge.repository")
@EntityScan(basePackages = "br.com.gocharge.model")
class LocalDataBaseConfiguration {

  @Bean
  @ConfigurationProperties("app.h2.datasource")
  public PoolProperties dataSourceProperties() {return new PoolProperties(); }

  @Bean
  public DataSource dataSource(PoolProperties poolProperties) {
    final DataSource dataSource = new DataSource();

    dataSource.setUrl(poolProperties.getUrl());
    dataSource.setDriverClassName(poolProperties.getDriverClassName());
    dataSource.setUsername(poolProperties.getUsername());
    dataSource.setPassword(poolProperties.getPassword());
    dataSource.setValidationQuery(poolProperties.getValidationQuery());
    dataSource.setValidationInterval(poolProperties.getValidationInterval());
    dataSource.setTimeBetweenEvictionRunsMillis(poolProperties.getTimeBetweenEvictionRunsMillis());
    dataSource.setMaxActive(poolProperties.getMaxActive());
    dataSource.setMinIdle(poolProperties.getMinIdle());
    dataSource.setMaxIdle(poolProperties.getMaxIdle());
    dataSource.setMaxWait(poolProperties.getMaxWait());
    dataSource.setInitialSize(poolProperties.getInitialSize());
    dataSource.setRemoveAbandonedTimeout(poolProperties.getRemoveAbandonedTimeout());
    dataSource.setMinEvictableIdleTimeMillis(poolProperties.getMinEvictableIdleTimeMillis());
    dataSource.setSuspectTimeout(poolProperties.getSuspectTimeout());

    return dataSource;
  }
}
