package src;
import javax.swing.*;
import java.awt.*;
import src.components.*;
import java.awt.GraphicsDevice;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.imageio.*;

public class MainWindow extends JFrame
{
	public static boolean isLeft, isRight, isNormal;
    private MainPanel m_Main_P;
    private static ConsolePanel m_Console_P;
    private ThirdPersonLabel m_thrdPer_L;
    private TopDownPanel m_tpDwn_P;
    private ControllerPanel m_Controller_P;
    private JPanel m_Inner_P, m_LargeScreen_P, m_SmallScreen_P;
    private int scrWidth, scrHeight;
    private DroneController m_DrnCtrl;
    public MainWindow(String title)
    {
    	MainWindow.isLeft = false;
    	MainWindow.isNormal = false;
    	MainWindow.isRight = false;
        m_DrnCtrl = DroneController.newInstance(10,10,10);
        	// Obtain window dimensions
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().
			getDefaultScreenDevice();
        scrWidth = graphicsDevice.getDisplayMode().getWidth();
        scrHeight = graphicsDevice.getDisplayMode().getHeight();
        
		createComponents();
		designComponents();
		addComponents();
    }
    
    public void createComponents()
    {
        m_Main_P = new MainPanel();
        m_Inner_P = new JPanel();
        
        m_Console_P = new ConsolePanel();
        m_thrdPer_L = new ThirdPersonLabel();
        m_Controller_P = new ControllerPanel();
        m_tpDwn_P = new TopDownPanel();
        
        m_LargeScreen_P = new JPanel();
        m_SmallScreen_P = new JPanel();
    }
    
    public void designComponents()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        m_Main_P.setPreferredSize(new Dimension(scrWidth-10,scrHeight-10));
        m_Main_P.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        m_Inner_P.setLayout(new BoxLayout(m_Inner_P,BoxLayout.Y_AXIS));
        
        m_Console_P.setLayout(new FlowLayout());
        m_thrdPer_L.setLayout(new FlowLayout());
        m_Controller_P.setLayout(new FlowLayout());
        m_tpDwn_P.setLayout(new FlowLayout());
        
        m_LargeScreen_P.setLayout(new FlowLayout());
        m_SmallScreen_P.setLayout(new BoxLayout(m_SmallScreen_P, BoxLayout.X_AXIS));
    }
    
    public void addComponents()
    {
    	setKeyBind();
        m_LargeScreen_P.add(m_thrdPer_L);
        
        m_SmallScreen_P.add(m_Console_P);
        m_SmallScreen_P.add(m_Controller_P);
        //m_SmallScreen_P.add(m_tpDwn_P);
        
        m_Inner_P.add(m_LargeScreen_P);
        m_Inner_P.add(m_SmallScreen_P);
        m_Main_P.add(m_Inner_P);
        
        this.add(m_Main_P);
        this.setLocation(0,0);
    }
    
    public static void update()
    {
        m_Console_P.updateConsole();
    }
    
    public static void updateConsole(String message)
    {
       m_Console_P.updateConsole(message);
    }
    
	/**setKeyBind
	* maps the key strokes with there actions
	* and decides what to do with each event.
	**/
	private class KeyAction extends AbstractAction
    {
		private String m_Command;
		KeyAction()
		{
			
		}
		
		KeyAction(String command)
		{
			m_Command = command;
		}
        public void actionPerformed(ActionEvent event)
        {
			switch(m_Command)
			{
                case "EXIT":
                        System.exit(1);// DOUBLE CHECK WHERE TO SEND...
                        break;
                case "W": DroneController.getLocation().y += .002;
                		  update();
                        break;
                case "S": DroneController.getLocation().y -= .002;
      		  			update();
      		  			break;
                case "A": DroneController.getLocation().x -= .002;
      		  			update();
      		  			break;
                case "D": DroneController.getLocation().x += .002;
                		update();
                		break;
			}
		}
    }
	/**setKeyBind
	* maps the key strokes with there actions
	* and decides what to do with each event.
	**/
	private void setKeyBind()
	{
		m_Main_P.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),"EXIT");
		m_Main_P.getActionMap().put( "EXIT", new KeyAction("EXIT"));
		m_Main_P.getInputMap().put(KeyStroke.getKeyStroke("W"),"W");
		m_Main_P.getActionMap().put( "W", new KeyAction("W"));
		m_Main_P.getInputMap().put(KeyStroke.getKeyStroke("S"),"S");
		m_Main_P.getActionMap().put( "S", new KeyAction("S"));
		m_Main_P.getInputMap().put(KeyStroke.getKeyStroke("A"),"A");
		m_Main_P.getActionMap().put( "A", new KeyAction("A"));
		m_Main_P.getInputMap().put(KeyStroke.getKeyStroke("D"),"D");
		m_Main_P.getActionMap().put( "D", new KeyAction("D"));
		m_Main_P.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),"EXIT");
		m_Main_P.getActionMap().put( "EXIT", new KeyAction("EXIT"));

	}
    /*
    private void init()
    {
        m_DrnCtrl = DroneController.newInstance(0,0,10);
        JLabel label = new JLabel();
        Graphics g = this.getGraphics();
        //g.drawImage(loadGameImage("ball.png", 20,20), 100,100, this);
        
        
        
    }
    
    public void paint(Graphics g){
        Image img = createImage(getWidth(), getHeight());
        Graphics gr = img.getGraphics();
        draw(gr);
        g.drawImage(img, 0, 0, this);
    }

    public void draw(Graphics g){
        super.paint(g);
        g.drawRect(100, 100, 200, 100);
        g.drawImage(loadGameImage("ball.png",40,40), 100,100, this);
    }*
	/*private Image loadImage(String name, int w, int h)
	{
		//String path = "";
		//path = System.getProperty("user.dir");
		//path = path.replace('\\','/');
		//path = path.replaceAll("Source", "Assets/GUI/GameImages/" + name);
		Image img;
		
		try 
		{
			img = ImageIO.read(new File(name));
			img = img.getScaledInstance(w, h,  java.awt.Image.SCALE_AREA_AVERAGING);
			return img;
		} catch (IOException ex) 
		{
			System.out.println("FIle Not Found\nFile Path: " + name);System.exit(0);
		}
		return null;
	}*/
}