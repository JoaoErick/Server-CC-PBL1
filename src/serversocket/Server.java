
package serversocket;

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
            
            //Inicializando o servidor no endereço e porta especificados.
            server = new ServerSocket();
            InetAddress addr = InetAddress.getByName("localhost"); 
            InetSocketAddress inetSocket = new InetSocketAddress(addr, 60000);
            server.bind(inetSocket);
            
            System.out.println("---- Servidor em execução ----");
            System.out.println("| Endereço: " + server.getInetAddress());
            System.out.println("| Porta: " + server.getLocalPort());
            System.out.println("------------------------------");
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
