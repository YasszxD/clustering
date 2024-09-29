package com.fdev.clustering.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ClusterOperationException extends ClusterManagementException {

    public ClusterOperationException(String message) {
        super(message);
    }

    public ClusterOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}