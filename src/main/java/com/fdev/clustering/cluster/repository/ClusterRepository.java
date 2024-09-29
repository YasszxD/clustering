package com.fdev.clustering.cluster.repository;

import com.fdev.clustering.cluster.model.Cluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClusterRepository extends JpaRepository<Cluster,String> {
}
