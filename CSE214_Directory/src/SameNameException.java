/**
 * A class that represents user-defined exception thrown when a folder/file with
 * the same name as the existing folder/file is being added.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  section: 06
 *  Stony Brook ID: 112167606
 */
public class SameNameException extends Exception
{
    public SameNameException()
    {
        super("ERROR: Present directory already has a directory with the same "
                + "node.");
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
    public SameNameException(String s)
    {
        super(s);
    }
}