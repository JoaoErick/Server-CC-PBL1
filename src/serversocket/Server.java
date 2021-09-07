
package serversocket;

import Util.PatientServices;
import static Util.PatientServices.patients;
import Util.ThreadClient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Servidor Socket responsável por receber requisições de clientes, processar e 
 * retornar uma resposta adequada.
 * 
 * @author João Erick Barbosa
 */
public class Server {

    private static ServerSocket server;
    
    public static void main(String[] args) {
        try {
            PatientServices.seed();
            
            for (int i = 0; i < patients.size(); i++) {
                System.out.println("ID: " + patients.get(i).getId());
                System.out.println("Nome: " + patients.get(i).getUserName());
                System.out.println("Frequência Respiratória: " + patients.get(i).getRespiratoryFrequency());
                System.out.println("");
            }
            
            //Inicializando o servidor no endereço e porta especificados.
            server = new ServerSocket();
            InetAddress addr = InetAddress.getByName("127.0.0.2"); 
            InetSocketAddress inetSocket = new InetSocketAddress(addr, 60000);
            server.bind(inetSocket);
            
            System.out.println("Servidor iniciado na porta 60000");
            System.out.println("HostAddress="+server.getInetAddress());
            System.out.println("");
            
            while(true) {
                //Aguarda um pedido de conexão e tenta aceitá-lo
                Socket client = server.accept();
                System.out.println("IP do cliente: " + client.getInetAddress().getHostAddress() + " | Porta: " + client.getPort());
                //Inicia thread do cliente
                ExecutorService es = Executors.newCachedThreadPool();
                es.execute(new ThreadClient(client));
            }
            
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
