
import javax.swing.*;
import java.awt.*;
import components.*;
import java.awt.GraphicsDevice;


public class MainWindow extends JFrame
{
    private MainPanel m_Main_P;
    private ConsolePanel m_Console_P;
    private ThirdPersonPanel m_thrdPer_P;
    private TopDownPanel m_tpDwn_P;
    private ControllerPanel m_Controller_P;
    private JPanel m_Inner_P, m_LargeScreen_P, m_SmallScreen_P;
    private int scrWidth, scrHeight;
    public MainWindow(String title)
    {
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
        m_thrdPer_P = new ThirdPersonPanel();
        m_Controller_P = new ControllerPanel();
        m_tpDwn_P = new TopDownPanel();
        
        m_LargeScreen_P = new JPanel();
        m_SmallScreen_P = new JPanel();
    }
    
    public void designComponents()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        m_Main_P.setPreferredSize(new Dimension(scrWidth-10,scrHeight-10));
        m_Main_P.setLayout(new FlowLayout());
        m_Inner_P.setLayout(new BoxLayout(m_Inner_P,BoxLayout.Y_AXIS));
        
        m_Console_P.setLayout(new FlowLayout());
        m_thrdPer_P.setLayout(new FlowLayout());
        m_Controller_P.setLayout(new FlowLayout());
        m_tpDwn_P.setLayout(new FlowLayout());
        
        m_LargeScreen_P.setLayout(new FlowLayout());
        m_SmallScreen_P.setLayout(new BoxLayout(m_SmallScreen_P, BoxLayout.X_AXIS));
    }
    
    public void addComponents()
    {
        m_LargeScreen_P.add(m_thrdPer_P);
        
        m_SmallScreen_P.add(m_Console_P);
        m_SmallScreen_P.add(m_Controller_P);
        m_SmallScreen_P.add(m_tpDwn_P);
        
        m_Inner_P.add(m_LargeScreen_P);
        m_Inner_P.add(m_SmallScreen_P);
        m_Main_P.add(m_Inner_P);
        
        this.add(m_Main_P);
        this.setLocation(0,0);
    }
}