/**
 * The Packet class represents a packet that will be sent through the network.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stnoybrook.edu
 *  Stony Brook ID: 112167606
 */

public class Packet
{
    //used to assign an id to a newly created packet
    //will start with the value 0, and every time a new packet object is
    //created, increment this counter and assign the value as id of the Packet
    private static int packetCount;
    //a unique identifier for the packet
    //will be determined by using packetCount
    private int id;
    //size of packet being sent
    //randomly determined by simulator using Math.random()
    private int packetSize;
    //the time this Packet is created
    private int timeArrive;
    //contains number of simulation units that it takes for a packet to arrive
    //at the destination router
    //start at packetSize/100 and will decrease at every simulation time unit
    //once it reaches 0, we assume that the packet has arrived at destination
    private int timeToDest;
    //contains number of simulation units it takes to get to destination in
    //case of bandwidth
    private int simNum;

    /**
     * Creates an instance of Packet object
     */
    public Packet(int size, int pC)
    {
        packetCount=pC;
        id=0;
        packetSize=size;
        timeArrive=0;
        timeToDest=size/100;
        simNum=0;
    }

    /**
     * Returns the id of the packet
     *
     * @return
     *  the id of the packet
     */
    public int getID()
    {
        return id;
    }
    /**
     * Returns the size of the packet
     *
     * @return
     *  Size of the packet
     */
    public int getPacketSize()
    {
        return packetSize;
    }
    /**
     * Returns the time the Packet is created
     *
     * @return
     *  the time Packet has been created
     */
    public int getTimeArrive()
    {
        return timeArrive;
    }
    /**
     * Returns the time the Packet takes to Destination
     *
     * @return
     *  the time Packet takes to Destination
     */
    public int getTimeToDest()
    {
        return timeToDest;
    }

    /**
     * Returns the number of simulation units it takes packet to get to
     * Destination in case of bandwidth
     *
     * @return
     *  Number of simulation units
     */
    public int getSimNum()
    {
        return simNum;
    }

    //mutator methods
    /**
     * Replaces the current ID with the given value
     *
     * @param i
     *  a number that identifies a new Packet
     */
    public void setID(int i)
    {
        id=i;
    }
    /**
     * Replaces the current ID with the given value
     *
     * @param s
     *  Size of the packet
     */
    private void setPacketSize(int s)
    {
        packetSize=s;
    }
    /**
     * Replaces the time arrived with the given value
     *
     * @param a
     *  Time the Packet is created
     */
    public void setTimeArrive(int a)
    {
        timeArrive=a;
    }
    /**
     * Replaces the time to destination with the given value
     *
     * @param d
     *  Time it takes for the Packet to arrive at Destination
     */
    public void setTimeToDest(int d)
    {
        timeToDest=d;
    }

    /**
     * Replaces the number of simulation units with given value
     *
     * @param s
     *  number of simulation units it takes for Packet to arrive at Destination
     *  in case of bandwidth
     */
    public void setSimNum(int s)
    {
        simNum=s;
    }

    /**
     * Prints the Packet object in the format: [id, timeArrive, timeToDest]
     *
     * @return
     *  String that prints the Packet object in: [id, timeArrive, timeToDest]
     */
    public String toString()
    {
        return "["+id+", "+timeArrive+", "+timeToDest+"]";
    }

    /**
     * Decrements timeToDest by 1
     */
    public void decrementTimeToDest()
    {
        timeToDest--;
    }

    /**
     * Increments simNum by 1
     */
    public void incrementSimNum()
    {
        simNum++;
    }

    /**
     * Decrements simNum by 1
     */
    public void decrementSimNum()
    {
        simNum--;
    }
}