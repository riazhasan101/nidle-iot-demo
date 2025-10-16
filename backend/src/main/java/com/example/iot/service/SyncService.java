package com.example.iot.service;

import com.example.iot.model.Device;
import com.example.iot.repository.DeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import com.mongodb.client.*;
import org.bson.Document;

@Service
public class SyncService {

    @Autowired
    private DeviceRepository deviceRepository;

    private final MongoClient mongoClient = MongoClients.create("mongodb://mongodb:27017");
    private final MongoCollection<Document> collection = mongoClient
            .getDatabase("iot_data")
            .getCollection("readings");

    @Scheduled(fixedRate = 10000) // every 10 seconds
    public void syncData() {
        try {
            for (Document doc : collection.find().limit(5)) {
                Device device = new Device();
                device.setDeviceId(doc.getString("device_id"));
                device.setTemperature(doc.getDouble("temperature"));
                device.setHumidity(doc.getDouble("humidity"));
                deviceRepository.save(device);
            }
            System.out.println("✅ Synced latest IoT data from MongoDB → MariaDB");
        } catch (Exception e) {
            System.err.println("⚠️ Sync error: " + e.getMessage());
        }
    }
}
