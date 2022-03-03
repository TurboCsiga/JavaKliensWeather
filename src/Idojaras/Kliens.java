package Idojaras;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Kliens {
    public static void main(String[] args) {
        try {
                Socket kapcsolat = new Socket("localhost", 8080);
                DataInputStream szervertol = new DataInputStream(kapcsolat.getInputStream());
                DataOutputStream szervernek = new DataOutputStream(kapcsolat.getOutputStream());
                Scanner sc2 = new Scanner(System.in);
                int menu;
                do {
                    System.out.println("Melyik menüpontot választja: ");
                    System.out.println("1. Listázás: \n2. Előrejelzések száma: \n3. Kilépés: \n");
                    menu = sc2.nextInt();
                    szervernek.writeInt(menu);
                    szervernek.flush();

                    System.out.println(szervertol.readUTF());
                }
                while (menu != 5);
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
