package com.example.iot.repository;
import com.example.iot.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DeviceRepository extends JpaRepository<Device,Long>{}
