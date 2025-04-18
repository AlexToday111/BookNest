package com.booknest.recommendation;

import com.booknest.common.error.BusinessRuleException;

public class UnsupportedRecommendationTypeException extends BusinessRuleException {

    public UnsupportedRecommendationTypeException(RecommendationType type) {
        super("Unsupported recommendation type: " + type);
    }
}
