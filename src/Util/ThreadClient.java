
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
        //Recebe a informação enviada pelo cliente
        Scanner data;
        //Armazena temporariamente as novas informações recebidas pela requisição.
        List<String> newPatient = new ArrayList();
        
        try {
            data = new Scanner(client.getInputStream());
            String content; //Armazena uma linha por vez dos dados recebidos.
            int i = 0; //Verifica a quantidade de linhas recebidas.
            
            while (data.hasNextLine()) {
                content = data.nextLine();
                newPatient.add(content);

                i++;
                //Caso a rota de requisição seja "GET /list", a lista de pacientes é retornada ao cliente.
                if (content.equals("GET /list")) {
                    ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    output.writeObject(patients);
                    output.flush();
                    output.writeObject(new String("200 OK"));
                }
                //Caso a rota de requisição seja "POST /create", um novo paciente é criado e adicionado na lista de pacientes e é retornada uma mensagem de confirmação.
                if (newPatient.get(0).equals("POST /create") && i == 8) {
                    PatientServices.create(newPatient.get(1), newPatient.get(2), newPatient.get(3), newPatient.get(4), newPatient.get(5), newPatient.get(6), newPatient.get(7));

                    ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    output.writeObject(new String("200 OK"));

                    newPatient.removeAll(newPatient);

                    i = 0;
                //Caso a rota de requisição seja "PUT /update", os dados de um paciente são alterados na lista de pacientes e é retornada uma mensagem de confirmação.
                } else if (newPatient.get(0).equals("PUT /update") && i == 8) {
                    PatientServices.update(newPatient.get(1), newPatient.get(2), newPatient.get(3), newPatient.get(4), newPatient.get(5), newPatient.get(6), newPatient.get(7));

                    ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
                    output.flush();
                    output.writeObject(new String("200 OK"));

                    newPatient.removeAll(newPatient);

                    i = 0;
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
}
