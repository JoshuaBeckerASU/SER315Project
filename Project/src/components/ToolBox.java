package components;
import java.awt.*;



public class ToolBox 
{
    public static int width, height;
    
    public static void ToolBox()
    {
        	GraphicsDevice graphicsDevice;
	
	// Obtain window dimensions
	graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().
			getDefaultScreenDevice();
	width = graphicsDevice.getDisplayMode().getWidth();
	height = graphicsDevice.getDisplayMode().getHeight();
    }
}