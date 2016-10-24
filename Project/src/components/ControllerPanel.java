package components;
import java.awt.*;
import javax.swing.*;

public class ControllerPanel extends JPanel
{
    private JLabel label;
    private int scrWidth, scrHeight;
    public ControllerPanel()
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
        label.setBorder(BorderFactory.createLineBorder(Color.darkGray));
    }
    
    public void addComponents()
    {
        this.add(label);
    }
}