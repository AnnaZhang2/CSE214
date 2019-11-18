/**
 * A class that represents user-defined exception thrown when the directory is
 * full.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  section: 06
 *  Stony Brook ID: 112167606
 */
public class FullDirectoryException extends Exception {
    public FullDirectoryException() {
        super("ERROR: Present directory is full.");
    }

    /**
     * Parameterized constructor that passes a provided String to the Exception
     * class constructor.
     *
     * @param s The message that the object is to contain.
     *          <p>
     *          Postconditions:
     *          The object is created and contains the provided message
     */
    public FullDirectoryException(String s) {
        super(s);
    }
}