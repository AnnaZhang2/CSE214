/**
 * The Planner class implements an abstract data type for a list of courses
 * supporting some common operations on such lists.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  Stony Brook ID: 112167606
 */

public class Planner
{
    private Course[] courses;
    private final int MAX_COURSES = 50;

    /**
     * Constructs an instance of Planner with no Course objects in it.
     *
     * Postcondition:
     *  This Planner has been initialized to an empty list of Courses.
     */
    public Planner()
    {
        courses=new Course[MAX_COURSES];
    }

    /**
     * Determines the number of courses currently in the list.
     *
     * Preconditions:
     *  This Planner has been instantiated.
     *
     * @return
     *  The number of Courses in this Planner
     */
    public int size()
    {
        try
        {
            //creates a counter
            int count=0;

            //goes through for loop to count the number of Courses
            for(int i=0; i<MAX_COURSES; i++)
            {
                if(courses[i]!=null)
                    count++;
            }
            return count;
        }
        catch(NullPointerException e)
        {
            System.out.println("Planner has not been instantiated.");
            return 0;
        }
    }

    /**
     * Adds a Course to this Planner.
     *
     * @param newCourse
     *  The new course to add to the list.
     *
     * @param position
     *  The position (preference) of this course on the list.
     *
     * Preconditions:
     *  This Course object has been instantiated.
     *  1 ≤ position ≤ items_currently_in_list + 1
     *  The number of Course objects in this Planner is less than MAX_COURSES.
     *
     * Postconditions:
     *  The new Course is now listed in the correct preference on the list.
     *  All Courses that were originally greater than or equal to position are
     *      moved back one position.
     *
     * @throws IllegalArgumentException
     *  Indicates that position is not within range of between 1 and
     *      items_currently_in_list+1, inclusive
     *
     * @throws FullPlannerException
     *  Indicates that there is no more room in the Planner to record an
     *      additional Course; when the number of Course objects in this Planner
     *      is greater than MAX_COURSES.
     */
    public void addCourse(Course newCourse, int position) throws
            FullPlannerException
    {
        //handles the preconditions first
        if(size()==MAX_COURSES)
            throw new FullPlannerException();
        else if(position<1||position>size()+1)
            throw new IllegalArgumentException("Position has to be within the"
                    + " range of 1 to "+(size()+1)+" inclusive.");
        try
        {
            Course c;
            for(int i=size()-1; i>=position-1; i--)
            {
                c=courses[i];
                courses[i+1]=c;
            }
            courses[position-1]=newCourse;
        }
        catch(NullPointerException e)
        {
            System.out.println("Planner has not been instantiated.");
        }
    }

    public void addCourse(Course newCourse) throws FullPlannerException
    {
        addCourse(newCourse, size()+1);
    }

    /**
     * Removes a Course at a given position from the Planner.
     *
     * @param position
     *  The position in the Planner where the Course will be removed from.
     *
     * Preconditions:
     *  This Planner has been instantiated.
     *  1 ≤ position ≤ items_currently_in_list
     *
     * Postconditions:
     *  The Course at the desired position has been removed.
     *  All Courses that were originally greater than or equal to position are
     *      moved backward one position.
     *
     * @throws IllegalArgumentException
     *  Indicates that position is not within the valid range.
     */
    public void removeCourse(int position)
    {
        if(position<1||position>size())
            throw new IllegalArgumentException("Position is not within range.");
        try
        {
            Course c;
            for(int i=position-1; i<size()-1; i++)
            {
                c=courses[i+1];
                courses[i]=c;
            }
            courses[size()-1]=null;
        }
        catch(NullPointerException e)
        {
            System.out.println("Planner has not be instantiated.");
        }
    }

    /**
     * Finds the Course at a given position in the list.
     *
     * @param position
     *  Position of the Course to retrieve.
     *
     * Preconditions:
     *  The Planner object has been instantiated.
     *  1 ≤ position ≤ items_currently_in_list
     *
     * @return
     *  The Course at the specified position in this Planner object.
     *
     * @throws IllegalArgumentException
     *  Indicates that position is not within the valid range
     */
    public Course getCourse(int position)
    {
        try
        {
            //checks if position is within range
            if(position<1||position>size()+1)
                throw new IllegalArgumentException("Position has to be within"
                        + "the range of 1 to "+size()+1+" inclusive.");
            else
                return courses[position-1];
        }
        catch(NullPointerException e)
        {
            System.out.println("Planner has not been instantiated.");
            return null;
        }
    }

    /**
     * Prints all Courses that are within the specified department
     *
     * @param planner
     *  The list of courses to search in.
     *
     * @param department
     *  The 3-letter department code for a Course
     *
     * Preconditions:
     *  The Planner object has been instantiated.
     *
     * Postconditions:
     *  Displays a neatly formatted table of each course filtered from the
     *      Planner.
     *  Keep the preference numbers the same.
     */
    public static void filter(Planner planner, String department)
    {
        try
        {
            //decalres a course named c
            Course c;

            //prints the column labels with the separation line
            System.out.println("No.  Course Name                Department  "
                    + "Code Section  Instructor\n---------------------------------"
                    + "----------------------------------------------");

            //goes through the Planner
            for(int i=0; i<planner.size(); i++)
            {
                //instantiates the course to the current Course
                c=planner.getCourse(i+1);

                //check if the current Course has the same department the user
                //is searching for
                if(c.getDepartment().equals(department))
                {
                    System.out.printf("%3d", i+1);
                    System.out.printf("%21s", c.getCourseName());
                    System.out.printf("%11s", c.getDepartment());
                    System.out.printf("%13d", c.getCode());
                    System.out.printf("%7d", 0);
                    System.out.printf("%1d", c.getSection());
                    System.out.printf("%2s", "  ");
                    System.out.printf(c.getInstructor());
                    System.out.println();
                }
            }
        }
        catch(NullPointerException e)
        {
            System.out.println("Planner has not be instantiated.");
        }
    }

    /**
     * Checks whether a certain Course is already in the list.
     *
     * @param course
     *  The Course we are looking for.
     *
     * Preconditions:
     *  This Planner and Course have both been instantiated.
     *
     * @return
     *  Returns true if the Planner contains this Course, false otherwise.
     */
    public boolean exists(Course course)
    {
        try
        {
            for(Course c: courses)
            {
                if(c.equals(course))
                    return true;
            }
            return false;
        }
        catch(NullPointerException e)
        {
            return false;
        }
    }

    /**
     * Creates a copy of this Planner. Subsequent changes to the copy will not
     * affect the original and vice versa.
     *
     * Preconditions:
     *  This Planner object has been instantiated.
     *
     * @return
     *  Returns a copy (backup) of this Planner object
     */
    public Object clone()
    {
        //creates a new Planner
        Planner p = new Planner();

        for(int i=0; i<this.size(); i++)
        {
            try
            {
                p.addCourse((Course)courses[i].clone());
            }
            catch(NullPointerException e)
            {
                System.out.println("The Planner has not been instantiated.");
            }
            catch(FullPlannerException e)
            {
                System.out.println("There is no more room to add another "
                        + "course in this Planner.");
            }
        }
        return p;
    }

    /**
     * Prints a neatly formatted table of each item in the list with its
     * position number as shown in the sample output.
     *
     * Preconditions:
     *  This Planner has been instantiated. 
     *
     * Postconditions:
     *  Displays a neatly formatted table of each course from the Planner.
     */
    public void printAllCourses()
    {
        System.out.print(this);
    }

    /**
     * Gets the String representation of this Planner object, which is a neatly
     * formatted table of each Course in the Planner on its own line with its
     * position number as shown in the sample output.
     *
     * @return
     *  The String representation of this Planner object.
     */
    public String toString()
    {
        //prints the column labels with the separation line
        String table="No.  Course Name                Department  Code Section"
                + "  Instructor\n--------------------------------------------------"
                + "-----------------------------\n";

        //declares a Course object named c
        Course c;
        //goes through the Planner and prints out the courses
        for(int i=0; i<this.size(); i++)
        {
            c=courses[i];

            table+=String.format("%3d%21s%11s%13d%7d%1d%2s", i+1,
                    c.getCourseName(), c.getDepartment(), c.getCode(), 0,
                    c.getSection(), "  ")+c.getInstructor()+"\n";
        }
        return table;
    }
}