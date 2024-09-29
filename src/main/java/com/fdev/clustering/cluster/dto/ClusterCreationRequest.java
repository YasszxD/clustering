package com.fdev.clustering.cluster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClusterCreationRequest {
    private String name;
    private Map<String, String> configurations;
}
