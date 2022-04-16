package com.godngu.boilerplate.infrastructure.common

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor

@Configuration
class BeanConfiguration {

    // TODO 적용 안됨
    /**
     * @Repository 애노테이션을 사용한 곳에 예외변환 AOP를 적용해서 JPA 예외를 스프링프레임워크가 추상화한 예외로 변환하도록 처리
     * (예) javax.persistence.NoResultException -> org.springframework.dao.EmptyResultDataAccessException
     */
    @Bean
    fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor =
        PersistenceExceptionTranslationPostProcessor()
}
