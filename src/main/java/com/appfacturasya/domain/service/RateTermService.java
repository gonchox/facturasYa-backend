package com.appfacturasya.domain.service;

import com.appfacturasya.domain.model.RateTerm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RateTermService {

    ResponseEntity<?> deleteRateTerm(Long rateTermId);

    RateTerm updateRateTerm(Long rateTermId, RateTerm rateTermRequest);

    RateTerm createRateTerm(RateTerm rateTerm);

    RateTerm getRateTermById(Long rateTermId);

    Page<RateTerm> getAllRateTerms(Pageable pageable);

}
