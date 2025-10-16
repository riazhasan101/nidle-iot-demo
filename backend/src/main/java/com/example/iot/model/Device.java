package com.example.iot.model;
import jakarta.persistence.*;
@Entity public class Device {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 String deviceId; Double temperature; Double humidity;
 public Long getId(){return id;} public void setId(Long i){id=i;}
 public String getDeviceId(){return deviceId;} public void setDeviceId(String d){deviceId=d;}
 public Double getTemperature(){return temperature;} public void setTemperature(Double t){temperature=t;}
 public Double getHumidity(){return humidity;} public void setHumidity(Double h){humidity=h;}
}
