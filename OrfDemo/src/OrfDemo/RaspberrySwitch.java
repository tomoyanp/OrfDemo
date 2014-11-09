package OrfDemo;

public class RaspberrySwitch {
	private String address;
	private int switchX;
	private int switchY;
	
	RaspberrySwitch(String addr,int x,int y){
		this.address = addr;
		this.switchX = x;
		this.switchY = y;
		System.out.println(this.address);
		System.out.println(this.switchX);
		System.out.println(this.switchY);
	}

	
	public int[] switchConnectAPI(String addr){
		int[] switchAxis = new int[2];
		if(this.address.equals(addr)){
			switchAxis[0] = this.switchX;
			switchAxis[1] = this.switchY;
		}else{
			switchAxis[0] = -1;
			switchAxis[1] = -1;
		}
		
		return switchAxis;

	}
	
	public int[] switchGetAxis(){
		int[] switchAxis = new int[2];
		switchAxis[0] = this.switchX;
		switchAxis[1] = this.switchY;
		
		return switchAxis;
	}
	
	public String getAddr(){
		return this.address;
	}
}
