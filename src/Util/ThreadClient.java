/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import static Util.PatientServices.patients;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Optimus 2020
 */
public class ThreadClient implements Runnable{
    
    private Socket client;

    public ThreadClient(Socket client) {
      this.client = client;
    }

    @Override
    public void run() {
        //Recebe a informação enviada pelo cliente
            Scanner data;
            List<String> newPatient = new ArrayList();
        try {
            data = new Scanner(client.getInputStream());
            String content;
            int i = 0;
            while(data.hasNextLine()){
                content = data.nextLine();
                System.out.println(content);
                newPatient.add(content);
                
                i++;
                if(content.equals("GET /list")){
                    ObjectOutputStream saida = new ObjectOutputStream(client.getOutputStream());
                    saida.flush();
                    saida.writeObject(patients);
                }
                if(newPatient.get(0).equals("POST /create") && i == 8){
                    PatientServices.create(newPatient.get(1), newPatient.get(2), newPatient.get(3), newPatient.get(4), newPatient.get(5), newPatient.get(6), newPatient.get(7));
                    
                    ObjectOutputStream saida = new ObjectOutputStream(client.getOutputStream());
                    saida.flush();
                    saida.writeObject(new String("O paciente [" + patients.get(patients.size()-1).getUserName()) + "] foi cadastrado!");
                    
                    newPatient.removeAll(newPatient);
                    
                    System.out.println("----CADASTRO---");
                    for (int j = 0; j < patients.size(); j++) {
                        System.out.println("ID: " + patients.get(j).getId());
                        System.out.println("Nome: " + patients.get(j).getUserName());
                        System.out.println("Frequência Respiratória: " + patients.get(j).getRespiratoryFrequency());
                        System.out.println("");
                    }
                    i = 0;
                } else if(newPatient.get(0).equals("PUT /update") && i == 8){
                    PatientServices.update(newPatient.get(1), newPatient.get(2), newPatient.get(3), newPatient.get(4), newPatient.get(5), newPatient.get(6), newPatient.get(7));
                    
                    ObjectOutputStream saida = new ObjectOutputStream(client.getOutputStream());
                    saida.flush();
                    saida.writeObject(new String("O paciente [" + patients.get(patients.size()-1).getUserName()) + "] foi cadastrado!");
                    
                    newPatient.removeAll(newPatient);
                    
                    System.out.println("----UPDATE---");
                    for (int j = 0; j < patients.size(); j++) {
                        System.out.println("ID: " + patients.get(j).getId());
                        System.out.println("Nome: " + patients.get(j).getUserName());
                        System.out.println("Frequência Respiratória: " + patients.get(j).getRespiratoryFrequency());
                        System.out.println("");
                    }
                    i = 0;
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
}
