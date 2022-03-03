package feladat2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Kliens {

    public static void main(String[] args) {
        try {
            Socket kapcsolat = new Socket("localhost", 8080);
            DataInputStream szervertol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream szervernek = new DataOutputStream(kapcsolat.getOutputStream());
            Scanner sc = new Scanner(System.in);
            while (true){
                System.out.print("Kérem az oldalt: ");
                int a = sc.nextInt();
                szervernek.writeInt(a);
                szervernek.flush();
                System.out.print("Kérem a másik oldalt: ");
                int b = sc.nextInt();
                szervernek.writeInt(b);
                szervernek.flush();
                int menu;
                do {
                    System.out.println("Melyik menüpontot választja: ");
                    System.out.println("1. Kerülete: \n2. Területe: \n3. Négyzete: \n4. Átlója: \n5. Kilépés: \n");
                    menu = sc.nextInt();
                    szervernek.writeInt(menu);
                    szervernek.flush();
                    System.out.println(szervertol.readUTF());
                }
                while (menu != 5);
            }

        }catch (IOException e){
            System.out.println(e);
        }
    }
}
