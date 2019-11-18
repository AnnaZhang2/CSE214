import java.util.ArrayList;

/**
 * The Router class represents a router in the network, which is ultimately a
 * queue.
 * @author Anna Zhang
 *  email: anna.zhang.2@stnoybrook.edu
 *  Stony Brook ID: 112167606
 */

public class Router extends ArrayList<Packet>
{

    /**
     * Constructs a Router object
     */
    public Router()
    {

    }

    /**
     * Adds a new Packet to the end of the router buffer
     *
     * Preconditions:
     *  The queue has been instantiated
     *
     * @param p
     *  A Packet
     */
    public void enqueue(Packet p)
    {
        if(this==null)
            throw new NullPointerException("The router has not been created.");
        else
            add(p);
    }

    /**
     * Removes the first Packet in the router buffer
     *
     * Preconditions:
     *  The queue has been instantiated
     *
     * @return
     *  The first Packet that has been removed
     */
    public Packet dequeue()
    {
        if(this==null)
            throw new NullPointerException("The router has not been created.");
        else if(isEmpty())
            return null;
        else
            return remove(0);
    }

    /**
     * Returns, but does not remove the first Packet in the router buffer.
     *
     * @return
     *  The first Packet in the router buffer
     */
    public Packet peek()
    {
        if(this==null)
            throw new NullPointerException("The router has not been created.");
        else if(isEmpty())
            return null;
        else
            return this.get(0);
    }

    /**
     * Returns a String representation of the router buffer
     *
     * @return
     *  A String display of the router buffer
     */
    public String toString()
    {
        String middle = "{";

        for(int i=0; i<size(); i++)
        {
            if(i==size()-1)
                middle+=get(size()-1);
            else
                middle+=get(i)+", ";
        }
        middle+="}";

        return middle;
    }

    /**
     * Loops through the list Intermediate routers.
     *
     * @param routers
     *  A list of intermediate routers
     *
     * @return
     *  the index of the router
     */
    public static int sendPacketTo(ArrayList<Router> routers, int max) throws
            FullRouterBufferException
    {
        boolean full=true;

        for(int i=0; i<routers.size(); i++)
            if(routers.get(i).size()<max)
                full=false;

        if(full==false)
        {
            int min = routers.get(0).size();
            int index = 0;
            for(int i=1; i<routers.size(); i++)
            {
                if(routers.get(i).size()<min)
                {
                    min = routers.get(i).size();
                    index = i;
                }
            }
            return index;
        }
        else
            throw new FullRouterBufferException();
    }
}