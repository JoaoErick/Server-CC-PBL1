/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Patient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Optimus 2020
 */
public class PatientServices {
    public static List<Patient> patients = new ArrayList();
    
    public static List<Patient> list(){
        return patients;
    }
    
    public static void seed(){
        create("123", "Joao", "11", "35.5", "96.7", "83", "112");
        create("456", "Carlos", "14", "35.1", "98.4", "76", "117");
        create("789", "Maria", "10", "34.9","97.3", "88", "128");
        create("000", "Antônio", "12", "35.7", "94.7", "68", "108");
    }
    
    public static Patient get(String id){
        for (int i = 0; i < patients.size(); i++) {
            if(patients.get(i).getId().equals(id)){
                return patients.get(i);
            }
        }
        return null;
    }
    
    public static void create(String id, String userName, String respiratoryFrequency, String temperature, String bloodOxygen, String heartRate, String bloodPressure){
        Patient patient = new Patient(id, userName, respiratoryFrequency, temperature, bloodOxygen, heartRate, bloodPressure);
        patients.add(patient);
    }
    
    public static void update(String id, String userName, String respiratoryFrequency, String temperature, String bloodOxygen, String heartRate, String bloodPressure){
        if(get(id) != null){
            for (int i = 0; i < patients.size(); i++) {
                if(patients.get(i).getId().equals(id)){
                    patients.get(i).setRespiratoryFrequency(respiratoryFrequency);
                    patients.get(i).setTemperature(temperature);
                    patients.get(i).setBloodOxygen(bloodOxygen);
                    patients.get(i).setHeartRate(heartRate);
                    patients.get(i).setBloodPressure(bloodPressure);
                }
            }
        }
    }
    
    public static void delete(String id){
        for (int i = 0; i < patients.size(); i++) {
            if(patients.get(i).getId().equals(id)){
                patients.remove(i);
            }
        }
    }
}
