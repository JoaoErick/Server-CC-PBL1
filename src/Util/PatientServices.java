
package Util;

import Model.Patient;
import java.util.ArrayList;
import java.util.List;

/**
 * Camada de serviço que realiza operações relacionadas a Model Patient.
 * @author João Erick Barbosa
 */
public class PatientServices {
    public static List<Patient> patients = new ArrayList();
    
    /**
     * Retorna a lista de pacientes.
     * @return List<Patient> - Lista de pacientes.
     */
    public static List<Patient> list(){
        return patients;
    }
    
    public static void seed(){
        create("1234", "Joao", "11", "35.5", "96.7", "83", "112");
        create("4567", "Carlos", "14", "35.1", "98.4", "76", "117");
        create("7898", "Maria", "10", "34.9","97.3", "88", "128");
        create("0000", "Antônio", "12", "35.7", "94.7", "68", "108");
    }
    
    /**
     * Busca um paciente na lista pelo id passado por parâmetro.
     * @param id - id do paciente a ser buscado.
     * @return Patient - Paciente encontrado na lista.
     */
    public static Patient get(String id){
        for (int i = 0; i < patients.size(); i++) {
            if(patients.get(i).getId().equals(id)){
                return patients.get(i);
            }
        }
        return null;
    }
    
    /**
     * Cria um novo paciente e adiciona-o na lista de pacientes.
     * @param id - id do paciente.
     * @param userName - Nome do paciente.
     * @param respiratoryFrequency - Valor da frequência respiratória do paciente.
     * @param temperature - Valor da temperatura do paciente.
     * @param bloodOxygen - Valor da oxigenação do sangue do paciente.
     * @param heartRate - Valor da frequência cardíaca do paciente.
     * @param bloodPressure - Valor da pressão arterial do paciente.
     */
    public static void create(String id, String userName, String respiratoryFrequency, String temperature, String bloodOxygen, String heartRate, String bloodPressure){
        Patient patient = new Patient(id, userName, respiratoryFrequency, temperature, bloodOxygen, heartRate, bloodPressure);
        patients.add(patient);
    }
    
    /**
     * Atualiza um paciente presente na lista de pacientes.
     * @param id - id do paciente.
     * @param userName - Nome do paciente.
     * @param respiratoryFrequency - Valor da frequência respiratória do paciente.
     * @param temperature - Valor da temperatura do paciente.
     * @param bloodOxygen - Valor da oxigenação do sangue do paciente.
     * @param heartRate - Valor da frequência cardíaca do paciente.
     * @param bloodPressure - Valor da pressão arterial do paciente.
     */
    public static void update(String id, String userName, String respiratoryFrequency, String temperature, String bloodOxygen, String heartRate, String bloodPressure){
        if(get(id) != null){
            for (int i = 0; i < patients.size(); i++) {
                if(patients.get(i).getId().equals(id)){
                    patients.get(i).setRespiratoryFrequency(respiratoryFrequency);
                    patients.get(i).setTemperature(temperature);
                    patients.get(i).setBloodOxygen(bloodOxygen);
                    patients.get(i).setHeartRate(heartRate);
                    patients.get(i).setBloodPressure(bloodPressure);
                    
                    patients.get(i).verifySeriousness();
                }
            }
        }
    }
    
    /**
     * Remove um paciente da lista de pacientes.
     * @param id - id do paciente.
     */
    public static void delete(String id){
        for (int i = 0; i < patients.size(); i++) {
            if(patients.get(i).getId().equals(id)){
                patients.remove(i);
            }
        }
    }
}
