package com.fdev.clustering.cluster.service;

import com.fdev.clustering.cluster.dto.ClusterCreationRequest;
import com.fdev.clustering.cluster.dto.ClusterDTO;
import com.fdev.clustering.cluster.dto.ClusterUpdateRequest;
import com.fdev.clustering.cluster.model.Cluster;
import com.fdev.clustering.cluster.repository.ClusterRepository;
import com.fdev.clustering.cluster.service.ClusterService;
import com.fdev.clustering.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClusterServiceImpl implements ClusterService {

    @Autowired
    private  ClusterRepository clusterRepository;


    @Override
    public ClusterDTO createCluster(ClusterCreationRequest request) {
        Cluster cluster = new Cluster();
        cluster.setName(request.getName());
        cluster.setStatus("CREATED");
        cluster.setCreatedAt(LocalDateTime.now());
        cluster.setUpdatedAt(LocalDateTime.now());
        cluster.setConfigurations(request.getConfigurations());

        Cluster savedCluster = clusterRepository.save(cluster);
        return convertToDTO(savedCluster);
    }

    @Override
    public ClusterDTO getCluster(String clusterId) {
        Cluster cluster = clusterRepository.findById(clusterId)
                .orElseThrow(() -> new ResourceNotFoundException("Cluster not found"));
        return convertToDTO(cluster);
    }

    @Override
    public List<ClusterDTO> getAllClusters() {
        return clusterRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClusterDTO updateCluster(String clusterId, ClusterUpdateRequest request) {
        Cluster cluster = clusterRepository.findById(clusterId)
                .orElseThrow(() -> new ResourceNotFoundException("Cluster not found"));

        cluster.setName(request.getName());
        cluster.setConfigurations(request.getConfigurations());
        cluster.setUpdatedAt(LocalDateTime.now());

        Cluster updatedCluster = clusterRepository.save(cluster);
        return convertToDTO(updatedCluster);
    }

    @Override
    public void deleteCluster(String clusterId) {
        if (!clusterRepository.existsById(clusterId)) {
            throw new ResourceNotFoundException("Cluster not found");
        }
        clusterRepository.deleteById(clusterId);
    }

    @Override
    public ClusterDTO getClusterStatus(String clusterId) {
        Cluster cluster = clusterRepository.findById(clusterId)
                .orElseThrow(() -> new ResourceNotFoundException("Cluster not found"));
        return convertToDTO(cluster);
    }

    private ClusterDTO convertToDTO(Cluster cluster) {
        return new ClusterDTO(
                cluster.getId(),
                cluster.getName(),
                cluster.getStatus(),
                cluster.getHosts(),
                cluster.getCreatedAt(),
                cluster.getUpdatedAt(),
                cluster.getConfigurations()
        );
    }
}
