package com.casestudy.application.service.adjustment;

import com.casestudy.domain.model.adjustment.AdjustmentRepository;
import com.casestudy.domain.model.adjustment.AdjustmentRequest;
import com.casestudy.domain.model.adjustment.AdjustmentResponse;
import org.springframework.stereotype.Service;

@Service
public class AdjustmentService {

    AdjustmentRepository adjustmentRepository;

    public AdjustmentResponse resize(AdjustmentRequest request) {
        return adjustmentRepository.resize(request);
    }

    AdjustmentService(AdjustmentRepository adjustmentRepository) {
        this.adjustmentRepository = adjustmentRepository;
    }
}