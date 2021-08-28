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
        create("Joao", "11", "35.5", "96.7", "83", "112");
        create("Carlos", "14", "35.1", "98.4", "76", "117");
        create("Maria", "10", "34.9","97.3", "88", "128");
        create("Antônio", "12", "35.7", "94.7", "68", "108");
    }
    
    public static Patient get(String id){
        for (int i = 0; i < patients.size(); i++) {
            if(patients.get(i).getId().equals(id)){
                return patients.get(i);
            }
        }
        return null;
    }
    
    public static void create(String userName, String respiratoryFrequency, String temperature, String bloodOxygen, String heartRate, String bloodPressure){
        Patient patient = new Patient(userName, respiratoryFrequency, temperature, bloodOxygen, heartRate, bloodPressure);
        patients.add(patient);
    }
    
    public static void update(String id, String userName, String respiratoryFrequency, String temperature, String bloodOxygen, String heartRate, String bloodPressure){
        Patient patient = new Patient(userName, respiratoryFrequency, temperature, bloodOxygen, heartRate, bloodPressure);
        patients.add(patient);
    }
    
    public static void delete(String id){
        for (int i = 0; i < patients.size(); i++) {
            if(patients.get(i).getId().equals(id)){
                patients.remove(i);
            }
        }
    }
}
