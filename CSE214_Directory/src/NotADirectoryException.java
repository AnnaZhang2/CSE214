/**
 * A class that represents user-defined exception thrown when the given
 * DirectoryNode object is not a directory.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  section: 06
 *  Stony Brook ID: 112167606
 */
public class NotADirectoryException extends Exception
{
    public NotADirectoryException()
    {
        super("ERROR: Cannot change file into a directory.");
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
    public NotADirectoryException(String s)
    {
        super(s);
    }
}