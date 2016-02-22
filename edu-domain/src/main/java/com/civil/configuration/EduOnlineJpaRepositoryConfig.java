package com.civil.configuration;

import com.civil.domain.CivilDomain;
import com.jolbox.bonecp.BoneCPDataSource;
import com.vzs.persistence.jpa.springdatajpa.VzsDataSourcePropertiesHelper;
import com.vzs.persistence.jpa.springdatajpa.VzsJpaRepositoryFactoryBean;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by byao on 4/21/15.
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = { CivilDomain.class } ,
        entityManagerFactoryRef = "entityManagerFactory",
        repositoryFactoryBeanClass = VzsJpaRepositoryFactoryBean.class,
        transactionManagerRef = "eduOnlineTransactionManager")
public class EduOnlineJpaRepositoryConfig {
    public static final String PRODUCT_JDBC_PROPERTIES_PREFIX = "edu.online.mysql.jdbc";

    @Autowired
    private Environment environment;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource dataSource = new VzsDataSourcePropertiesHelper(this.environment, PRODUCT_JDBC_PROPERTIES_PREFIX).build();
        if (dataSource instanceof BoneCPDataSource) {
            BoneCPDataSource boneCPDataSource = (BoneCPDataSource) dataSource;
            boneCPDataSource.setMaxConnectionAgeInSeconds(420);
        }
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(this.dataSource());

        factoryBean.setPersistenceUnitName("Ben");
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return factoryBean;
    }

    @Bean(name = "eduOnlineTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
