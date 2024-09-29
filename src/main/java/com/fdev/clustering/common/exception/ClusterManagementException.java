package com.fdev.clustering.common.exception;

public class ClusterManagementException extends RuntimeException {

    public ClusterManagementException(String message) {
        super(message);
    }

    public ClusterManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}