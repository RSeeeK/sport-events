package ru.devhack.motomoto.sportevents.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import ru.devhack.motomoto.sportevents.db.meta.SportEventsDBMeta;

import javax.sql.DataSource;

/**
 * Database configuration
 *
 * @author AR
 */
@Configuration
public class DatabaseConfig {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean("flyway")
    public Flyway flyway() {
        FluentConfiguration fluentConfiguration = Flyway.configure()
                .dataSource(dbUrl, dbUser, dbPassword)
                .schemas(SportEventsDBMeta.schema);
        Flyway flyway = fluentConfiguration.load();
        flyway.repair();
        flyway.migrate();
        return flyway;
    }

    @DependsOn("flyway")
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);
        return new HikariDataSource(config);
    }
}
