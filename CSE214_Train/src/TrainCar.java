/**
 * The TrainCar class represents a car of a train that contains a load, along
 *  with two additional attributes: its length and width.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stnoybrook.edu
 *  Stony Brook ID: 112167606
 */
public class TrainCar
{
    //instance variables
    private double length; //in meters
    private double weight; //in ton
    private ProductLoad load;

    /**
     * Constructs an instance of TrainCar with given values.
     *
     * @param l
     *  The length of the TrainCar in meters.
     *
     * @param w
     *  The weight of the TrainCar in tons.
     *
     * @param lo
     *  The ProductLoad within the TrainCar.
     */
    public TrainCar(double l, double w)
    {
        length=l;
        weight=w;
    }

    /**
     *
     * @return
     *  The length of the TrainCar in meters.
     */
    public double getLength()
    {
        return length;
    }
    /**
     *
     * @return
     *  The weight of the TrainCar in tons.
     */
    public double getWeight()
    {
        return weight;
    }
    /**
     *
     * @return
     *  The load reference.
     */
    public ProductLoad getLoad()
    {
        return load;
    }

    /**
     * Replaces the original load reference with the given load reference.
     *
     * @param lo
     *  The given load reference.
     */
    public void setLoad(ProductLoad lo)
    {
        load=lo;
    }

    /**
     * Determines whether the car is empty or not.
     *
     * @return
     *  Returns true if the car is empty.
     *  Returns false if the car is not empty.
     */
    public boolean isEmpty()
    {
        if(load==null)
            return true;
        return false;
    }
}