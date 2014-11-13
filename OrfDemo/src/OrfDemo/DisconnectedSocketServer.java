package OrfDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;


import java.net.*;
import java.io.*;


public class DisconnectedSocketServer {
    private int port = 7777;
   	private int intv_time = 100;

    public void setUpSocket() {
    	while(true){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            
//            while(is.available() >= 0) {
//                if(is.available() == 0){
//                    continue;
//                }

                char[] data = new char[is.available()];
                in.read(data, 0, is.available());

                System.out.println("aaaaaaaaaaaaaaa");
                String message = String.valueOf(data);
                DemoMain.disconnectedSwitchCallback(message);
                Thread.sleep(intv_time);
 //            }

             in.close();
             socket.close();
             serverSocket.close();
         } catch (InterruptedException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    	}
    }
}


/*public class DisconnectedSocketServer {
    private int port = 7777;
   	private int intv_time = 100;
   	private DatagramSocket recSocket;
   	private SocketAddress sockAddress;

   	public void setUpSocket(){
   		byte[] buf = new byte[256];
   		try {
			recSocket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		DatagramPacket packet = new DatagramPacket(buf,buf.length);
   		try {
			recSocket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		
   		int len = packet.getLength();
   		String msg = new String(buf,0,len);
   		System.out.println(msg);
   		DemoMain.disconnectedSwitchCallback(msg);
   	}
	
}*/