package src;
import src.Drone.*;

public class DroneController
{
	public static boolean isManualMode;
	public static boolean isAutoMode;
	public static boolean isReady;
	public static boolean isFlying;
	public static boolean isSystemCheck;
    private static Location location;
    public static int battery;
    private static long flightTime;
    public static long startTime;
    private static SystemsCheck sc;
    
    private DroneController(Location startLoc)
    {
    	DroneController.location = startLoc;
        DroneController.isManualMode = false;
        DroneController.isAutoMode = false;
        DroneController.isReady = false;
        DroneController.flightTime = 0;
        DroneController.startTime = 0;
        DroneController.battery = getBatteryLife();
        DroneController.isFlying = false;
        DroneController.sc = new SystemsCheck();
        DroneController.isSystemCheck = false;
    }
    public static Location getLocation()
    {
        return location;
    }
    public void setLocation(Location loc)
    {
        this.location = loc;
    }
    public static double getFlightTime()
    {
    	if(DroneController.isReady)
    		return ((startTime - System.currentTimeMillis())*(-1))*(1/1000.0)*(1/60.0)*(1/60.0);
    	else
    		return 0.00;
    }
    public DroneController getInstance()
    {
        return this;
    }
    private int getBatteryLife()
    {
    	return 100;
    }
    public static DroneController newInstance(int x, int y, int z)
    {
        return new DroneController(new Location(x,y,z));
    }
    public static void runSystemChecks()
    {
    	sc.start();
    }
    private class SystemsCheck extends Thread
    {
    	public SystemsCheck()
    	{
    		
    	}
        public boolean checkBattery()
        {
        	return true;
        }
        public boolean checkMotors()
        {
			stop(1000);
			MainWindow.updateConsole("Running Motor One Check...");
        	if(checkmotorOne())
        	{
        		MainWindow.updateConsole("Running Motor One Ok...");
        		stop(1000);
        	}else
        	{
        		MainWindow.updateConsole("Running Motor One Fail...");
        		stop(1000);
        		return false;
        	}
        	if(checkmotorTwo())
        	{
        		MainWindow.updateConsole("Running Motor Two Ok...");
        		stop(1000);
        	}else
        	{
        		MainWindow.updateConsole("Running Motor Two Fail...");
        		stop(1000);
        		return false;
        	}
        	if(checkmotorThree())
        	{
        		MainWindow.updateConsole("Running Motor Three Ok...");
        		stop(1000);
        	}else
        	{
        		MainWindow.updateConsole("Running Motor Three Fail...");
        		stop(1000);
        		return false;
        	}
        	if(checkmotorFour())
        	{
        		MainWindow.updateConsole("Running Motor Four Ok...");
        		stop(1000);
        	}else
        	{
        		MainWindow.updateConsole("Running Motor Four Fail...");
        		stop(1000);
        		return false;
        	}
        	return true;
        }
        public boolean checkmotorOne()
        {
        	return true;
        }
        public boolean checkmotorTwo()
        {
        	return true;
        }
        public boolean checkmotorThree()
        {
        	return true;
        }
        public boolean checkmotorFour()
        {
        	return true;
        }
        public boolean checkSignal()
        {
        	return true;
        }
        public boolean checkGPS()
        {
        	return true;
        }
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
        	DroneController.isSystemCheck = true;
        	MainWindow.updateConsole("Running Systems Check...");
			stop(1000);
			MainWindow.updateConsole("Running Battery Check...");
			stop(1000);
        	if(checkBattery())
        	{
        		MainWindow.updateConsole("Running Battery Ok...");
        		stop(1000);
        	}else
        	{
        		MainWindow.updateConsole("Running Battery Fail...");
        		stop(1000);
        	}
			stop(1000);
			MainWindow.updateConsole("Running Motor Check...");
			stop(1000);
        	if(checkMotors())
        	{
        		MainWindow.updateConsole("Running Motor check DONE...");
        		stop(1000);
        	}else
        	{
        		MainWindow.updateConsole("Running Motors Fail...");
        		stop(1000);
        	}
			stop(1000);
			MainWindow.updateConsole("Running Signal Check...");
			stop(1000);
        	if(checkSignal())
        	{
        		MainWindow.updateConsole("Running Signal Ok...");
        		stop(1000);
        	}else
        	{
        		MainWindow.updateConsole("Running Signal Fail...");
        		stop(1000);
        	}
			stop(1000);
			MainWindow.updateConsole("Running GPS Check...");
			stop(1000);
        	if(checkGPS())
        	{
        		MainWindow.updateConsole("Running GPS Ok...");
        		stop(1000);
        	}else
        	{
        		MainWindow.updateConsole("Running GPS Fail...");
        		stop(1000);
        	}
        	stop(1000);
        	MainWindow.updateConsole("Systems Check Complete...");
			stop(1000);
			DroneController.isReady = true;
			DroneController.isSystemCheck = false;
        }
    }
}