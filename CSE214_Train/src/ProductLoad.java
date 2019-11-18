/**
 * The ProductLoad class represents the load within one train car. It should
 * contain the name of the product, the weight, the value, and if it is
 * dangerous or not.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stnoybrook.edu
 *  Stony Brook ID: 112167606
 */


public class ProductLoad
{
    //instance variables
    private String name;
    private double weight; //in ton
    private double value;
    boolean dangerous;

    /**
     * Constructs an instance of ProductLoad with given values.
     *
     * @param n
     *  The name of the product.
     *
     * @param w
     *  The weight of the product.
     *
     * @param v
     *  The value of the product.
     *
     * @param d
     *  Whether the product is dangerous or safe.
     */
    public ProductLoad(String n, double w, double v, boolean d)
    {
        name=n;
        weight=w;
        value=v;
        dangerous=d;
    }

    /**
     *
     * @return
     *  Returns the name of the product.
     */
    public String getName()
    {
        return name;
    }
    /**
     *
     * @return
     *  Returns the weight of the product.
     */
    public double getWeight()
    {
        return weight;
    }
    /**
     *
     * @return
     *  Returns the value of the product.
     */
    public double getValue()
    {
        return value;
    }
    /**
     *
     * @return
     *  Returns true if the product is dangerous.
     *  Returns false otherwise.
     */
    public boolean isDangerous()
    {
        return dangerous;
    }

    /**
     * Replaces the original name of the product with the given name.
     *
     * @param n
     *  The name of ProductLoad.
     */
    public void setProductName(String n)
    {
        name=n;
    }
    /**
     * Replaces the original weight of the product with the given weight.
     *
     * @param w
     *  The weight of ProductLoad in tons.
     *
     * @throws IllegalArgumentException
     *  Indicates that the given weight should be greater than to 0.
     */
    public void setWeight(double w)
    {
        if(w<=0)
            throw new IllegalArgumentException("Must be a positive number.");
        else
            weight=w;
    }
    /**
     * Replaces the original value of the product with the given value.
     *
     * @param v
     *  The value of ProductLoad in dollars.
     *
     * @throws IllegalArgumentException
     *  Indicates that the given value should be greater than 0.
     */
    public void setDollars(double v)
    {
        if(v<=0)
            throw new IllegalArgumentException("Must be a positive number.");
        else
            value=v;
    }
    /**
     * Re-determines whether the load is dangerous or not.
     *
     * @param d
     *  Whether ProductLoad is dangerous or not.
     */
    public void setDangerous(boolean d)
    {
        dangerous=d;
    }
}