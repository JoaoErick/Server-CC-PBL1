/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.UUID;

/**
 *
 * @author Optimus 2020
 */
public class Patient {
    private String id;
    private String userName;
    private String respiratoryFrequency;
    private String temperature;
    private String bloodOxygen;
    private String heartRate;
    private String bloodPressure;
    
    public Patient(String userName, String respiratoryFrequency, String temperature, String bloodOxygen, String heartRate, String bloodPressure) {
        this.id = UUID.randomUUID().toString().substring(9, 13);
        this.userName = userName;
        this.respiratoryFrequency = respiratoryFrequency;
        this.temperature = temperature;
        this.bloodOxygen = bloodOxygen;
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
    }

    public Patient() {
    }

    public String getId() {
        return id;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRespiratoryFrequency() {
        return respiratoryFrequency;
    }

    public void setRespiratoryFrequency(String respiratoryFrequency) {
        this.respiratoryFrequency = respiratoryFrequency;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getBloodOxygen() {
        return bloodOxygen;
    }

    public void setBloodOxygen(String bloodOxygen) {
        this.bloodOxygen = bloodOxygen;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }
    
}
