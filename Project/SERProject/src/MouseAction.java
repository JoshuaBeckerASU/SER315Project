package src;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JLabel;

public class MouseAction extends MouseAdapter
{
	private int mouse_i_x, mouse_i_y, mouse_bound_x, mouse_bound_y;
	private final double speed = 10000.0;
	private JLabel target;
	private Cursor stick;
	private Image img;
	private Icon icon;
	private boolean inUse;
	private mouseListenerThread mouseLis_Th;
	public MouseAction(JLabel target, int mouse_i_x, int mouse_i_y, int mouse_bound_x, int mouse_bound_y)
	{
		this.mouse_i_x = mouse_i_x; 
		this.mouse_i_y = mouse_i_y;
		this.mouse_bound_x = mouse_bound_x;
		this.mouse_bound_y = mouse_bound_y;
		this.target = target;
		this.img = loadImage("analogStickBlackWithAlpha.png",128,128);
		this.icon = target.getIcon();
		this.inUse = false;
		this.mouseLis_Th = new mouseListenerThread();
		mouseLis_Th.start();

		//MouseInfo.getPointerInfo().getLocation()
	}
	@Override
	public void mouseEntered(java.awt.event.MouseEvent evt) 
	{
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent evt) 
	{
		
	}
	@Override
	public void mouseClicked(java.awt.event.MouseEvent evt)
	{
		
	}
	@Override
	public void mousePressed(java.awt.event.MouseEvent evt)
	{
		if(DroneController.isManualMode && DroneController.isReady)
		{
			inUse = true;
			target.setCursor(target.getToolkit().createCustomCursor(img, new Point(0, 0), "c1"));
			//target.setIcon(null);
		}
	}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent evt)
	{
		if(DroneController.isManualMode && DroneController.isReady)
		{
			target.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			//target.setIcon(icon);
			inUse = false;
			MainWindow.isRight = false;
			MainWindow.isLeft = false;
			MainWindow.isNormal = true;
			//MainWindow.isNormal = true;
		}
	}
    private Image loadImage(String name, int w, int h)
	{
		String path = "";
		path = System.getProperty("user.dir");
		path = path.replace('\\','/');
		path = path.replaceAll("SERProject", "SERProject/Assets/" + name);
		Image img;
		
		try 
		{
			img = ImageIO.read(new File(path));
			img = img.getScaledInstance(w, h,  java.awt.Image.SCALE_AREA_AVERAGING);
			return img;
		} catch (IOException ex) 
		{
			System.out.println("FIle Not Found\nFile Path: " + path);System.exit(0);
		}
		return null;
	}
	private class mouseListenerThread extends Thread
    {
        public void stop(long time)
        {
        	long startTime = 0;
        	startTime = System.currentTimeMillis()+time;
        	while(startTime > System.currentTimeMillis())
        	{
        		
        	}
        }
    	@Override
    	public void run()
    	{
    		System.out.println("In thread...");

    		System.out.println(MouseInfo.getPointerInfo().getLocation().x + " < " + mouse_i_x);
    	while(true)
    	{
    		while(DroneController.isReady)
    		{
        		mouse_i_x = MouseInfo.getPointerInfo().getLocation().x;
        		mouse_i_y = MouseInfo.getPointerInfo().getLocation().y;
    			while(inUse)
    			{
    				stop(60);
    				int difX =  MouseInfo.getPointerInfo().getLocation().x - mouse_i_x;
    				int difY =  MouseInfo.getPointerInfo().getLocation().y - mouse_i_y;
    				System.out.println("X = " + difX + " Y = " + difY);
    				if(difX < 0)
    				{
    					System.out.println("left");
    					MainWindow.isLeft = true;
    					MainWindow.isRight= false;
    					MainWindow.isNormal = false;
    					DroneController.getLocation().x += difX/speed;
    				}
    				else if(difX > 0)
    				{
    					MainWindow.isLeft = false;
    					System.out.println("right");
    					MainWindow.isRight= true;
    					MainWindow.isNormal = false;
    					DroneController.getLocation().x += difX/speed;
	    			}else
	    			{
	    				MainWindow.isLeft = false;
	    				MainWindow.isRight= false;
	    				System.out.println("Normal");
	    				MainWindow.isNormal = true;
	    			}
	    			if(difY < 0)
	    			{
	    				DroneController.getLocation().y += difY/speed;
	    			}
    				if(difY > 0)
    				{
    					DroneController.getLocation().y += difY/speed;
    				}
    				MainWindow.update();
    			}
    		
    			stop(100);
    			MainWindow.update();
				MainWindow.isRight = false;
				MainWindow.isLeft = false;
				MainWindow.isNormal = true;
    		}
    		stop(100);
    	}
    		//MainWindow.isNormal = true;
    	}
    }
}
