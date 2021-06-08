package com.appfacturasya.service;

import com.appfacturasya.domain.model.RateTerm;
import com.appfacturasya.domain.model.User;
import com.appfacturasya.domain.repository.RateTermRepository;
import com.appfacturasya.domain.repository.UserRepository;
import com.appfacturasya.domain.service.RateTermService;
import com.appfacturasya.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RateTermServiceImpl implements RateTermService {

    @Autowired
    private RateTermRepository rateTermRepository;

    @Override
    public ResponseEntity<?> deleteRateTerm(Long rateTermId) {
        RateTerm rateTerm = rateTermRepository.findById(rateTermId).orElseThrow(() -> new ResourceNotFoundException("RateTerm", "Id", rateTermId));
        rateTermRepository.delete(rateTerm);
        return ResponseEntity.ok().build();
    }

    @Override
    public RateTerm updateRateTerm(Long rateTermId, RateTerm rateTermRequest) {
        RateTerm rateTerm = rateTermRepository.findById(rateTermId)
                .orElseThrow(() -> new ResourceNotFoundException("RateTerm", "Id", rateTermId));
        rateTerm.setDaysPerYear(rateTermRequest.getDaysPerYear());
        rateTerm.setRateTerm(rateTermRequest.getRateTerm());
        rateTerm.setEffectiveRate(rateTermRequest.getEffectiveRate());
        rateTerm.setDiscountDate(rateTermRequest.getDiscountDate());
        return rateTermRepository.save(rateTerm);
    }

    @Override
    public RateTerm createRateTerm(RateTerm rateTerm) {
        return rateTermRepository.save(rateTerm);
    }

    @Override
    public RateTerm getRateTermById(Long rateTermId) {
        RateTerm rateTerm = rateTermRepository.findById(rateTermId).orElseThrow(() -> new ResourceNotFoundException("RateTerm", "Id", rateTermId));
        return rateTerm;
    }

    @Override
    public Page<RateTerm> getAllRateTerms(Pageable pageable) {
        return rateTermRepository.findAll(pageable);
    }
}
