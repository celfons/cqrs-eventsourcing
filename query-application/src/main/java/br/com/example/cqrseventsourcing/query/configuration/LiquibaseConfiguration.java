package br.com.example.cqrseventsourcing.query.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class LiquibaseConfiguration {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) throws SQLException {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath*:db/changelog-master.xml");
        springLiquibase.setContexts("development, production");
        return springLiquibase;
    }

}
