package com.fdev.clustering.host.repository;

import com.fdev.clustering.host.model.Host;
import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host,String> {
}
