
package Util;

import Model.Patient;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Classe que gerar um arquivo .txt com dados de todos os pacientes.
 * @author João Erick Barbosa
 */
public class TxtGenerator {
    
    /**
     * Método responsável por gerar um arquivo txt com os dados dos pacientes.
     * Esse arquivo será consumido por uma API.
     * @param path - Caminho do arquivo.
     * @param patients - Lista de pacientes.
     */
    public static void generate(String path, List<Patient> patients) {
        File file = new File(path);
        
        try {

            /*Cria um novo arquivo caso não exista.*/
            if (!file.exists()) {
                file.createNewFile();
            }

            /*Buffer que fará a escrita dos dados no arquivo.*/
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            
            for (int i = 0; i < patients.size(); i++) {
                writer.append(patients.get(i).getId() + "\n");
                writer.append(patients.get(i).getUserName() + "\n");
                writer.append(patients.get(i).getRespiratoryFrequency() + "\n");
                writer.append(patients.get(i).getTemperature()+ "\n");
                writer.append(patients.get(i).getBloodOxygen()+ "\n");
                writer.append(patients.get(i).getHeartRate()+ "\n");
                writer.append(patients.get(i).getBloodPressure()+ "\n");
            }

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Erro ao fazer a escrita no arquivo!");
            System.out.println(ioe);
        }
    
    }
}
