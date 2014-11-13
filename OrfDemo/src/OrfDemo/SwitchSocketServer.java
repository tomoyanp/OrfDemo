package OrfDemo;

import java.net.*;
import java.io.*;


class SwitchSocketServer {
	private int port = 9999;
	private int intv_time = 100;

    public void setUpSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            
            while(is.available() >= 0) {
                if(is.available() == 0){
                    continue;
                }

                char[] data = new char[is.available()];
                in.read(data, 0, is.available());

                String message = String.valueOf(data);
                DemoMain.switchPacketCallback(message);
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

