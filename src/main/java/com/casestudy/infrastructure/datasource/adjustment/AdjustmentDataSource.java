package com.casestudy.infrastructure.datasource.adjustment;

import com.casestudy.application.exception.AdjustmentException;
import com.casestudy.domain.model.adjustment.*;
import com.casestudy.infrastructure.datasource.Temporary;
import com.casestudy.infrastructure.datasource.Workingplace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import org.bytedeco.javacpp.opencv_core.Mat;


@Repository
class AdjustmentDataSource implements AdjustmentRepository {

    Logger LOG = LoggerFactory.getLogger(AdjustmentDataSource.class);

    Boolean debugging;

    @Override
    public AdjustmentResponse resize(AdjustmentRequest request) {

        Workingplace.create();
        Temporary temporary = new Temporary(request.photo());
        temporary.write();

        Mat source = temporary.source();
        AdjustmentSize size = request.size();

        try {
            Resize resize = new Resize(source, size);
            resize.resizing();

            if (debugging)
                resize.write();

            return new AdjustmentResponse(resize.asImage());
        } catch (Exception e) {
            LOG.error("adjustment exception=" + e.getMessage());
            throw new AdjustmentException(e);
        } finally {
            temporary.remove();
        }
    }

    AdjustmentDataSource(@Value("${haarcascades.debugging:false}") Boolean debugging) {
        this.debugging = debugging;
    }
}