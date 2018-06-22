package com.casestudy.domain.model.adjustment;

public enum AdjustmentStatus {
    success,
    failed;

    boolean isFailed() {
        return this == failed;
    }
}