package UDP_used_chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static byte buf[] = new byte[1024];
    public static int cport = 2222,sport=5555;
    public static void main(String[] args) throws IOException {
        DatagramSocket ServerSC=new DatagramSocket(sport);
        DatagramPacket dpS=new DatagramPacket(buf, buf.length);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        InetAddress ia=InetAddress.getLocalHost();
        System.out.println("Server Started");
        while (true){
            ServerSC.receive(dpS);
            String clmsg=new String(dpS.getData(),0,dpS.getLength());
            System.out.println("                                            Client :"+clmsg);
            String Svmsg =new String(br.readLine());
            buf=Svmsg.getBytes();
            ServerSC.send(new DatagramPacket(buf,Svmsg.length(),ia,cport));
        }
    }
}
/*
     Scanner inpu =new Scanner(System.in);
        try {

            System.out.println("Hey client Enter My Ip :10.100.51.130");
            ServerSocket ss=new ServerSocket(9806);
            Socket socket=ss.accept();
            System.out.println("Client Connected"+"\n"+"Start Massaging");
            while(true){
                BufferedReader serverIn =new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String str=serverIn.readLine();

                System.out.println("                                                    Client: "+str);
                System.out.print("Server :");
                str =inpu.nextLine();
                PrintWriter out =new PrintWriter(socket.getOutputStream(),true);
                out.println("                                                    Server: "+str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
 */