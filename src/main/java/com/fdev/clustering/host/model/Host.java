package com.fdev.clustering.host.model;

import com.fdev.clustering.cluster.model.Cluster;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hosts")
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String hostName;

    @Column
    private String ipAddress;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private int vcpus;

    @Column(nullable = false)
    private Long memoryMB;

    @Column(nullable = false)
    private String osVariant;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cluster_id", nullable = false)
    private Cluster cluster;

    @ElementCollection
    @CollectionTable(name = "host_configurations", joinColumns = @JoinColumn(name = "host_id"))
    @MapKeyColumn(name = "config_key")
    @Column(name = "config_value")
    private Map<String, String> configurations;



}
