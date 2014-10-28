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

    public DemoUi() {
        createFrame();
        setControllerUi();
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
        ImageIcon img = new ImageIcon("/Users/tomop/java/OrfDemo/src/OrfDemo/smartphone.png");
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

    public void setControllerUi(){
    	ImageIcon controller = new ImageIcon("/Users/tomop/java/OrfDemo/src/OrfDemo/controller.png");
    	JLabel label = new JLabel(controller);
    	contentPanel.add(label);
    	label.setBounds(1100,300,controller.getIconWidth(),controller.getIconHeight());
    	setVisible(true);
    }
    
    public void drawTransmittionAxis(int srcX,int srcY,int dstX,int dstY){
    	this.srcX = srcX;
    	this.srcY = srcY;
    	this.dstX = dstX;
    	this.dstY = dstY;
    	repaint();
    	setVisible(true);
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	System.out.println("draw");
    	Graphics2D g2 = (Graphics2D)g;
    	g2.setColor(Color.BLUE);
    	g2.setStroke(new BasicStroke(10));
    	Line2D line = new Line2D.Double(this.srcX + 60,this.srcY + 90,this.dstX + 60,this.dstY + 90);
    	System.out.println(this.srcX);
    	System.out.println(this.srcY);
    	System.out.println(this.dstX);
    	System.out.println(this.dstY);
    	g2.draw(line);
    }
    
    public void clear(){
    	System.out.println("aaaaaaaaaaaaaaaaa");
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
/*    	this.setControllerUi();
    	int counterTmp = this.counter;
    	this.counter = 0;
    	for(int i = 1; i <= counterTmp; i++){
    		setSwitchUi();
    	}
    	*/
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
        