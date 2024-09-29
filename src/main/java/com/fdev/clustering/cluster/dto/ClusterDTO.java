package com.fdev.clustering.cluster.dto;

import com.fdev.clustering.host.model.Host;
import com.fdev.clustering.host.service.HostService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClusterDTO {
    private String id;
    private String name;
    private String status;
    private List<Host> hosts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Map<String, String> configurations;
}
