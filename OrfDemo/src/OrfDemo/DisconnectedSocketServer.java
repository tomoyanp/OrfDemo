package OrfDemo;


import java.net.*;
import java.io.*;


public class DisconnectedSocketServer {
	private String src;
	private String dst;
  	private String flag;

    public void setUpSocket() {
    	int port = 7777;
    	int intv_time = 100;
        try {
            System.out.println("Start");
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress() + "accept now");
            InputStream is = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            
            int counter = 0;
            while(is.available() >= 0) {
                if(is.available() == 0){
                    continue;
                }


                char[] data = new char[is.available()];
                in.read(data, 0, is.available());

                String message = String.valueOf(data);
//                System.out.println(message.substring(17, 34));
//                this.src = message.substring(0, 17);
//                this.dst = message.substring(17, 34);
//                this.flag = message.substring(34, 35);
                	//DemoMain.switchPacketCallback(this.src,this.dst);
              	//SwitchPacketCallbackThread sTh = new SwitchPacketCallbackThread();
               	//sTh.run(this.src,this.dst);
                DemoMain.disconnectedSwitchCallback(message);
                //System.out.println(data);
                System.out.println("--------------");
                Thread.sleep(intv_time);
             }

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
