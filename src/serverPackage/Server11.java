package serverPackage;
import java.io .*;
import java.net .*;

public class Server11 {
        public static void main(String[] args) throws IOException {
            try {
                // La première étape :
                ServerSocket socketServeur = new ServerSocket(1234);
                System.out.println("Je suis un serveur en attente la connexion d'un client ");

                // La deuxième étape
                Socket socket = socketServeur.accept();
                System.out.println("un client est connecté");

                // La troisieme étape
                socket.close();
                socketServeur.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

