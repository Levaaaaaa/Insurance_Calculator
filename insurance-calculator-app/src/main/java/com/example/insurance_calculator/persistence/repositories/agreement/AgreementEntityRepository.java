package com.example.insurance_calculator.persistence.repositories.agreement;

import com.example.insurance_calculator.persistence.entities.agreement.AgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;


public interface AgreementEntityRepository extends JpaRepository<AgreementEntity, Long> {
//    @Query("SELECT aed FROM AgreementEntityDomain aed " +
//            "WHERE aed.dateFrom = :df " +
//            "AND aed.dateTo = :dt " +
//            "AND aed.country = :c " +
//            "AND aed.premium = :p")
//    Optional<AgreementEntity> findByDateFromAndDateToAndCountryAndPremium(@Param("df")Date dateFrom,
//                                                                          @Param("dt")Date dateTo,
//                                                                          @Param("c") String country,
//                                                                          @Param("p") BigDecimal premium);

    Optional<AgreementEntity> findByDateFromAndDateToAndCountryAndPremium(Date dateFrom,
                                                                          Date dateTo,
                                                                          String country,
                                                                          BigDecimal premium);
}
