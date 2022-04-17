package com.godngu.boilerplate.infrastructure.common

import org.hibernate.jpa.HibernatePersistenceProvider
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.Properties
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    // Repository 위치
    // domain 모듈의 repository 인터페이스 위치는 상관 없나? -> 상관 있음....
    basePackages = ["com.godngu.boilerplate"],
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager",
)
class DataManagerConfiguration {

    @Value("\${spring.jpa.hibernate.ddl-auto}")
    val hibernateDdlAuto: String = "validate"

    @Value("\${spring.jpa.properties.hibernate.show_sql}")
    val showSql: Boolean = false

    @Value("\${spring.jpa.properties.hibernate.format_sql}")
    val formatSql: Boolean = false

    @Primary
    @Bean(name = ["entityManagerFactory"])
    fun entityManagerFactory(
        @Qualifier("dataSource") dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        val factory = LocalContainerEntityManagerFactoryBean()
        factory.dataSource = dataSource
        factory.persistenceProvider = HibernatePersistenceProvider()
        factory.persistenceUnitName = "entityManager"
        // DataSource 이용하는 도메인의 Entity 가 위치한 패키지
        factory.setPackagesToScan("com.godngu.boilerplate.domain")

        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setShowSql(true)
        factory.jpaVendorAdapter = vendorAdapter

        factory.setJpaProperties(createJpaProperties())
        return factory
    }

    private fun createJpaProperties(): Properties {
        val properties = Properties()
        properties["hibernate.hbm2ddl.auto"] = hibernateDdlAuto
        properties["hibernate.show_sql"] = showSql
        properties["hibernate.format_sql"] = formatSql
        return properties
    }

    @Primary
    @Bean(name = ["transactionManager"])
    fun transactionManager(@Qualifier("entityManagerFactory") entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory
        return transactionManager
    }

    @Bean
    fun exceptionTranslationPostProcessor(): PersistenceExceptionTranslationPostProcessor =
        PersistenceExceptionTranslationPostProcessor()
}
