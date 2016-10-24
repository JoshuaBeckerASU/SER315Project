import Drone.*;

public class DroneController
{
    private Location location; 
    private DroneController(Location startLoc)
    {
        this.location = startLoc;
    }
    public Location getLocation()
    {
        return location;
    }
    public void setLocation(Location loc)
    {
        this.location = loc;
    }
    
    public DroneController getInstance()
    {
        return this;
    }
    
    public static DroneController newInstance(int x, int y, int z)
    {
        return new DroneController(new Location(x,y,z));
    }
}