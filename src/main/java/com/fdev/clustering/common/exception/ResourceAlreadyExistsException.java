package com.fdev.clustering.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends ClusterManagementException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}