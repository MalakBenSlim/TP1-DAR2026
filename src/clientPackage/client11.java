package clientPackage;

import java.io .*;
import java.net .*;
public class client11 {

        public static void main (String[] args) throws IOException {
            try {

                Socket socket = new Socket("localhost",1234);
                System.out.println("Je suis un client pas encore connecté ... ");
                System.out.println("je suis un client connecté");

                // La dernière étape : Fermer socket
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();


            }
        }
}
