import java.util.Scanner;
import java.util.*;

/**
 * A class that serves the driver that creates a Planner object and prompts the
 * user to execute operations (adding courses, removing courses, etc.)
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  Stony Brook ID: 112167606
 */
public class PlannerManager
{
    /**
     * The main method runs a menu driven application, which first creates an
     * empty Planner object, and prompts the user for a command to execute an
     * operation. Once a command has been chosen, the program may ask the user
     * for additional information if necessary, and performs the operation.
     */
    public static void main(String[] args)
    {
        //creates a Planner named p
        Planner p = new Planner();

        //creates a Planner named backup
        Planner backup = new Planner();

        //welcoming statement
        System.out.println("*************************************\n"
                + "**** WELCOME TO YOUR NEW PLANNER ****\n"
                + "*************************************");

        //prints menu
        printMenu();

        //sets up for user input
        Scanner input = new Scanner(System.in);
        String letter = input.nextLine();

        //while the input is not one of the selections or while the input is not
        //Q, refers back to menu and prompts the user to enter the correct
        //selection
        while((letter.equals("A")&&letter.equals("G")&&
                letter.equals("R")&&letter.equals("P")&&letter.equals("F")&&
                letter.equals("L")&&letter.equals("S")&&letter.equals("B")&&
                letter.equals("PB")&&letter.equals("RB"))&&!letter.equals("Q"))
        {
            printMenu();

            input = new Scanner(System.in);
            letter = input.nextLine();
        }

        //while the user does not enter "Q", the program will continue on
        while(!letter.equals("Q"))
        {
            //declares all the necessary variables for storing input
            Scanner inputN, inputD, inputC, inputS, inputP, inputI;
            String name, depart, instruct;
            int code = 0;
            int pos = 0;
            byte section = 0;
            Course c;

            //declares conditions for in case user inputs wrong variable type
            boolean inputAcceptedC = false;
            boolean inputAcceptedS = false;
            boolean inputAcceptedP = false;

            //adds a Course to the Planner
            if(letter.equals("A"))
            {
                System.out.println();

                //starts a series of prompting for user to enter info
                System.out.print("Enter course name: ");
                inputN = new Scanner(System.in);
                name = inputN.nextLine();

                System.out.print("Enter department: ");
                inputD = new Scanner(System.in);
                depart = inputD.nextLine();

                //checks if the input is valid
                while(!inputAcceptedC)
                {
                    System.out.print("Enter course code: ");
                    inputC = new Scanner(System.in);

                    try
                    {
                        code = inputC.nextInt();
                        inputAcceptedC = true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("\n*** Please enter the course code"
                                + " in the correct form; it should be a number. "
                                + "***\n");
                    }
                }

                //checks if input is valid
                while(!inputAcceptedS)
                {
                    try
                    {
                        System.out.print("Enter course section: ");
                        inputS = new Scanner(System.in);
                        section = inputS.nextByte();

                        inputAcceptedS = true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("\n*** Please enter the course "
                                + "section in the correct form; it should be a"
                                + " number. ***\n");
                    }
                }

                System.out.print("Enter instructor: ");
                inputI = new Scanner(System.in);
                instruct = inputI.nextLine();

                //checks if input is valid
                while(!inputAcceptedP)
                {
                    try
                    {
                        System.out.print("Enter position: ");
                        inputP = new Scanner(System.in);
                        pos = inputP.nextInt();

                        inputAcceptedP = true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("\n*** Please enter a valid position"
                                + " that is the range of 1 to "+p.size()+". ***\n");
                    }
                }

                //instantiates c with a newly constructed Course object with
                //given information
                c = new Course(name, depart, instruct, code, section);

                //checks if the course already existed
                if(p.exists(c))
                {
                    System.out.println("\n*** You already added this course. "
                            + "No duplicates allowed! ***");
                }
                else
                {
                    //starts adding the Course to the planner
                    try
                    {
                        p.addCourse(c, pos);

                        System.out.println();

                        System.out.println(depart+" "+code+".0"+section+" "
                                + "successfully added to the planner.");
                    }
                    catch(IllegalArgumentException e)
                    {
                        System.out.println("\nPosition has to be within the"
                                + " range of 1 to "+(p.size()+1)+" inclusive.");
                    }
                    catch(FullPlannerException e)
                    {
                        System.out.println("\nNo more courses can be added to"
                                + " this planner; it is already full.");
                    }
                }
            }
            //Displays the number of Courses in the Planner
            else if(letter.equals("S"))
            {
                if(p.size()==1)
                    System.out.println("\nThere is "+p.size()+" course in the "
                            + "planner.");
                else
                    System.out.println("\nThere are "+p.size()+" courses in the "
                            + "planner.");
            }
            //Finds the Course(s) that has/have the given department
            else if(letter.equals("F"))
            {
                System.out.print("\nEnter department code: ");
                inputD = new Scanner(System.in);
                depart = inputD.nextLine();

                System.out.println();

                Planner.filter(p, depart);
            }
            //Finds the Course at a given position
            else if(letter.equals("G"))
            {
                System.out.print("\nEnter position: ");
                inputP = new Scanner(System.in);
                pos = inputP.nextInt();

                //finds the course and prints it out in a table
                try
                {
                    c = p.getCourse(pos);

                    String table="\nNo.  Course Name                Department  "
                            + "Code Section  Instructor\n------------------------------"
                            + "-------------------------------------------------\n";
                    table+=String.format("%3d%21s%11s%13d%7d%1d%2s", pos,
                            c.getCourseName(), c.getDepartment(), c.getCode(), 0,
                            c.getSection(), "  ")+c.getInstructor()+"\n";

                    System.out.print(table);
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println("\nPosition has to be within the range of"
                            + " 1 to "+p.size()+" inclusive.");
                }
            }
            //Creates a backup of the Planner
            else if(letter.equals("B"))
            {
                //if Planner is already initialized, creates backup
                if(p.size()!=0)
                {
                    backup=(Planner)p.clone();

                    System.out.println("\nCreated a backup of the current "
                            + "planner.");
                    System.out.println(backup);
                }
                //if not, tells the reader there is nothing to be created a
                //backup of
                else
                    System.out.println("Unsucessful: There are no courses in"
                            + " the current planner for the backup.");
            }
            //Looks for a Course with given parameters
            else if(letter.equals("L"))
            {
                System.out.println();

                //starts a series of prompting for user to enter info
                System.out.print("Enter course name: ");
                inputN = new Scanner(System.in);
                name = inputN.nextLine();

                System.out.print("Enter department: ");
                inputD = new Scanner(System.in);
                depart = inputD.nextLine();

                System.out.print("Enter course code: ");
                inputC = new Scanner(System.in);
                code = inputC.nextInt();

                System.out.print("Enter course section: ");
                inputS = new Scanner(System.in);
                section = inputS.nextByte();

                System.out.print("Enter instructor: ");
                inputI = new Scanner(System.in);
                instruct = inputI.nextLine();

                c = new Course(name, depart, instruct, code, section);

                pos = findsCourse(p, c);

                System.out.println();

                if(pos==-1)
                    System.out.println(depart+" "+code+".0"+section+" is not "
                            + "found in the planner.");
                else
                    System.out.println(depart+" "+code+".0"+section+" is found "
                            + "in the planner at position "+pos+".");
            }
            //Removes a Course from the Planner
            else if(letter.equals("R"))
            {
                System.out.println();

                System.out.print("Enter position: ");
                inputP = new Scanner(System.in);
                pos = inputP.nextInt();

                try
                {
                    p.removeCourse(pos);
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println("\nPosition has to be within the range of"
                            + " 1 to "+p.size()+" inclusive.");
                }
            }
            //Prints Courses in Planner
            else if(letter.equals("P"))
            {
                System.out.println("\nPlanner:");
                p.printAllCourses();
            }
            //Prints Courses in backup Planner
            else if(letter.equals("PB"))
            {
                //if there is a backup with at least one course in it, print
                if(backup.size()!=0)
                {
                    System.out.println("\nPlanner (Backup):");
                    backup.printAllCourses();
                }
                //if not, don't print
                else
                    System.out.println("There is no backup.");
            }
            else if(letter.equals("RB"))
            {
                if(backup.size()!=0)
                {
                    p = revert(backup);
                    System.out.println("\nPlanner successfully reverted to the"
                            + " backup copy.");
                }
                else
                    System.out.println("There is no backup.");
            }

            //repeats the whole process after above operation is done
            printMenu();

            input = new Scanner(System.in);
            letter = input.nextLine();

            //while the input is not one of the selections or while the input is not
            //Q, refers back to menu and prompts the user to enter the correct
            //selection
            while((letter.equals("A")&&letter.equals("G")&&
                    letter.equals("R")&&letter.equals("P")&&letter.equals("F")&&
                    letter.equals("L")&&letter.equals("S")&&letter.equals("B")&&
                    letter.equals("PB")&&letter.equals("RB"))&&!letter.equals("Q"))
            {
                printMenu();

                input = new Scanner(System.in);
                letter = input.nextLine();
            }
        }

        if(letter.equals("Q"))
            System.out.println("Program terminating successfully...");
    }

    //Prints menu operations
    public static void printMenu()
    {
        System.out.print("\n(A) Add Course\n" +
                "(G) Get Course\n" +
                "(R) Remove Course\n" +
                "(P) Print Courses in Planner\n" +
                "(F) Filter by Department Code\n" +
                "(L) Look For Course\n" +
                "(S) Size\n" +
                "(B) Backup\n" +
                "(PB) Print Courses in Backup\n" +
                "(RB) Revert to Backup\n" +
                "(Q) Quit\n\n" +
                "Enter a selection: ");
    }

    //looks for Course
    public static int findsCourse(Planner p, Course c)
    {
        if(p.exists(c))
            for(int i=0; i<p.size(); i++)
                if(p.getCourse(i+1).equals(c))
                    return i+1;
        return -1;
    }

    //sets this planner back to backup
    public static Planner revert(Planner backup)
    {
        Planner n = new Planner();
        try
        {
            for(int i=0; i<backup.size(); i++)
                n.addCourse((Course)backup.getCourse(i+1).clone());
        }
        catch(FullPlannerException e)
        {
            System.out.println("There is no more room to add another course "
                    + "in this Planner.");
        }
        return n;
    }
}