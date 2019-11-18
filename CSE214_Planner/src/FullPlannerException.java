/**
 * A class that represents user-defined exception thrown when there is no more
 * room in the Planner to record an additional Course.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  Stony Brook ID: 112167606
 */
public class FullPlannerException extends Exception
{
    public FullPlannerException()
    {
        super("There is no more room to add another course in this Planner.");
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
    public FullPlannerException(String s)
    {
        super(s);
    }
}