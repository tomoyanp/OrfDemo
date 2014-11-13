package OrfDemo;

import java.net.*;
import java.io.*;


class ReadySocketServer {
	private int port = 8888;
	private int intv_time = 100;


    public void setUpSocket() {
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
                Thread.sleep(intv_time);
             }

         } catch (IOException e) {
             e.printStackTrace();
         } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
