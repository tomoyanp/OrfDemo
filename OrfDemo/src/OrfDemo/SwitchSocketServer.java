package OrfDemo;

import java.net.*;
import java.io.*;


class SwitchSocketServer {
	private String src;
	private String dst;


    public void setUpSocket() {
    	int port = 9999;
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
                System.out.println("--------------");

                String message = String.valueOf(data);
//                System.out.println(message.substring(17, 34));
                this.src = message.substring(0, 17);
                this.dst = message.substring(17, 34);
                System.out.println("souce mac address");
                System.out.println(this.src);
                System.out.println("destnation mac address");
                System.out.println(this.dst);

/*                if(counter == 0){
                	this.src = String.valueOf(data);
                	counter++;
                }else{
                	this.dst = String.valueOf(data);
                	counter = 0;
                	//DemoMain.switchPacketCallback(this.src,this.dst);
                	SwitchPacketCallbackThread sTh = new SwitchPacketCallbackThread();
                	sTh.run(this.src,this.dst);
                }
                */
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

class SwitchPacketCallbackThread extends Thread {
	public void run(String src,String dst) {
		DemoMain.switchPacketCallback(src, dst);
	}
}
