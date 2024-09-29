package com.fdev.clustering.host.config;

import org.libvirt.Connect;
import org.libvirt.LibvirtException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HostConfiguration {
    @Bean
    public Connect libvirtConnect() throws LibvirtException {
        Connect connect = new Connect("qemu:///system");
        if (connect.isConnected()) {
            System.out.println("Successfully connected to libvirt.");
        } else {
            System.out.println("Failed to connect to libvirt.");
        }
        return connect;
    }
}
