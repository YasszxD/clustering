package com.fdev.clustering.host.dto;

import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class HostUpdateRequest {
    String hostname;
    String ipAddress;
    Map<String, String> configurations;
}