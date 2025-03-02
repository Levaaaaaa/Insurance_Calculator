package com.example.insurance_calculator.persistence.repositories.calculate;

import com.example.insurance_calculator.persistence.entities.calculate.Classifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassifierRepository extends JpaRepository<Classifier, Long> {

    @Cacheable(cacheNames = {"classifierCache"}, key = "#p0", unless="#result == null")
    public Optional<Classifier> findByTitle(String title);

}
