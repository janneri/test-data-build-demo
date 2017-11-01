package fi.solita.demo;

import org.dalesbred.Database;
import org.dalesbred.conversion.TypeConversionRegistry;
import org.dalesbred.integration.spring.DalesbredConfigurationSupport;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration extends DalesbredConfigurationSupport {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    protected void setupDatabase(Database db) {

        TypeConversionRegistry conversions = db.getTypeConversionRegistry();

        /*
        conversions.registerConversionFromDatabase(BigDecimal.class, Raha.class, Raha::valueOf);
        conversions.registerConversionToDatabase(Raha.class, Raha::toBigDecimal);

        conversions.registerConversions(
                Integer.class, LippuId.class, LippuId::valueOf, LippuId::intValue);

        conversions.registerConversions(
                String.class, Sahkoposti.class, Sahkoposti::stringToSahkoposti, Sahkoposti::sahkopostiToString);

        conversions.registerConversions(
                String.class, Tilausnumero.class, Tilausnumero::stringToTilausnumero, Tilausnumero::tilausnumeroToString);

        conversions.registerConversions(
                Integer.class, Lippunumero.class, Lippunumero::valueOf, Lippunumero::intValue);
        */

    }

}