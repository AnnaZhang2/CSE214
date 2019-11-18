/**
 * A class that represents user-defined exception thrown when the cursor is
 * already at tail and user attempts to advance the cursor forward.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  Stony Brook ID: 112167606
 *  Section: 06
 */
public class ClosedAuctionException extends Exception
{
    public ClosedAuctionException()
    {
        super("The auction has been closed.");
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
    public ClosedAuctionException(String s)
    {
        super(s);
    }
}