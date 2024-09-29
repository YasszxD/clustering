package com.fdev.clustering.host.service;


import com.fdev.clustering.host.dto.HostCreationRequest;
import com.fdev.clustering.host.dto.HostDTO;



import java.util.List;


public interface HostService {


    HostDTO createHost(HostCreationRequest host);

    void startHost(String name);

    void stopHost(String name);

    void deleteHost(String name);

    HostDTO getHost(String name);

    //HostDTO createHost(HostCreationRequest host);
    List<HostDTO> getAllHosts();

}