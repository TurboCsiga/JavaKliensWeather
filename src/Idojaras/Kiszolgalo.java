package Idojaras;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Kiszolgalo implements Runnable{

    private HashMap<String, Idojaras> elorejelzesek;
    private Socket kapcsolat;
    public Kiszolgalo(Socket kapcsolat){
        this.kapcsolat = kapcsolat;
        elorejelzesek = new HashMap<>();
        Beolvas();
    }


    @Override
    public void run() {
        try {

            DataInputStream ugyfeltol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnek = new DataOutputStream(kapcsolat.getOutputStream());

            int menu;

            do {
                menu = ugyfeltol.readInt();

                switch (menu){
                    case 1:ugyfelnek.writeUTF(kiir());
                        ugyfelnek.flush();
                        break;
                    case 2:ugyfelnek.writeUTF(kiir2());
                        ugyfelnek.flush();
                        break;
                    case 3: ugyfelnek.writeUTF("Kilépés");
                        ugyfelnek.flush();
                        break;
                    case 4: eliminate();
                }
            }

            while (menu != 3);

        } catch (IOException e) {

            System.out.println(e);

        }
    }

    public void Beolvas(){

        try{
            BufferedReader br = new BufferedReader(new FileReader("weather.txt"));
            br.readLine();
            String sor = br.readLine();
            while (sor != null){
                Idojaras i = new Idojaras(sor);
                String megye = i.getMegye();
                elorejelzesek.put(megye, i);
                sor = br.readLine();
            }
            /*for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()) {
                System.out.println(entry.getValue());
            }*/
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public String kiir() {
        String s = "";
        for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()) {
            s += entry.getValue() + "\n";
        }
        return s;
    }

    public String kiir2() {
        return "Előrejelzések száma: " + elorejelzesek.size();
    }

    public void eliminate() throws IOException{
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("shutdown -s -t 0");
            System.exit(0);
    }
}
