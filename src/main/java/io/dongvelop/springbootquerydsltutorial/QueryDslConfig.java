package io.dongvelop.springbootquerydsltutorial;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
//@RequiredArgsConstructor
public class QueryDslConfig {

    private final EntityManager entityManager;

    public QueryDslConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
