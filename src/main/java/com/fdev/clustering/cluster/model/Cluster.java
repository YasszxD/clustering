package com.fdev.clustering.cluster.model;

import com.fdev.clustering.host.model.Host;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Cluster")
public class Cluster {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "cluster" ,fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Host> hosts = new ArrayList<>();


    @ElementCollection
    @CollectionTable(name = "cluster_configurations", joinColumns = @JoinColumn(name = "cluster_id"))
    @MapKeyColumn(name = "config_key")
    @Column(name = "config_value")
    private Map<String, String> configurations;

    public void addHost(Host host) {
        hosts.add(host);
        host.setCluster(this);
    }

    public void removeHost(Host host) {
        hosts.remove(host);
        host.setCluster(null);
    }

    public Cluster(String id) {
        super();
        this.id = id;
    }

}