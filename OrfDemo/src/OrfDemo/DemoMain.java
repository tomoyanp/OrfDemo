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
        rp = new RaspberrySwitch(addr,x,y,-1);
        rpSwitch.add(rp);
    }
    
    public static void switchConnectCallback(String addr){

    	int flag = 0;
    	if (addr.equals("cc:e1:d5:17:88:6d")){
    		flag = 0;
    	} else if(addr.equals("cc:e1:d5:17:80:a0")) {
    		flag = 1;
    	} else if(addr.equals("cc:e1:d5:17:f2:cd")) {
    		flag = 2;
    	} else if(addr.equals("cc:e1:d5:17:d2:e7")) {
    		flag = 3;
    	}
        ui.setSwitchUi(flag);
        rp = new RaspberrySwitch(addr,ui.getSwitchX(flag),ui.getSwitchY(flag),flag);
        rpSwitch.add((rpSwitch.size()-1),rp);
    }
    
    public static void disconnectedSwitchCallback(String addr){
    	System.out.println("call Disconnected Switch Callback method");
        System.out.println(addr);
    	for(int i = 0; i < rpSwitch.size(); i++){
    		if(rpSwitch.get(i).getAddr().equals(addr)){
    			System.out.println("remove addr = " + addr);
    			ui.removeSwitch(rpSwitch.get(i).getFlag());
    			rpSwitch.remove(i);
    		}
    	}
    }
    
    public static void switchPacketCallback(String addr){
    	int[] srcAxis = new int[2];
    	int[] dstAxis = new int[2];
    	if(addr.equals("192.168.0.7")){
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

        ui.clear();
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
