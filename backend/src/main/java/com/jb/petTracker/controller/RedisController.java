package com.jb.petTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.petTracker.model.DeviceLocations;
import com.jb.petTracker.model.Group;
import com.jb.petTracker.model.LocationDetails;
import com.jb.petTracker.service.RedisService;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    // Endpoint za čuvanje grupe
    @PostMapping("/group")
    public void saveGroup(@RequestBody Group group) {
        redisService.saveGroup(group);
    }

    // Endpoint za čuvanje podataka o uređaju
    @PostMapping("/device/{deviceId}/locations")
    public void saveDeviceLocations(@PathVariable String deviceId, @RequestBody DeviceLocations deviceLocations) {
        redisService.saveDeviceLocations(deviceId, deviceLocations);
    }

    // Endpoint za dobijanje grupe po ID-u
    @GetMapping("/group/{groupId}")
    public Group getGroup(@PathVariable String groupId) {
        return redisService.getGroup(groupId);
    }

    // Endpoint za dobijanje poslednjih lokacija uređaja
    @GetMapping("/device/{deviceId}/last-location")
    public List<LocationDetails> getLastLocationsForDevice(@PathVariable String deviceId) {
        return redisService.getLastLocationsForDevice(deviceId);
    }
}