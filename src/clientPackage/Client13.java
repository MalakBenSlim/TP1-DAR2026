package clientPackage;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client13 {
    private static final String IP_SERVEUR= "192.168.1.108";
    private static final int PORT=1234;

    public static void main (String[] args) throws IOException{
        try(
            Socket socket = new Socket();
            Scanner sc=new Scanner(System.in)) {
            sc.useLocale(Locale.US); // les décimaux se saisissent avec un point (3.5)
            // 1. Se connecter à l'IP et au port du serveur,avec un délai maximal de 5 secondes.
            socket.connect(new InetSocketAddress(IP_SERVEUR, PORT), 5000);
            System.out.println("je suis un client connecté: "+ IP_SERVEUR + ":" + PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //Confirme la connexion en affichant l’adresse du serveur.
            int choix;
            do {
                System.out.println("\n===== MENU =====");
                System.out.println("1. Addition");
                System.out.println("2. Soustraction");
                System.out.println("3. Multiplication");
                System.out.println("4. Division");
                System.out.println("0. Quitter");
                System.out.print("Votre choix : ");
                choix = sc.nextInt();

                if (choix < 0||choix > 4) {
                    System.out.println("Choix invalide, réessayez.");
                    continue;
                }
                out.writeInt(choix); // envoyer au serveur
                if (choix != 0) {
                    System.out.print("Donner x : ");
                    double x = sc.nextDouble();
                    System.out.print("Donner y : ");
                    double y = sc.nextDouble();
                    //envoyer les variables au serveur
                    out.writeDouble(x);
                    out.writeDouble(y);
                    System.out.println("Résultat : " + in.readUTF());
                }
            } while (choix!= 0);
            System.out.println("Fin de la communication");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
