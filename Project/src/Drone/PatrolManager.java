
public class PatrolManager extends Thread
{
    private boolean patrolling;
    private Route route;
    
    private PatrolManager(Route route)
    {
        this.route = route;
    }
    
    public PatrolManager getInstance()
    {
        return this;
    }
    
    public PatrolManager newInstance(Route route)
    {
        return new PatrolManager(route);
    }
    
    public void setNewRoute(Route route)
    {
        this.route = route;
        startRoute();
    }
    
    public void startRoute()
    {
        while(patrolling)
        {
            //need to implement for patrolling
        }
    }
    public void run()
    {
        startRoute();
    }
}