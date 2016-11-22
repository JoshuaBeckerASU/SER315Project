package src.components;
import src.DroneController;
import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class ConsolePanel extends JPanel
{
    private JLabel main_L;
    public static JTextArea m_Console_TA;
    private DecimalFormat NumeberFormate;
    private DecimalFormat decimalFormate;
    private int scrWidth, scrHeight;
    
    public ConsolePanel()
	{
        super();
        GraphicsDevice graphicsDevice;
	
        // Obtain window dimensions
        graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().
			getDefaultScreenDevice();
        scrWidth = graphicsDevice.getDisplayMode().getWidth();
        scrHeight = graphicsDevice.getDisplayMode().getHeight();
		createComponents();
		designComponents();
		addComponents();
	}
    
    public void createComponents()
    {
        main_L = new JLabel();
        m_Console_TA = new JTextArea();
    }
    
    public void designComponents()
    {
    	NumeberFormate = new DecimalFormat("###.###");
    	decimalFormate = new DecimalFormat("###");
    	
    	if(scrWidth > 1400)
    	{
    		main_L.setPreferredSize(new Dimension(scrWidth/3-15,scrHeight/2 - 50));
    		m_Console_TA.setPreferredSize(new Dimension(scrWidth/3-20,scrHeight/2 - 70));
    	}
    	else
    	{
    		main_L.setPreferredSize(new Dimension(scrWidth/4-20,scrHeight/2 - 50));
    		m_Console_TA.setPreferredSize(new Dimension(scrWidth/4-20,scrHeight/2 - 70));
    	}
        //main_L.setBorder(BorderFactory.createLineBorder(Color.black));
        
        main_L.setLayout(new FlowLayout());
        
        m_Console_TA.setBackground(Color.black);
        m_Console_TA.setForeground(Color.WHITE);
        m_Console_TA.setEditable(false);
        m_Console_TA.setBorder(BorderFactory.createLineBorder(Color.black));
        
        updateConsole();
    }
    
    public void addComponents()
    {
        main_L.add(m_Console_TA);
        this.add(main_L);
    }
    
    public void updateConsole()
    {
        m_Console_TA.setText("\n\nLatitude:\t"+NumeberFormate.format(DroneController.getLocation().x)
        					 +"\n\nLongitude:\t" +NumeberFormate.format(DroneController.getLocation().y)
        					 + "\n\nBattery:\t" + DroneController.battery + "%"
        					 + "\n\nFlight Time:\t" + decimalFormate.format(DroneController.getFlightTime()) + " hr "
        				     + decimalFormate.format((DroneController.getFlightTime()*(60.0)) % 60) + " Min "
        					 + decimalFormate.format((DroneController.getFlightTime()*(60.0)*(60.0) ) % 60) + " Sec"
        					 + "\n\nSystem Message:\n\n");
    }
    public void updateConsole(String message)
    {
        m_Console_TA.append("\n" + message);
    }
}