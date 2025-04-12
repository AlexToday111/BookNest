package com.booknest.common.error;

import java.time.LocalDateTime;
import java.util.List;

public record ApiErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String path,
        List<FieldErrorResponse> details
) {
}
