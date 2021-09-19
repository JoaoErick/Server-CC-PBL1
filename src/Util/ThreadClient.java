
package Util;

import Model.Patient;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Thread responsável por tratar as requições do cliente que foi aceito pelo servidor.
 * 
 * @author João Erick Barbosa
 */
public class ThreadClient implements Runnable{
    
    private Socket client;

    public ThreadClient(Socket client) {
      this.client = client;
    }

    @Override
    public void run() {
        try {
            
            while(true){
                ObjectInputStream input = new ObjectInputStream(client.getInputStream());
                String request = (String)input.readObject(); //Armazena o método HTTP e a rota da requisição.
                
                //Caso a rota de requisição seja "GET /list", a lista de pacientes é retornada ao cliente.
                if (request.equals("GET /list")) {
                    ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    output.writeObject(PatientServices.list());
                    output.flush();
                    output.writeObject(new String("200 OK"));
                    
                //Caso a rota de requisição seja "POST /create", um novo paciente é criado e adicionado na lista de pacientes e é retornada uma mensagem de confirmação.
                } else if (request.equals("POST /create")) {
                    
                    Patient patient = (Patient)input.readObject(); //Armazena as informações do paciente recebidas pela requisição.
                    PatientServices.create(
                            patient.getId(), 
                            patient.getUserName(), 
                            patient.getRespiratoryFrequency(), 
                            patient.getTemperature(), 
                            patient.getBloodOxygen(), 
                            patient.getHeartRate(), 
                            patient.getBloodPressure()
                    );

                    ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    output.writeObject(new String("201 Created"));
                    
                    TxtGenerator.generate("data.txt", PatientServices.list());

                //Caso a rota de requisição seja "PUT /update", os dados de um paciente são alterados na lista de pacientes e é retornada uma mensagem de confirmação.
                } else if (request.equals("PUT /update")) {
                    
                    Patient patient = (Patient)input.readObject(); //Armazena as informações do paciente recebidas pela requisição.
                    PatientServices.update(
                            patient.getId(), 
                            patient.getUserName(), 
                            patient.getRespiratoryFrequency(), 
                            patient.getTemperature(), 
                            patient.getBloodOxygen(), 
                            patient.getHeartRate(), 
                            patient.getBloodPressure()
                    );

                    ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    output.writeObject(new String("200 OK"));
                    
                    TxtGenerator.generate("data.txt", PatientServices.list());
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            try {
                client.close();
            }
            catch(Exception ec) {}
            System.out.println("");
            System.out.println("Conexão finalizada com o cliente:");
            System.out.println("\tIP: " + client.getInetAddress().getHostAddress() + " | Porta: " + client.getPort());
            System.out.println("------------------------------");
            System.out.println("");
        }
            
    }
    
}
