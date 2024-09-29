package com.fdev.clustering.host.controller;


import com.fdev.clustering.host.dto.HostCreationRequest;
import com.fdev.clustering.host.dto.HostDTO;

import com.fdev.clustering.host.service.HostService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
@RequiredArgsConstructor
public class HostController {

    private  final HostService hostService;

    @PostMapping
    public ResponseEntity<HostDTO> createHost(@RequestBody HostCreationRequest hostCreationRequest) {
        System.out.printf("---->1");
        HostDTO createdHost = hostService.createHost(hostCreationRequest);
        System.out.printf("---->2");
        return ResponseEntity.ok(createdHost);
    }

    @PostMapping("/{name}/start")
    public ResponseEntity<Void> startHost(@PathVariable String name) {
        hostService.startHost(name);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{name}/stop")
    public ResponseEntity<Void> stopHost(@PathVariable String name) {
        hostService.startHost(name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteHost(@PathVariable String name) {
        hostService.deleteHost(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<HostDTO> getVM(@PathVariable String name) {
        HostDTO host = hostService.getHost(name);
        return ResponseEntity.ok(host);
    }

   /* @GetMapping
    public ResponseEntity<List<HostDTO>> getAllVMs() {
        List<HostDTO> vms = hostService.getAllVirtualMachines();
        return ResponseEntity.ok(vms);
    }
    */

    /*

    @PostMapping("/{host}")
    public ResponseEntity<HostDTO> createHost(@PathVariable HostCreationRequest hostCreationRequest){
        HostDTO host = hostService.createHost(hostCreationRequest);
        return new ResponseEntity<>(host, HttpStatus.CREATED);
    }

    @GetMapping("/{hostId}")
    public ResponseEntity<HostDTO> getHost(@PathVariable String hostId){
        HostDTO host = hostService.getHost(hostId);
        return ResponseEntity.ok(host);
    }
*/

}
