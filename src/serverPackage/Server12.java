package serverPackage;
import java.io.*;
import java.net.*;

public class Server12 {
    public static void main(String[] args) throws IOException {
        try (ServerSocket socketServeur = new ServerSocket(1234)) {
            System.out.println("Je suis un serveur en attente de connexion d'un client");
            try (
                    Socket socket = socketServeur.accept();
                    DataInputStream in = new DataInputStream(socket.getInputStream()); //de type primitive
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream())
            ) {
                System.out.println("Un client est connecté");
                while (true) {
                    try {
                        // Recevoir l'entier envoyé par le client
                        int x = in.readInt();
                        if (x == 0) {
                            System.out.println("Le client a envoyé 0 : fin de la communication");
                            break;
                        }

                        System.out.println("Reçu : " + x);
                        // Calculer et renvoyer le résultat au client
                        out.writeInt(x * 5);
                    } catch (EOFException e) {
                        System.out.println("Le client s'est déconnecté");
                        break;
                    }
                }
            }
        }
    }


}
