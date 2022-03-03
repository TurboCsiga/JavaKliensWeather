package feladat2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Ugyfelkiszolgalo implements Runnable {
    Socket kapcsolat;
    public Ugyfelkiszolgalo(Socket kapcsolat){
        this.kapcsolat = kapcsolat;
    }


    @Override
    public void run() {
        try {
            DataInputStream ugyfeltol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnek = new DataOutputStream(kapcsolat.getOutputStream());
            while (true){
                int a = ugyfeltol.readInt();
                int b = ugyfeltol.readInt();
                int menu;
                do {
                    menu = ugyfeltol.readInt();
                    switch (menu){
                        case 1: ugyfelnek.writeUTF(kerulet(a, b)); break;
                        case 2: ugyfelnek.writeUTF(terulet(a, b)); break;
                        case 3: ugyfelnek.writeUTF(negyzet(a, b)); break;
                        case 4: ugyfelnek.writeUTF(atlo(a, b)); break;
                        case 5: ugyfelnek.writeUTF("Kilépés");
                    }
                }
                while (menu != 5);
            }

        }catch (IOException e) {
            System.out.println(e);;
        }
    }

    private String negyzet(int a, int b) {
        if (a == b){
            return "A téglalap négyzet";
        }else {
            return "A téglalap nem négyzet";
        }
    }

    private String atlo(int a, int b) {
        return "A téglalap átlói: " + Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    private String kerulet(int a, int b) {
        return "A téglalap kerulete: " + (2 * (a + b));
    }

    private String terulet(int a, int b) {
        return "A teglalap terulete: " + (a * b);
    }
}
