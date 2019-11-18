/**
 * The Course class represents a course a student is taking, which includes the
 * name of the course, the department the course belongs to, the instructor
 * that teaches the course, the code that represents the Course, and the section
 * of the Course.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stnoybrook.edu
 *  Stony Brook ID: 112167606
 */
public class Course
{
    //Declaring Instance Variables
    private String courseName; //name of the Course
    private String department; //which department the Course is from
    private String instructor; //who is teaching the Course
    private int code; //the code that represents the Course
    private byte section; //which section the Course is

    /**
     * Returns an instance of Course with given values.
     *
     * @param c
     *  The name of the Course.
     *
     * @param d
     *  The name of the department which the Course belongs to.
     *
     * @param i
     *  The name of the instructor that teaches the Course.
     *
     * @param co
     *  The code number of the Course.
     *
     * @param s
     *  The section of the Course.
     */
    public Course(String c, String d, String i, int co, byte s)
    {
        courseName=c;
        department=d;
        instructor=i;
        code=co;
        section=s;
    }

    /**
     * Returns the name of the Course.
     *
     * @return
     *  Returns the name of the Course.
     */
    public String getCourseName()
    {
        return courseName;
    }

    /**
     * Returns the department the Course belongs to.
     *
     * @return
     *  Returns the department of the Course.
     */
    public String getDepartment()
    {
        return department;
    }

    /**
     * Returns the name of the instructor that teaches the Course.
     *
     * @return
     *  Returns the instructor of the Course.
     */
    public String getInstructor()
    {
        return instructor;
    }

    /**
     * Returns the code that represents the Course.
     *
     * @return
     *  Returns the code of the Course.
     */
    public int getCode()
    {
        return code;
    }

    /**
     * Returns the section of the Course.
     *
     * @return
     *  Returns the section of the Course
     */
    public byte getSection()
    {
        return section;
    }

    /**
     * Replaces the original course name with the given name.
     *
     * @param c
     *  The name of the Course.
     */
    private void setCourseName(String c)
    {
        courseName=c;
    }

    /**
     * Replaces the original department with the given department.
     *
     * @param d
     *  The department of the Course.
     */
    private void setDepartment(String d)
    {
        department=d;
    }

    /**
     * Replaces the original instructor with the given instructor.
     *
     * @param i
     *  The instructor of the Course.
     */
    private void setInstructor(String i)
    {
        instructor=i;
    }

    /**
     * Replaces the original code of the Course with the given instructor.
     *
     * @param co
     *  The code of the Course.
     */
    private void setCode(int co)
    {
        code=co;
    }

    /**
     * Replaces the original section of the Course with the given section.
     *
     * @param s
     *  The section of the Course.
     */
    private void setSection(byte s)
    {
        section=s;
    }

    /**
     * Makes a deep clone of the Course.
     *
     * @return
     *  Returns a copy of an instance of Course with a different reference.
     */
    public Object clone()
    {
        Course copy = new Course(courseName, department, instructor, code,
                section);
        return copy;
    }

    /**
     * Checks if an Object obj refers to a Course object with same attributes
     * as this Course.
     *
     * @param obj
     *  An Object.
     *
     * @return
     *  Returns true if Object obj refers to a Course object with the same
     *      attributes.
     *  Returns false otherwise.
     */
    public boolean equals(Object obj)
    {
        //if obj is an instance of Course
        if(obj instanceof Course)
        {
            //typecast obj to a Course
            Course o=(Course)obj;

            //returns true if all fields are equal
            return this.courseName.equals(o.courseName) &&
                    this.department.equals(o.department) &&
                    this.instructor.equals(o.instructor) &&
                    this.code==o.code && this.section==o.section;
        }

        //automatically return false is obj is not an instance of Course.
        return false;
    }
}