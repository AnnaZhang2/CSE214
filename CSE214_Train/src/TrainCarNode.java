/**
 * The TrainCarNode class acts as a node wrapper around a TrainCar object. It
 * contains two TrainCarNode references (one for the next node in the chain and
 * one for the previous node in the chain), and one TrainCar reference
 * variable containing the data.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  Stony Brook ID: 112167606
 */

public class TrainCarNode
{
    //instance variables
    private TrainCarNode prev;
    private TrainCarNode next;
    private TrainCar car;

    /**
     * Constructs a TrainCarNode reference with default values
     */
    public TrainCarNode()
    {
        prev=null;
        next=null;
        car=null;
    }

    /**
     * Constructs a TrainCarNode reference with given values
     *
     * @param c
     *  TrainCar reference variable
     */
    public TrainCarNode(TrainCar c)
    {
        prev=null;
        next=null;
        car=new TrainCar(c.getLength(),c.getWeight());
    }

    /**
     *
     * @return
     *  TrainCarNode reference to the previous node in the chain.
     */
    public TrainCarNode getPrev()
    {
        return prev;
    }

    /**
     *
     * @return
     *  TrainCarNode reference to the next node in the chain.
     */
    public TrainCarNode getNext()
    {
        return next;
    }

    /**
     *
     * @return
     *  TrainCar object
     */
    public TrainCar getTrainCar()
    {
        return car;
    }

    /**
     * Sets the previous node with the given reference
     */
    public void setPrev(TrainCarNode p)
    {
        prev=p;
    }

    /**
     * Sets the next node with the given reference
     */
    public void setNext(TrainCarNode n)
    {
        next=n;
    }

    /**
     * Sets the TrainCar reference to the given reference
     */
    public void setTrainCar(TrainCar c)
    {
        car=new TrainCar(c.getLength(),c.getWeight());
    }

    /**
     * Returns the information of the car and the load within the car in table
     * format.
     *
     * @return
     *  A table displaying the information of TrainCar and ProductLoad.
     */
    public String toString()
    {
        String head="CAR:                               LOAD:\n";
        String table="   Length (m)    Weight (t)  |    Name      "
                + "Weight (t)     Value ($)   Dangerous\n"
                + "==================================+==========="
                + "========================================\n"
                + String.format("%3d%10d%10s%10d%10d%10b",car.getLength(),
                car.getWeight()+ "  |     " + car.getLoad().getName(),
                car.getLoad().getWeight(), car.getLoad().getValue(),
                car.getLoad().isDangerous());

        return head+table;
    }
}