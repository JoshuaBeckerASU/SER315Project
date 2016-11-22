package src.components;
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

import src.DroneController;
import src.MainWindow;

import java.io.*;
import javax.imageio.*;

public class ControllerPanel extends JPanel
{
    private JLabel main_L, throttle_L, leftB_L, rightB_L, touch_L, rightDownB_L, m_TrimA_L, m_TrimB_L, m_TrimC_L, m_TrimD_L;
    private JLabel rightMidB_L, rightTopB_L, leftDownB_L, leftTopB_L, leftMidB_L,  analogStick_L;
    private int scrWidth, scrHeight;
    private JPanel m_LeftButtons_P, m_RightButtons_P, m_TouchScreen_P;
    private JButton m_Manual_B, m_Auto_B, m_Back_B, m_Options_B, m_Start_B, m_SystemsCheck_B, m_ChangeView_B, m_EndFlight_B;
    private JSlider m_Throttle_S, m_TrimA_S, m_TrimB_S,m_TrimC_S, m_TrimD_S;
    private ImageIcon inFlightBackground, inMenuBackgound;
    public ControllerPanel()
	{
        super();
        GraphicsDevice graphicsDevice;

	// Obtain window Dimension
        graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().
			getDefaultScreenDevice();
        scrWidth = graphicsDevice.getDisplayMode().getWidth();
        scrHeight = graphicsDevice.getDisplayMode().getHeight();
        inFlightBackground = loadImage("InFlight.png",2*(scrWidth/3-15)/2 +175,scrHeight/2 - 75);
        inMenuBackgound = loadImage("TitleTouchScreen.jpg",2*(scrWidth/3-15),scrHeight/2 - 50);
		createComponents();
		designComponents();
        addActionListeners();
		addComponents();
		addMouseListeners();
	}
    
    public void createComponents()
    {
        main_L = new JLabel();
        m_LeftButtons_P = new JPanel();
        m_RightButtons_P = new JPanel();
        m_TouchScreen_P = new JPanel();
        leftB_L = new JLabel();
        rightB_L = new JLabel();
        rightDownB_L = new JLabel();
        rightMidB_L = new JLabel();
        rightTopB_L = new JLabel();
        leftDownB_L = new JLabel();
        leftTopB_L = new JLabel();
        leftMidB_L = new JLabel();
        throttle_L = new JLabel("Throttle");
        m_TrimA_L = new JLabel("Trim A:");
        m_TrimB_L = new JLabel("Trim B:");
        m_TrimC_L = new JLabel("Trim C:");
        m_TrimD_L = new JLabel("Trim D:");
        
        m_TrimA_S= new JSlider(JSlider.NORTH,0,100,0);
        m_TrimB_S= new JSlider(JSlider.NORTH,0,100,0);
        m_TrimC_S= new JSlider(JSlider.NORTH,0,100,0);
        m_TrimD_S= new JSlider(JSlider.NORTH,0,100,0);
        m_Throttle_S = new JSlider(JSlider.NORTH,0,100,0);
        
        analogStick_L = new JLabel();
        touch_L = new JLabel(inMenuBackgound);
        
        m_Back_B = new JButton(loadButtonImage("BackB.png"));
        m_Options_B = new JButton(loadButtonImage("OptionsB.png"));
        m_Start_B = new JButton(loadButtonImage("StartDroneB.png"));
        m_SystemsCheck_B = new JButton(loadButtonImage("SystemCheckB.png"));
        m_Manual_B = new JButton(loadButtonImage("ManualB.png"));
        m_Auto_B = new JButton(loadButtonImage("AutoB.png"));
        m_ChangeView_B = new JButton(loadButtonImage("ChangeViewB.png"));
        m_EndFlight_B = new JButton(loadButtonImage("EndFlightB.png"));
    }
    
    public void designComponents()
    {
    	int width = 2*(scrWidth/3-15), height = scrHeight/2;
    	
        
        //main_L.setBorder(BorderFactory.createLineBorder(Color.red));
        main_L.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
        rightDownB_L.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        leftDownB_L.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        leftTopB_L.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        rightMidB_L.setLayout(new FlowLayout());
        rightTopB_L.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        
        m_TouchScreen_P.setLayout(new FlowLayout());
        touch_L.setLayout(new BoxLayout(touch_L,BoxLayout.Y_AXIS));
        rightB_L.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        leftB_L.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        
        if(scrWidth > 1400)
        {
        	main_L.setPreferredSize(new Dimension(width,height - 50));
	        touch_L.setPreferredSize(new Dimension(width/2+120,height - 75));
	        leftB_L.setPreferredSize(new Dimension(width/4-75,height - 75));
	        rightB_L.setPreferredSize(new Dimension(width/4-75,height - 75));
	        analogStick_L.setPreferredSize(new Dimension(width/5-20,(height - 75)/3 - 5));
	        rightDownB_L.setPreferredSize(new Dimension(width/3-5,(height - 75)/3));
	        rightMidB_L.setPreferredSize(new Dimension(width/3-5,(height - 75)/3));
	        rightTopB_L.setPreferredSize(new Dimension(width/3-5,(height - 75)/3));
	        leftTopB_L.setPreferredSize(new Dimension(width/3-5,(height - 75)/3));
        }else
        {
        	main_L.setPreferredSize(new Dimension(width+250,height - 50));
        	Dimension d = new Dimension(width/4-25,height - 75);
	        leftB_L.setPreferredSize(d);
	        rightB_L.setPreferredSize(d);
	        touch_L.setPreferredSize(new Dimension(width/2+200,height - 75));
	        analogStick_L.setPreferredSize(new Dimension(width/5-10,(height - 75)/3 - 5));
	        rightDownB_L.setPreferredSize(new Dimension(width/3-10,(height - 75)/3));
	        rightMidB_L.setPreferredSize(new Dimension(width/3-10,(height - 75)/3));
	        rightTopB_L.setPreferredSize(new Dimension(width/3-10,(height - 75)/3));
	        leftTopB_L.setPreferredSize(new Dimension(width/3-10,(height - 75)/3));
        }
        
        m_Throttle_S.setPreferredSize(new Dimension(100,(height - 115)/3));
        m_Throttle_S.setLabelTable(m_Throttle_S.createStandardLabels(25,0));
       // m_Throttle_S.setMajorTickSpacing(1);
        m_Throttle_S.setPaintLabels(true);
        m_Throttle_S.setEnabled(false);
        
        m_TrimA_S.setPreferredSize(new Dimension(40,(height - 125)/3));
        m_TrimA_S.setLabelTable(m_Throttle_S.createStandardLabels(25,0));
        m_TrimA_S.setPaintLabels(true);
        m_TrimA_S.setEnabled(false);
        
        m_TrimB_S.setPreferredSize(new Dimension(40,(height - 125)/3));
        m_TrimB_S.setLabelTable(m_Throttle_S.createStandardLabels(25,0));
        m_TrimB_S.setPaintLabels(true);
        m_TrimB_S.setEnabled(false);
        
        m_TrimC_S.setPreferredSize(new Dimension(40,(height - 125)/3));
        m_TrimC_S.setLabelTable(m_Throttle_S.createStandardLabels(25,0));
        m_TrimC_S.setPaintLabels(true);
        m_TrimC_S.setEnabled(false);
        
        m_TrimD_S.setPreferredSize(new Dimension(40,(height - 125)/3));
        m_TrimD_S.setLabelTable(m_Throttle_S.createStandardLabels(25,0));
        m_TrimD_S.setPaintLabels(true);
        m_TrimD_S.setEnabled(false);
        
        leftDownB_L.setPreferredSize(new Dimension(width/3-5,(height - 75)/3));
        leftTopB_L.setPreferredSize(new Dimension(width/3-5,(height - 75)/3));
        leftMidB_L.setPreferredSize(new Dimension(width/3-5,(height - 75)/3));
        
        touch_L.setBorder(BorderFactory.createLineBorder(Color.green));
        leftB_L.setBorder(BorderFactory.createLineBorder(Color.black));
        rightB_L.setBorder(BorderFactory.createLineBorder(Color.black));
        rightDownB_L.setBorder(BorderFactory.createLineBorder(Color.black));
        rightMidB_L.setBorder(BorderFactory.createLineBorder(Color.black));
        rightTopB_L.setBorder(BorderFactory.createLineBorder(Color.black));
        leftDownB_L.setBorder(BorderFactory.createLineBorder(Color.black));
        leftTopB_L.setBorder(BorderFactory.createLineBorder(Color.black));
        leftMidB_L.setBorder(BorderFactory.createLineBorder(Color.black));
        
        m_SystemsCheck_B.setAlignmentX(CENTER_ALIGNMENT);
        m_Start_B.setAlignmentX(CENTER_ALIGNMENT);
        m_Manual_B.setAlignmentX(CENTER_ALIGNMENT);
        m_Options_B.setAlignmentX(CENTER_ALIGNMENT);
        m_Auto_B.setAlignmentX(CENTER_ALIGNMENT);
        m_ChangeView_B.setAlignmentX(CENTER_ALIGNMENT);
        m_Back_B.setAlignmentX(CENTER_ALIGNMENT);
        m_EndFlight_B.setAlignmentX(CENTER_ALIGNMENT);
        
        m_EndFlight_B.setMargin(new Insets(0,0,0,0));
        m_EndFlight_B.setActionCommand("EndFlightB");
        
        m_Manual_B.setMargin(new Insets(0,0,0,0));
        m_Manual_B.setActionCommand("ManualB");
        
        m_Back_B.setMargin(new Insets(0,0,0,0));
        m_Back_B.setActionCommand("BackB");
        
        m_Start_B.setMargin(new Insets(0,0,0,0));
        m_Start_B.setActionCommand("StartB");
        
        m_Options_B.setMargin(new Insets(0,0,0,0));
        m_Options_B.setActionCommand("OptionsB");
        
        m_SystemsCheck_B.setMargin(new Insets(0,0,0,0));
        m_SystemsCheck_B.setActionCommand("SystemsCheckB");
        
        m_Auto_B.setMargin(new Insets(0,0,0,0));
        m_Auto_B.setActionCommand("autoB");
        
        m_ChangeView_B.setMargin(new Insets(0,0,0,0));
        m_ChangeView_B.setActionCommand("ChangeViewB");
        
        
        analogStick_L.setIcon(loadImage("AnalogStickWithAlpha.png", width/5-20,(height - 75)/3 - 5));
        
    }
    
	private void addActionListeners()
	{
		m_Auto_B.addActionListener(new ButtonListener());
		m_Manual_B.addActionListener(new ButtonListener());
		m_SystemsCheck_B.addActionListener(new ButtonListener());
		m_Options_B.addActionListener(new ButtonListener());
		m_Start_B.addActionListener(new ButtonListener());
		m_EndFlight_B.addActionListener(new ButtonListener());
		m_ChangeView_B.addActionListener(new ButtonListener());
	}
    
	private void addMouseListeners()
	{
		analogStick_L.addMouseListener(new src.MouseAction(analogStick_L,0,0,0,0));
	}
    public void addComponents()
    {
    	leftTopB_L.add(m_TrimA_L);
    	leftTopB_L.add(m_TrimA_S);
    	leftTopB_L.add(m_TrimB_L);
    	leftTopB_L.add(m_TrimB_S);
    	
    	rightTopB_L.add(m_TrimC_L);
    	rightTopB_L.add(m_TrimC_S);
    	rightTopB_L.add(m_TrimD_L);
    	rightTopB_L.add(m_TrimD_S);
    	
    	leftDownB_L.add(m_Throttle_S);
    	leftDownB_L.add(throttle_L);
    	rightDownB_L.add(analogStick_L);

    	rightB_L.add(rightTopB_L);
    	rightB_L.add(rightMidB_L);
    	rightB_L.add(rightDownB_L);
    	
    	
    	leftB_L.add(leftTopB_L);
    	leftB_L.add(leftMidB_L);
    	leftB_L.add(leftDownB_L);
    	
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(new JLabel("\n"));
        touch_L.add(m_Manual_B);
        touch_L.add(new JLabel("\n"));
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(new JLabel("\n"));
        touch_L.add(m_Auto_B);
        
        main_L.add(leftB_L);
        main_L.add(touch_L);
        main_L.add(rightB_L);
        this.add(main_L);
        this.revalidate();
    }
    
    private void setUpInFlightTouchScreen()
    {
        m_ChangeView_B.setAlignmentX(RIGHT_ALIGNMENT);
        m_Back_B.setAlignmentX(RIGHT_ALIGNMENT);
        m_Options_B.setAlignmentX(RIGHT_ALIGNMENT);
        m_EndFlight_B.setAlignmentX(RIGHT_ALIGNMENT);
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(m_Back_B);
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(m_Options_B);
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(m_ChangeView_B);
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(new JLabel("\n"));
    	touch_L.add(m_EndFlight_B);
    	touch_L.revalidate();
    }
    
    private void enableSliders(boolean enable)
    {
    	m_Throttle_S.setEnabled(enable);
    	m_TrimD_S.setEnabled(enable);
    	m_TrimC_S.setEnabled(enable);
    	m_TrimB_S.setEnabled(enable);
    	m_TrimA_S.setEnabled(enable);
    }
    
    private ImageIcon loadImage(String name, int w, int h)
	{
		String path = "";
		path = System.getProperty("user.dir");
		path = path.replace('\\','/');
		path = path.replaceAll("SERProject", "SERProject/Assets/" + name);
		Image img;
		
		try 
		{
			img = ImageIO.read(new File(path));
			img = img.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(img);
		} catch (IOException ex) 
		{
			System.out.println("FIle Not Found\nFile Path: " + path);System.exit(0);
		}
		return null;
	}
	private ImageIcon loadButtonImage(String name)
	{
		String path = "";
		path = System.getProperty("user.dir");
		path = path.replace('\\','/');
		path = path.replaceAll("SERProject", "SERProject/Assets/" + name);
		Image img;
		
		try 
		{
			img = ImageIO.read(new File(path));
			return new ImageIcon(img);
		} catch (IOException ex) 
		{
			System.out.println("FIle Not Found\nFile Path: " + path);System.exit(1);
		}
		return null;
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String command = event.getActionCommand();
            //System.out.println(command);
			switch(command)
			{
				case "ManualB": 
				//touch_L.setIcon(loadImage("InFlight.png",2*(scrWidth/3-15)/2+120,scrHeight/2 - 75));
                touch_L.removeAll();
            	touch_L.add(new JLabel("\n"));
            	touch_L.add(new JLabel("\n"));
            	touch_L.add(new JLabel("\n"));
            	touch_L.add(new JLabel("\n"));
            	touch_L.add(new JLabel("\n"));
            	touch_L.add(new JLabel("\n"));
                touch_L.add(m_SystemsCheck_B);
                touch_L.add(new JLabel("\n"));
                touch_L.add(new JLabel("\n"));
            	touch_L.add(new JLabel("\n"));
            	touch_L.add(new JLabel("\n"));
                touch_L.add(m_Start_B);
                touch_L.revalidate();
                touch_L.getParent().revalidate();
					break;
				case "AutoB":
					DroneController.isManualMode = false;
					DroneController.isAutoMode = true;
					break;
				case "StartB":
					if(!DroneController.isSystemCheck)
					{
						enableSliders(true);
						m_Throttle_S.setValue(25);
						DroneController.isFlying = true;
		                DroneController.isManualMode = true;
		                DroneController.isAutoMode = false;
		                DroneController.isReady = true;
		                MainWindow.isNormal = true;
						DroneController.startTime = System.currentTimeMillis();
						touch_L.setIcon(inFlightBackground);
						touch_L.removeAll();
						setUpInFlightTouchScreen();
					}
					break;
				case "SystemsCheckB":
					System.out.println("SystemCheck");
					if(!DroneController.isSystemCheck)
						DroneController.runSystemChecks();
					
					DroneController.startTime = 0;
					break;
				case "EndFlightB":
					System.out.println("Flight Ended");
					enableSliders(false);
					m_Throttle_S.setValue(0);
					DroneController.isFlying = false;
					DroneController.isReady = false;
					DroneController.startTime = 0;
					touch_L.setIcon(inMenuBackgound);
					main_L.removeAll();
					touch_L.removeAll();
					rightDownB_L.removeAll();
					main_L.revalidate();
					designComponents();
					addComponents();
					main_L.revalidate();
					break;
			}
		}  
	}    
}
