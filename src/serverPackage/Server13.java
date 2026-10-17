package serverPackage;
import java.io.*;
import java.net.*;
public class Server13 {
    private static final String IP_SERVEUR= "192.168.1.108";
    private static final int PORT=1234;

    public static void main(String[] args) throws IOException {

        try (ServerSocket socketServeur = new ServerSocket()) {
            // 1. Écouter sur l'IP réelle et le port
            InetSocketAddress adresse= new InetSocketAddress(IP_SERVEUR, PORT); //l'adresse IP+port.
            socketServeur.bind(adresse);
            System.out.println("Serveur en attente sur " + adresse);
            //Il reste bloqué sur `accept()` jusqu'à l'arrivée d'un client.
            // 2. Attendre un client

            try (Socket socket = socketServeur.accept();
                 DataInputStream in= new DataInputStream(socket.getInputStream());
                 DataOutputStream out= new DataOutputStream(socket.getOutputStream())) {
                //l'IP et le port de la machine distante (client)
                System.out.println("Client connecté : " + socket.getInetAddress().getHostAddress());
                while (true) {
                    int choix;
                    choix=in.readInt(); // reçoit le num de l'operation
                    if (choix == 0) {
                        System.out.println("Le client a envoyé 0 : fin de la communication");
                        break;
                    }
                    double x = in.readDouble();
                    double y = in.readDouble();
                    String reponse;
                    switch (choix) {
                        case 1:
                            reponse =x + " + " + y + " = " + (x + y);
                            break;
                        case 2:
                            reponse =x + " - " + y + " = " + (x - y);
                            break;
                        case 3:
                            reponse =x + " * " + y + " = " + (x * y);
                            break;
                        case 4:
                            if (y == 0) {
                                reponse = "Erreur : division par zéro impossible";
                            } else {
                                reponse = x + " / " + y + " = " + (x / y);
                            }
                            break;
                        default:
                            reponse = "Erreur : opération inconnue";
                    }
                    out.writeUTF(reponse);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}