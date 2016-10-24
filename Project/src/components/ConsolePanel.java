package components;
import javax.swing.*;
import java.awt.*;

public class ConsolePanel extends JPanel
{
    private JLabel label;
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
        label = new JLabel();
    }
    
    public void designComponents()
    {
        label.setPreferredSize(new Dimension(scrWidth/3-15,scrHeight/2 - 50));
        label.setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    public void addComponents()
    {
        this.add(label);
    }
}