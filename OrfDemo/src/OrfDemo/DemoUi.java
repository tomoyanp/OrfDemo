package OrfDemo;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;


class DemoUi extends JFrame {
    private Container contentPanel;
    private int counter = 1;
    private int switchX;
    private int switchY;
    private int srcX;
    private int srcY;
    private int dstX;
    private int dstY;
    private String colorFlag = "1";
    private int device_num = 4;
    private int[] deviceX = new int[device_num];
    private int[] deviceY = new int[device_num];
    private JLabel[] deviceLabel = new JLabel[device_num];
    private JLabel[] textLabel = new JLabel[device_num];
    private ImageIcon[] img = new ImageIcon[device_num];
    private ImageIcon[] sImg = new ImageIcon[device_num];


    public DemoUi() {
        createFrame();
        setDevice();
        setControllerUi();
        setClientUi();
    }

    public void createFrame(){
        setTitle("Demo window");
        setBounds(0,0,1400,800);
        setVisible(true);
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPanel = getContentPane();
        contentPanel.setLayout(null);
    	this.clear();
    }

    
    public void setDevice() {
    	for(int i = 0; i < 5; i++){
    		img[i] = new ImageIcon("/home/tomoyan/OrfDemo/OrfDemo/src/OrfDemo/raspberrypi.png");
    		deviceLabel[i] = new JLabel(img[i]);
    		sImg[i] = new ImageIcon("/home/tomoyan/OrfDemo/OrfDemo/src/OrfDemo/switch.png");
    	}
    	for(int i = 0; i < this.device_num; i++){
    		textLabel[i] = new JLabel(String.valueOf(i+1));
    		textLabel[i].setFont(new Font("Serif", Font.PLAIN, 50));
    		contentPanel.add(deviceLabel[i]);
    		contentPanel.add(textLabel[i]);
    		if(i % 2 == 0){
    			deviceX[i] = 900 - (200*i);
    			deviceY[i] = 50;
    			deviceLabel[i].setBounds(deviceX[i],deviceY[i],img[i].getIconWidth(),img[i].getIconHeight());
    			textLabel[i].setBounds(deviceX[i],deviceY[i]-40,50,50);
    		} else {
    			deviceX[i] = 900 - (200*i);
    			deviceY[i] = 550;
    			deviceLabel[i].setBounds(deviceX[i],deviceY[i],img[i].getIconWidth(),img[i].getIconHeight());   			
    			textLabel[i].setBounds(deviceX[i],deviceY[i]-10,50,50);
    		}
    	}
        setVisible(true);
    }
    public void setSwitchUi(int flag){
    	deviceLabel[flag].setIcon(sImg[flag]);
       	deviceLabel[flag].setBounds(deviceX[flag],deviceY[flag],sImg[flag].getIconWidth(),sImg[flag].getIconHeight());
        setVisible(true);
    }
    
    public void removeSwitch(int flag) {
    	deviceLabel[flag].setIcon(img[flag]);
    	deviceLabel[flag].setBounds(deviceX[flag], deviceY[flag], img[flag].getIconWidth(), img[flag].getIconHeight());
    	setVisible(true);
    }
    
    public void setClientUi(){
    	ImageIcon img = new ImageIcon("/home/tomoyan/OrfDemo/OrfDemo/src/OrfDemo/smartphone.png");
        JLabel label = new JLabel(img);
        contentPanel.add(label);
        this.switchX = 0;
        this.switchY = 0;
       	label.setBounds(switchX,switchY,img.getIconWidth(),img.getIconHeight());
        setVisible(true);
        DemoMain.controllerConnectCallback("clientnode", 0, 0);
    }

    public void setControllerUi(){
    	ImageIcon controller = new ImageIcon("/home/tomoyan/OrfDemo/OrfDemo/src/OrfDemo/controller.png");
    	JLabel label = new JLabel(controller);
    	contentPanel.add(label);
    	this.switchX = 1100;
    	this.switchY = 300;
    	label.setBounds(this.switchX,this.switchY,controller.getIconWidth(),controller.getIconHeight());
    	setVisible(true);
    	DemoMain.controllerConnectCallback("cc:e1:d5:17:80:a0",1100,300);
    }
    
    public void drawTransmittionAxis(int srcX,int srcY,int dstX,int dstY, String flag){
    	this.srcX = srcX;
    	this.srcY = srcY;
    	this.dstX = dstX;
    	this.dstY = dstY;
    	this.colorFlag = flag;
    	repaint();
    	setVisible(true);
    }
    
    public void paint(Graphics g) {
    	//super.paint(g);
    	Graphics2D g2 = (Graphics2D)g;
    	if(this.colorFlag.equals("1")){
    		g2.setColor(Color.BLUE);
    	}else{
    		g2.setColor(Color.RED);
    	}
    	g2.setStroke(new BasicStroke(10));
    	Line2D line = new Line2D.Double(this.srcX + 60,this.srcY + 90,this.dstX + 60,this.dstY + 90);
    	g2.draw(line);
    }
    
    public void clear(){
    	Graphics g = getGraphics();
    	//super.paint(g);
    	try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	Graphics2D g2 = (Graphics2D)g;
    	g2.setColor(getBackground());
    	g2.clearRect(0, 0, getWidth(), getHeight());
    	super.paint(g);
    	g2.dispose();
    	g.dispose();
    }

    public int getSwitchX(int flag){
    	return this.deviceX[flag];
    }
    
    public int getSwitchY(int flag) {
    	return this.deviceY[flag];
    }
}
        
