/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversocket;

import Model.Patient;
import Util.PatientServices;
import static Util.PatientServices.patients;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user03
 */
public class Server {

    private static ServerSocket server;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            PatientServices.seed();
//            String[] newPatient = {"","","","","","",""};
            List<String> newPatient = new ArrayList();
            
            for (int i = 0; i < patients.size(); i++) {
                System.out.println("ID: " + patients.get(i).getId());
                System.out.println("Nome: " + patients.get(i).getUserName());
                System.out.println("Frequência Respiratória: " + patients.get(i).getRespiratoryFrequency());
                System.out.println("");
            }
            
            //Atende a pedidos via rede e em alguma porta.
            server = new ServerSocket();
            InetAddress addr = InetAddress.getByName("127.0.0.2"); 
            InetSocketAddress inetSocket = new InetSocketAddress(addr, 60000);
            server.bind(inetSocket);
            
            System.out.println("Servidor iniciado na porta 60000");
            System.out.println("HostAddress="+server.getInetAddress());
            System.out.println("");
            
            //Aguarda um pedido de conexão e tenta aceitá-lo
            Socket client = server.accept();
            System.out.println("IP do cliente: " + client.getInetAddress().getHostAddress());
        
            //Recebe a informação enviada pelo cliente
            Scanner data = new Scanner(client.getInputStream());
            String content;
            int i = 0;
            while(data.hasNextLine()){
                content = data.nextLine();
                System.out.println(content);
                newPatient.add(content);
                
                if(content.equals("GET")){
                    ObjectOutputStream saida = new ObjectOutputStream(client.getOutputStream());
                    saida.flush();
                    saida.writeObject(patients);
                }
                
                if(newPatient.get(0).equals("POST") && i == 7){
                    System.out.println("Antes");
                    PatientServices.create(newPatient.get(1), newPatient.get(2), newPatient.get(3), newPatient.get(4), newPatient.get(5), newPatient.get(6), newPatient.get(7));
                    System.out.println("Depois");
                    ObjectOutputStream saida = new ObjectOutputStream(client.getOutputStream());
                    saida.flush();
                    saida.writeObject(new String("O paciente [" + patients.get(patients.size()-1).getUserName()) + "] foi cadastrado!");
                    newPatient.removeAll(newPatient);
                    System.out.println(patients.size());
                    System.out.println("----CADASTRO---");
                    for (int j = 0; j < patients.size(); j++) {
                        System.out.println("ID: " + patients.get(j).getId());
                        System.out.println("Nome: " + patients.get(j).getUserName());
                        System.out.println("Frequência Respiratória: " + patients.get(j).getRespiratoryFrequency());
                        System.out.println("");
                    }
                    i = 0;
                } else if(newPatient.get(0).equals("PUT") && i == 8){
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
                i++;
            }
            
            for (i = 0; i < patients.size(); i++) {
                System.out.println("ID: " + patients.get(i).getId());
                System.out.println("Nome: " + patients.get(i).getUserName());
                System.out.println("Frequência Respiratória: " + patients.get(i).getRespiratoryFrequency());
                System.out.println("");
            } 
            
            //Finalizando a conexão
            data.close();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            try {
                server.close();
            }

            catch(Exception ec) {}
        } finally{
            try {
                server.close();
            }

            catch(Exception ec) {}
        }
    }
    
}
