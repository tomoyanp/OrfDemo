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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;


class DemoUi extends JFrame {
    private JPanel panel;
    private Container contentPanel;
    private int counter = 1;
    private int switchX;
    private int switchY;
    private int srcX;
    private int srcY;
    private int dstX;
    private int dstY;
    private String colorFlag = "1";

    public DemoUi() {
        createFrame();
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

    public void setSwitchUi(){
        ImageIcon img = new ImageIcon("/Users/tomop/java/OrfDemo/src/OrfDemo/raspberrypi.png");
        JLabel label = new JLabel(img);
        contentPanel.add(label);
        this.switchX = 0;
        this.switchY = 0;
        if(counter % 2 == 0){
        	this.switchX = 900 - (150*counter);
        	this.switchY = 50;
        	label.setBounds(switchX,switchY,img.getIconWidth(),img.getIconHeight());
        }else{
        	this.switchX = 900 - (150*counter);
        	this.switchY = 550;
        	label.setBounds(switchX,switchY,img.getIconWidth(),img.getIconHeight());
        }
        setVisible(true);
        counter++;
    }
    
    public void setClientUi(){
    	ImageIcon img = new ImageIcon("/Users/tomop/java/OrfDemo/src/OrfDemo/smartphone.png");
        JLabel label = new JLabel(img);
        contentPanel.add(label);
        this.switchX = 0;
        this.switchY = 0;
       	label.setBounds(switchX,switchY,img.getIconWidth(),img.getIconHeight());
        setVisible(true);
        DemoMain.controllerConnectCallback("clientnode", 0, 0);
    }

    public void setControllerUi(){
    	ImageIcon controller = new ImageIcon("/Users/tomop/java/OrfDemo/src/OrfDemo/controller.png");
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
    public int getCounter() {
        return counter;
    }
    public int getSwitchX(){
    	return this.switchX;
    }
    
    public int getSwitchY() {
    	return this.switchY;
    }
}
        