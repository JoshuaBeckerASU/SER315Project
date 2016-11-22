package src.components;
import java.awt.*;
import javax.swing.*;

import src.DroneController;
import src.MainWindow;

public class ThirdPersonLabel extends JLabel
{
    private int scrWidth, scrHeight;
    private Icon droneLeft_IC,droneRight_IC,droneNormal_IC, droneOff_IC;
    private JLabel droneGif_L, droneContain_L;
    private JLayeredPane overlay;
    private DroneFlyingThread droneImage_Th;
    public ThirdPersonLabel()
	{
        super();
    	droneImage_Th = new DroneFlyingThread();
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
    	overlay = new JLayeredPane();
    	droneOff_IC = loadGif("/Assets/DroneGifs/DroneOff.png",128,128);
        droneGif_L = new JLabel(droneOff_IC);
        droneContain_L = new JLabel(loadGif("/Assets/BackgroundForDrone.png",0,0));
        
        droneLeft_IC = loadGif("/Assets/DroneGifs/Drone_flying_left.gif",128,128);
        droneRight_IC = loadGif("/Assets/DroneGifs/Drone_flying_right.gif",128,128);
        droneNormal_IC = loadGif("/Assets/DroneGifs/Drone_flying.gif",128,128);
    }
    
    public void designComponents()
    {
    	//this.setOpaque(true);
    	overlay.setPreferredSize(new Dimension(scrWidth-15,scrHeight/2 - 50));
    	overlay.setLayout(new OverlayLayout(overlay));
    	this.setLayout(new OverlayLayout(this));
    	this.setPreferredSize(new Dimension(scrWidth-15,scrHeight/2 - 50));
    	//droneGif_L.setPreferredSize(new Dimension(128,128));
    	droneContain_L.setPreferredSize(new Dimension(scrWidth-15,scrHeight/2 - 50));
    	this.setBorder(BorderFactory.createLineBorder(Color.gray));
        droneGif_L.setLayout(new FlowLayout());
        droneContain_L.setLayout(new FlowLayout());
        droneGif_L.setAlignmentX(CENTER_ALIGNMENT);
        droneContain_L.setAlignmentX(CENTER_ALIGNMENT);
        
        //droneGif_L.setPreferredSize(new Dimension(130,130));
        
        //droneGif_L.setIcon(droneNormal_IC);
    }
    
    public void addComponents()
    {
    	startDrone();
    	overlay.add(droneGif_L, new Integer(1));
    	overlay.add(droneContain_L, new Integer(0));
    	this.add(overlay);
    }
    
    public void startDrone()
    {
    	droneImage_Th.start();
    }
    
	private ImageIcon loadGif(String name, int w, int h)
	{
		String path = "";
		path = System.getProperty("user.dir");
		path = path.replace('\\','/');
		path = path.replaceAll("SERProject", "SERProject" + name);
		System.out.println(path);
		ImageIcon gif;
		
		gif = new ImageIcon(path);
		return gif;
	}
	
	private class DroneFlyingThread extends Thread
	{
		@Override
		public void run()
		{
			int old_Image = -1;
			System.out.println("In DroneFlyig Trhead");
			long startTime = 0;
			while(true)
			{
				startTime = System.currentTimeMillis()+60;
				if(DroneController.isReady && DroneController.isFlying)
				{
					if(MainWindow.isNormal)
					{
						System.out.println("in Normal");
						droneGif_L.setIcon(droneNormal_IC);
						MainWindow.isNormal = false;
						old_Image = 0;
					}else if (MainWindow.isRight)
					{
						System.out.println("in Right");
						droneGif_L.setIcon(droneRight_IC);
						MainWindow.isRight = false;
						old_Image = 1;
						//droneContain_L.getIcon().paintIcon(droneContain_L, droneContain_L.getGraphics(), 20, 40);
					}else if(MainWindow.isLeft)
					{
						System.out.println("in Left");
						droneGif_L.setIcon(droneLeft_IC);
						MainWindow.isLeft = false;
						old_Image = 2;
						//droneContain_L.getIcon().paintIcon(droneContain_L, droneContain_L.getGraphics(), 1110, 4110);
					}
					}else if(old_Image != -1)
					{
						old_Image = -1;
						droneGif_L.setIcon(droneOff_IC);
					}
					while(startTime > System.currentTimeMillis())
					{
					}
			}
		}
	}
}