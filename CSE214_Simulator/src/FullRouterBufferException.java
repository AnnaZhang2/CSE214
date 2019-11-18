/**
 * A class that represents user-defined exception thrown when the router buffer
 * is full.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  Stony Brook ID: 112167606
 */
public class FullRouterBufferException extends Exception
{
    public FullRouterBufferException()
    {
        super("All router buffers are full.");
    }
    /**
     * Parameterized constructor that passes a provided String to the Exception
     * class constructor.
     *
     * @param s
     *  The message that the object is to contain.
     *
     * Postconditions:
     *  The object is created and contains the provided message
     */
    public FullRouterBufferException(String s)
    {
        super(s);
    }
}