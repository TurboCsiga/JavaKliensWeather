package feladat;

import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSOutput;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Kliens {

    public static void main(String[] args) {
        try {
            Socket kapcsolat = new Socket("localhost", 8080);
            DataInputStream szervertol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream szerverre = new DataOutputStream(kapcsolat.getOutputStream());

            Scanner sc = new Scanner(System.in);
            while (true){
                System.out.println("Kérem a kör sugarát: ");
                int sugar = sc.nextInt();
                szerverre.writeInt(sugar);
                szerverre.flush();
                System.out.printf("Kerület: %.2f\n", szervertol.readDouble());
                System.out.printf("Terület: %.2f", szervertol.readDouble());
            }

        }catch (IOException e) {
            System.out.println(e);
        }
    }

}
