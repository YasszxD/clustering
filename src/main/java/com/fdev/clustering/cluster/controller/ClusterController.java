package com.fdev.clustering.cluster.controller;

import com.fdev.clustering.cluster.dto.ClusterCreationRequest;
import com.fdev.clustering.cluster.dto.ClusterDTO;
import com.fdev.clustering.cluster.dto.ClusterUpdateRequest;
import com.fdev.clustering.cluster.service.ClusterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/clusters")
@RequiredArgsConstructor
public class ClusterController {

    private final ClusterService clusterService;

    @PostMapping
    public ResponseEntity<ClusterDTO> createCluster(@RequestBody ClusterCreationRequest clusterCreationRequest) {
        ClusterDTO cluster = clusterService.createCluster(clusterCreationRequest);
        return new ResponseEntity<>(cluster, HttpStatus.CREATED);
    }

    @GetMapping("/{clusterId}")
    public ResponseEntity<ClusterDTO> getCluster(@PathVariable String clusterId) {
        ClusterDTO cluster = clusterService.getCluster(clusterId);
        return ResponseEntity.ok(cluster);
    }

    @GetMapping
    public ResponseEntity<List<ClusterDTO>> getAllClusters() {
        List<ClusterDTO> clusters = clusterService.getAllClusters();
        return ResponseEntity.ok(clusters);
    }

    @PutMapping("/{clusterId}")
    public ResponseEntity<ClusterDTO> updateCluster(@PathVariable String clusterId, @RequestBody ClusterUpdateRequest request) {
        ClusterDTO cluster = clusterService.updateCluster(clusterId, request);
        return ResponseEntity.ok(cluster);
    }

    @DeleteMapping("/{clusterId}")
    public ResponseEntity<Void> deleteCluster(@PathVariable String clusterId) {
        clusterService.deleteCluster(clusterId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{clusterId}/status")
    public ResponseEntity<ClusterDTO> getClusterStatus(@PathVariable String clusterId) {
        ClusterDTO cluster = clusterService.getClusterStatus(clusterId);
        return ResponseEntity.ok(cluster);
    }
}
