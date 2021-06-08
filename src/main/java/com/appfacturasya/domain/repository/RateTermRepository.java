package com.appfacturasya.domain.repository;

import com.appfacturasya.domain.model.RateTerm;
import com.appfacturasya.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateTermRepository extends JpaRepository<RateTerm, Long> {

}
