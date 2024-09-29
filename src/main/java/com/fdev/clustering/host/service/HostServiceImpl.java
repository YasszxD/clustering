package com.fdev.clustering.host.service;


import com.fdev.clustering.cluster.model.Cluster;
import com.fdev.clustering.cluster.repository.ClusterRepository;
import com.fdev.clustering.common.exception.ResourceNotFoundException;
import com.fdev.clustering.host.dto.HostCreationRequest;
import com.fdev.clustering.host.dto.HostDTO;
import com.fdev.clustering.host.model.Host;
import com.fdev.clustering.host.repository.HostRepository;

import jakarta.transaction.Transactional;
import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.DomainInfo;
import org.libvirt.LibvirtException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HostServiceImpl implements HostService{

    private Connect connect;
    @Autowired
    private HostRepository hostRepository;
    @Autowired
    private ClusterRepository clusterRepository;

    public void VMManager() throws LibvirtException {
        this.connect = new Connect("qemu:///system", false);
    }

    @Override
    public HostDTO createHost(HostCreationRequest host) {
        try {
            VMManager();
            System.out.printf("---------------------<");
        } catch (LibvirtException e) {
            throw new RuntimeException(e);
        }
        String xmlDesc = String.format(
                "<domain type='kvm'>" +
                        "  <name>%s</name>" +
                        "  <memory unit='MiB'>%d</memory>" +
                        "  <vcpu>%d</vcpu>" +
                        "  <os>" +
                        "    <type arch='x86_64' machine='pc'>hvm</type>" +
                        "  </os>" +
                        "</domain>", host.getHostname(), host.getMemoryMB(), host.getVcpus());
        try {
            Domain domain = connect.domainDefineXML(xmlDesc);
            hostRepository.save(convertDomainToVM(host,domain));
            return convertToDTO(domain);
        } catch (LibvirtException e) {
            System.err.printf("Libvirt exception: %s%n", e.getMessage());
            throw new RuntimeException("Failed to create virtual machine", e);
        } catch (Exception e) {
            System.err.printf("General exception: %s%n", e.getMessage());
            throw new RuntimeException("Failed to create virtual machine", e);
        }
    }

    @Override
    public void startHost(String name) {
        try {
            Domain domain = connect.domainLookupByName(name);
            domain.create();
        } catch (Exception e) {
            throw new RuntimeException("Failed to start virtual machine", e);
        }
    }

    @Override
    public void stopHost(String name) {
        try {
            Domain domain = connect.domainLookupByName(name);
            domain.shutdown();
        } catch (Exception e) {
            throw new RuntimeException("Failed to stop virtual machine", e);
        }
    }

    @Override
    public void deleteHost(String name) {
        try {
            VMManager();
        } catch (LibvirtException e) {
            throw new RuntimeException(e);
        }
        try {
            Domain domain = connect.domainLookupByName(name);
            domain.undefine();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete virtual machine", e);
        }
    }

    @Override
    public HostDTO getHost(String name) {
        try {
            Domain domain = connect.domainLookupByName(name);
            return convertToDTO(domain);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get virtual machine", e);
        }
    }

    /*@Override
    public HostDTO createHost(HostCreationRequest host) {
        return null;
    }*/

    @Override
    public List<HostDTO> getAllHosts() {
        return null;
    }


       /*
    @Override
    public List<VirtualMachineDTO> getAllVirtualMachines() {
        try {
        return connect.listAllDomains().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to list virtual machines", e);
        }
    }
    */


/*
    @Override
    public HostDTO getHost(String hostId){
        Host host = hostRepository.findById(hostId)
                .orElseThrow(() -> new ResourceNotFoundException("Host not found"));
        return ConvertToDTO(host);
    }

    @Override
    @Transactional
    public HostDTO createHost(HostCreationRequest request) {
        Cluster cluster = clusterRepository.findById(request.getClusterId())
                .orElseThrow(() -> new ResourceNotFoundException("Cluster", "id", request.getClusterId()));

        Host host = new Host();
        host.setHostName(request.getHostname());
        host.setIpAddress(request.getIpAddress());

        host.setCreatedAt(LocalDateTime.now());
        host.setUpdatedAt(LocalDateTime.now());
        host.setConfigurations(request.getConfiguration());
        cluster.addHost(host);
        Host savedHost = hostRepository.save(host);
        return ConvertToDTO(savedHost);
    }*/

   /*
    private HostDTO ConvertToDTO(Host host){
        return new HostDTO(
                host.getId(),
                host.getHostName(),
                host.getIpAddress(),
                host.getCluster().getId(),
                host.getCreatedAt(),
                host.getUpdatedAt(),
                host.getConfigurations()
        );
    }
    */

    private Host convertToBO(HostDTO dto) {
        Host vm = new Host();
        vm.setHostName(dto.getHostName());////shoud checkk
        vm.setState(dto.getState());
        vm.setVcpus(dto.getVcpus());
        vm.setMemoryMB(dto.getMemoryMB());
        vm.setOsVariant(dto.getOsVariant());  // Assuming this is in your DTO
        vm.setCreatedAt(LocalDateTime.now());
        vm.setUpdatedAt(LocalDateTime.now());
        // Note: id is not set as it's generated by the database
        // Note: host is not set as it's currently commented out in your entity
        return vm;
    }

    private HostDTO convertToDTO(Domain domain) throws Exception {
        DomainInfo info = domain.getInfo();
        return new HostDTO(
                String.valueOf(domain.getID()),
                domain.getName(),
                info.state.toString(),
                info.nrVirtCpu,
                info.memory / 1024,
                domain.getOSType().toString()
        );
    }

    private Host convertDomainToVM(HostCreationRequest host , Domain domain) throws Exception {
        DomainInfo info = domain.getInfo();
        Host vm = new Host();

        vm.setHostName(domain.getName());
        vm.setIpAddress("StaticforNow");
        vm.setState(info.state.toString());
        vm.setVcpus(info.nrVirtCpu);
        vm.setMemoryMB(info.memory / 1024);
        vm.setOsVariant(domain.getOSType().toString());
        vm.setCreatedAt(LocalDateTime.now());
        vm.setUpdatedAt(LocalDateTime.now());
        vm.setCluster(new Cluster(host.getClusterId()));

        // Note: id is not set as it's generated by the database
        // Note: host is not set as it's currently commented out in your entity

        return vm;
    }


    /**
     * hedhom ll adresss
     */
    /*
    private VirtualMachine convertDomainToVM(Domain domain, Connect connect) throws Exception {
        DomainInfo info = domain.getInfo();
        VirtualMachine vm = new VirtualMachine();

        vm.setName(domain.getName());
        vm.setState(info.state.toString());
        vm.setVcpus(info.nrVirtCpu);
        vm.setMemoryMB(info.memory / 1024);
        vm.setOsVariant(domain.getOSType().toString());
        vm.setCreatedAt(LocalDateTime.now());
        vm.setUpdatedAt(LocalDateTime.now());

        // Attempt to get IP address
        String ipAddress = getVMIpAddress(domain, connect);
        vm.setIpAddress(ipAddress);

        return vm;
    }

    private String getVMIpAddress(Domain domain, Connect connect) throws LibvirtException {
        String[] macAddresses = domain.getInterfaceNames();
        if (macAddresses != null && macAddresses.length > 0) {
            for (String macAddress : macAddresses) {
                NetworkInterface iface = connect.interfaceLookupByMACString(macAddress);
                if (iface != null) {
                    String[] ipAddresses = iface.getIPs();
                    if (ipAddresses != null && ipAddresses.length > 0) {
                        return ipAddresses[0];  // Return the first IP address found
                    }
                }
            }
        }
        return null;  // Return null if no IP address is found
    }
    */



}
