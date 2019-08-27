package UDP_used_chat;

import java.io.*;
import java.net.*;

public class Client {
    public static byte buf[] = new byte[1024];
    public static int cport = 222,sport=555;
    public static void main(String[] args) throws IOException {
        DatagramSocket ClientSC=new DatagramSocket(cport);
        DatagramPacket dpC=new DatagramPacket(buf, buf.length);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        InetAddress ia=InetAddress.getLocalHost();
        System.out.println("Client Started");
        System.out.println("Type some text if u want");
        while (true){
            String str=new String(br.readLine());
            buf=str.getBytes();

            ClientSC.send(new DatagramPacket(buf,str.length(),ia,sport));

            ClientSC.receive(dpC);

            String clmsg=new String(dpC.getData(),0,dpC.getLength());
            System.out.println("                                            server :"+clmsg);

        }
    }
}
/*        Scanner input=new Scanner(System.in);

        try {
            System.out.println("Request  for server Ip");
            System.out.print("Please Enter server Ip Address:");
            String serverIp=input.next();
            DatagramSocket ds =new DatagramSocket();
           // Socket socket= new Socket(serverIp,9806);

            BufferedReader userMessage=new BufferedReader((new InputStreamReader(System.in)));
            System.out.println("Connected With Server"+"\n"+"Start messaging");

            while (true){

                String str= userMessage.readLine();
                PrintWriter out =new PrintWriter(socket.getOutputStream(),true);
                out.println(str);
                BufferedReader inputBuffer =new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(inputBuffer.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
