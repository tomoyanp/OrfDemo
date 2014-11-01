package OrfDemo;

import java.net.*;
import java.io.*;


class ReadySocketServer {


    public void setUpSocket() {
    	int port = 8888;
    	int intv_time = 100;
        try {
            System.out.println("Start");
           	ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress() + "accept now");
            InputStream is = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            
            while(true) {

                if(is.available() == 0){
                    continue;
                }


                char[] data = new char[is.available()];
                in.read(data, 0, is.available());
                String addr = String.valueOf(data);
                DemoMain.switchConnectCallback(addr);
   //             Thread.sleep(intv_time);
             }

         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
