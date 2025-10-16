package com.example.iot.controller;
import com.example.iot.model.Device; import com.example.iot.repository.DeviceRepository;
import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/devices")
public class DeviceController {
 private final DeviceRepository repo;
 public DeviceController(DeviceRepository r){this.repo=r;}
 @GetMapping public List<Device> all(){return repo.findAll();}
 @PostMapping public Device add(@RequestBody Device d){return repo.save(d);}
}
