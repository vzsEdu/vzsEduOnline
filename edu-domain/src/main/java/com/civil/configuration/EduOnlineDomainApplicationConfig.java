package com.civil.configuration;

import com.civil.domain.EduOnlineDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import java.util.concurrent.Executor;

/**
 * Created by byao on 4/21/15.
 */
@Configuration
@EnableAspectJAutoProxy
@EnableAsync
@Import({EduOnlineJpaRepositoryConfig.class})
@ComponentScan(basePackageClasses = {EduOnlineDomain.class})
public class EduOnlineDomainApplicationConfig implements TransactionManagementConfigurer, AsyncConfigurer {

    @Qualifier("eduOnlineTransactionManager")
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(32);
        executor.setMaxPoolSize(128);
        executor.setQueueCapacity(1024);
        executor.setThreadNamePrefix("EduOnlineExecutor-");
        executor.initialize();
        return executor;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager;
    }
}
