package com.fdev.clustering.cluster.service;

import com.fdev.clustering.cluster.dto.ClusterCreationRequest;
import com.fdev.clustering.cluster.dto.ClusterDTO;
import com.fdev.clustering.cluster.dto.ClusterUpdateRequest;

import java.util.List;

public interface ClusterService {
    ClusterDTO createCluster(ClusterCreationRequest request);
    ClusterDTO getCluster(String clusterId);
    List<ClusterDTO> getAllClusters();
    ClusterDTO updateCluster(String clusterId, ClusterUpdateRequest request);
    void deleteCluster(String clusterId);
    ClusterDTO getClusterStatus(String clusterId);
}
