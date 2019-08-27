package ChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);

        try {
            System.out.println("Client started");
            System.out.println("Give an Ip Address :");

            String host=input.next();
            Socket socket= new Socket(host,9806);

            BufferedReader userin=new BufferedReader((new InputStreamReader(System.in)));

            while (true){
                System.out.println("Enter msg");
                String str= userin.readLine();
                PrintWriter out =new PrintWriter(socket.getOutputStream(),true);
                out.println(str);

                BufferedReader inputBuffer =new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(inputBuffer.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
