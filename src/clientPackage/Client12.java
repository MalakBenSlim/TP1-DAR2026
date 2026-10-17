package clientPackage;
import java.io .*;
import java.net .*;
import java.util. Scanner;
public class Client12 {
    public static void main (String[] args) throws IOException{
            System.out.println("Je suis un client pas encore connecté ... ");
            try(
                //les ressources déclarées entre les parenthèses du try ( ... )
                //sont fermées automatiquement à la sortie du bloc c'est pourquoi on n'a pas utilisé socket.close()
                Socket socket = new Socket( "localhost",1234);
                DataInputStream in =new DataInputStream(socket.getInputStream());
                DataOutputStream out=new DataOutputStream(socket.getOutputStream());
                Scanner sc=new Scanner(System.in)) {
                    //readInt() et writeInt() envoient l'entier sur 4 octets, ce qui gère correctement les négatifs.
                    System.out.println("je suis un client connecté");
                    int x;
                    do {
                        System.out.print("Donner un entier (0 pour quitter): ");
                        x = sc.nextInt();
                        out.writeInt(x); // envoyer au serveur
                        if (x != 0) {
                            int resultat = in.readInt();
                            //recevoir le resultat
                            System.out.println(x + " * 5 = " + resultat);
                        }
                    }
                    while (x != 0) ;
                    System.out.println("Fin de la communication");
            }catch (IOException e){
                e.printStackTrace();
            }
    }
}