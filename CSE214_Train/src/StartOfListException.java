/**
 * A class that represents user-defined exception thrown when the cursor is
 * already at head and user attempts to move the cursor backward.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  Stony Brook ID: 112167606
 */
public class StartOfListException extends Exception
{
    public StartOfListException()
    {
        super("The cursor is already at the end of the list.");
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
    public StartOfListException(String s)
    {
        super(s);
    }
}