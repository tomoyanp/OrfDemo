package OrfDemo;

import java.util.ArrayList;
import java.util.Random;


class DemoMain {
    private	static ArrayList<RaspberrySwitch> rpSwitch = new ArrayList<RaspberrySwitch>(1);
    private static DemoUi ui;
    private static RaspberrySwitch rp;
    private static Random rnd;

    public static void main(String args[]){
        rnd = new Random();

        SwitchReadySocketThread rSockTh = new SwitchReadySocketThread();
        rSockTh.start();
        
        SwitchPacketSocketThread sSockTh = new SwitchPacketSocketThread();
        sSockTh.start();
        
        SwitchDisconnectedSocketThread dSockTh = new SwitchDisconnectedSocketThread();
        dSockTh.start();
        

        ui = new DemoUi();


    }
    
    public static void controllerConnectCallback(String addr,int x,int y){
//        System.out.println(addr);
//        System.out.println(ui.getSwitchX());
//        System.out.println(ui.getSwitchY());
        rp = new RaspberrySwitch(addr,x,y);
        rpSwitch.add(rp);
    }
    
    public static void switchConnectCallback(String addr){

        ui.setSwitchUi();
        rp = new RaspberrySwitch(addr,ui.getSwitchX(),ui.getSwitchY());
        rpSwitch.add((rpSwitch.size()-1),rp);
    }
    
    public static void disconnectedSwitchCallback(String addr){
    	System.out.println("call Disconnected Switch Callback method");
    	for(int i = 0; i < rpSwitch.size(); i++){
    		if(rpSwitch.get(i).getAddr().equals(addr)){
    			System.out.println("remove addr = " + addr);
    			rpSwitch.remove(i);
    		}
    	}
    }
    
    public static void switchPacketCallback(String addr){
    	int[] srcAxis = new int[2];
    	int[] dstAxis = new int[2];
    	if(addr.equals("192.168.0.1")){
    		for(int i = 0; i < (rpSwitch.size()-1); i++){
    			srcAxis = rpSwitch.get(i).switchGetAxis();
    			dstAxis = rpSwitch.get(i+1).switchGetAxis();
    			ui.drawTransmittionAxis(srcAxis[0],srcAxis[1],dstAxis[0],dstAxis[1],"0");
    			try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	} else {
    		for(int i = (rpSwitch.size() - 1); i > 0; i--){
    			srcAxis = rpSwitch.get(i).switchGetAxis();
    			dstAxis = rpSwitch.get(i - 1).switchGetAxis();
    			ui.drawTransmittionAxis(srcAxis[0],srcAxis[1],dstAxis[0],dstAxis[1],"1");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    		}
    	}
//    	for(int i = 0; i < rpSwitch.size();i++){
//    		srcAxis = rpSwitch.get(i).switchConnectAPI(src);
//    		if(srcAxis[0] != -1 && srcAxis[1] != -1){
//    			break;
//    		}
//    	}
//    	for(int i = 0; i < rpSwitch.size();i++){
//    		dstAxis = rpSwitch.get(i).switchConnectAPI(dst);
//    		if(dstAxis[0] != -1 && dstAxis[1] != -1){
//    			break;
//    		}
//    	}
//    	ui.drawTransmittionAxis(srcAxis[0],srcAxis[1],dstAxis[0],dstAxis[1],flag);
//    	int ran = rnd.nextInt(7);
//    	if(ran == 2){
    		ui.clear();
//    	}
    }
} 


class SwitchReadySocketThread extends Thread {
    public void run() {
        ReadySocketServer rSockSv = new ReadySocketServer();
        rSockSv.setUpSocket();
    }
}

class SwitchPacketSocketThread extends Thread {
    public void run() {
        SwitchSocketServer sSockSv = new SwitchSocketServer();
        sSockSv.setUpSocket();
    }
}

class SwitchDisconnectedSocketThread extends Thread {
	public void run() {
		DisconnectedSocketServer dSockSv = new DisconnectedSocketServer();
		dSockSv.setUpSocket();
	}
}
