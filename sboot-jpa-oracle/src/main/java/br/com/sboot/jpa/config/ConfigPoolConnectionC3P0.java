package br.com.sboot.jpa.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
public class ConfigPoolConnectionC3P0 {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${minPoolSize}")
    private int minPoolSize;

    @Value("${maxPoolSize}")
    private int maxPoolSize;

    @Value("${idleConnectionTestPeriod}")
    private int idleConnectionTestPeriod;

    @Value("${maxIdleTime}")
    private int maxIdleTime;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("br.com.sboot.jpa.entity.*");
        entityManagerFactoryBean.setJpaProperties(hibProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager =
                new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public ComboPooledDataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(driver);

            dataSource.setJdbcUrl(url);
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setMinPoolSize(minPoolSize); //quantidade minima inicial de conexões
            dataSource.setMaxPoolSize(maxPoolSize); //quantidade máxima de conexões
            dataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriod); //derruba as conexões que ficam ociosas por muito tempo, eliminando o risco de escolher uma conexão quebrada
            //dataSource.setMaxIdleTime(maxIdleTime); //libera as conexões não utilizadas.
            //dataSource.setMaxStatements(maxStatements);
            //dataSource.setMaxStatementsPerConnection(maxStatementsPerConnection);

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
        properties.put("hibernate.show_sql", "true");
        return properties;
    }
}
