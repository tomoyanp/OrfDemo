package OrfDemo;

import java.util.ArrayList;


class DemoMain {
    private	static ArrayList<RaspberrySwitch> rpSwitch = new ArrayList<RaspberrySwitch>(1);
    private static DemoUi ui;
    private static RaspberrySwitch rp;

    public static void main(String args[]){

        SwitchReadySocketThread rSockTh = new SwitchReadySocketThread();
        rSockTh.start();
        
        SwitchPacketSocketThread sSockTh = new SwitchPacketSocketThread();
        sSockTh.start();

        ui = new DemoUi();


    }
    
    public static void switchConnectCallback(String addr){
        ui.setSwitchUi();
        rp = new RaspberrySwitch(addr,ui.getSwitchX(),ui.getSwitchY());
        rpSwitch.add(rp);
    }
    
    public static void switchPacketCallback(String src,String dst){
    	int[] srcAxis = new int[2];
    	int[] dstAxis = new int[2];
    	System.out.println("###########################");
    	System.out.println(rpSwitch.size());
    	for(int i = 0; i < rpSwitch.size();i++){
    		srcAxis = rpSwitch.get(i).switchConnectAPI(src);
    		if(srcAxis[0] != -1 && srcAxis[1] != -1){
    			break;
    		}
    	}
    	for(int i = 0; i < rpSwitch.size();i++){
    		dstAxis = rpSwitch.get(i).switchConnectAPI(dst);
    		if(dstAxis[0] != -1 && dstAxis[1] != -1){
    			break;
    		}
    	}
    	System.out.println("###############################");
    	System.out.println(srcAxis[0]);
    	System.out.println(srcAxis[1]);
    	System.out.println(dstAxis[0]);
    	System.out.println(dstAxis[1]);
    	ui.drawTransmittionAxis(srcAxis[0],srcAxis[1],dstAxis[0],dstAxis[1]);
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

