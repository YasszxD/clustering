package com.fdev.clustering.host.dto;

import lombok.*;


import java.time.LocalDateTime;
import java.util.Map;
@Value
public class HostDTO {
    private String id;
    private String hostName;
    //private String ipAddress;
    private String state;
    private int vcpus;
    private Long memoryMB;
    private String osVariant;
   // private String clusterID;
   // private LocalDateTime createdAt;
    //private LocalDateTime updatedAt;
  //  private Map<String, String> configurations;
    public String getOsVariant() {
        return  this.getOsVariant();
    }
}
