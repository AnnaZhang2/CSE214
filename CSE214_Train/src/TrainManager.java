import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The TrainManager class
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  Stony Brook ID: 112167606
 */

public class TrainManager
{
    /**
     * The main method runs a menu driven application which first creates an
     * empty TrainLinkedList object. The program prompts the user for a command
     * to execute an operation. Once a command has been chosen, the program may
     * ask the user for additional information if necessary, and performs the
     * operation.
     */
    public static void main(String[] args)
    {
        //creates an empty TrainLinkedList object
        TrainLinkedList t = new TrainLinkedList();

        //print menu
        printMenu();

        //sets up for user input
        Scanner input = new Scanner(System.in);
        String letter = input.nextLine();

        //while the input is not one of the selections or while the input is not
        //Q, refers back to menu and prompts the user to enter the correct
        //selection
        while((letter.equalsIgnoreCase("F")&&letter.equalsIgnoreCase("B")&&
                letter.equalsIgnoreCase("I")&&letter.equalsIgnoreCase("R")&&
                letter.equalsIgnoreCase("L")&&letter.equalsIgnoreCase("S")&&
                letter.equalsIgnoreCase("T")&&letter.equalsIgnoreCase("M")&&
                letter.equalsIgnoreCase("D"))&&!letter.equalsIgnoreCase("Q"))
        {
            printMenu();

            input = new Scanner(System.in);
            letter = input.nextLine();
        }

        //while the user does not enter "Q", the program will continue on
        while(!letter.equalsIgnoreCase("Q"))
        {
            Scanner inputN, inputL, inputW, inputV, inputD;
            TrainCar car;
            ProductLoad p;
            double length = 0;
            double weight = 0;
            double value = 0;
            String name;
            char danger;
            boolean dangerous = false;
            boolean inputAcceptedL = false;
            boolean inputAcceptedW = false;
            boolean inputAcceptedV = false;

            if(letter.equalsIgnoreCase("I"))
            {
                while(!inputAcceptedL)
                {
                    try
                    {
                        //ask for length
                        System.out.print("\nEnter car length in meters: ");
                        inputL = new Scanner(System.in);
                        length = inputL.nextDouble();
                        inputAcceptedL = true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("\nPlease enter a number for the "
                                + "length in meters.");
                    }
                }

                while(!inputAcceptedW)
                {
                    try
                    {
                        //ask for weight
                        System.out.print("Enter car weight in meters: ");
                        inputW = new Scanner(System.in);
                        weight = inputW.nextDouble();
                        inputAcceptedW = true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("\nPlease enter a number for the "
                                + "weight in tons.");
                    }
                }
                car = new TrainCar(length, weight);
                t.insertAfterCursor(car);

                try
                {
                    if(!t.isAtEnd())
                        t.cursorForward();

                    System.out.println("\nNew train car " + car.getLength()
                            + " meters " + car.getWeight() + " tons inserted into "
                            + "train.\n");
                }
                catch(EndOfListException e)
                {
                    System.out.println();
                }
            }
            else if(letter.equalsIgnoreCase("M"))
            {
                System.out.println();
                t.printManifest();
            }
            else if(letter.equalsIgnoreCase("L"))
            {
                System.out.print("\nEnter product name: ");
                inputN = new Scanner(System.in);
                name = inputN.nextLine();

                while(!inputAcceptedW)
                {
                    try
                    {
                        //ask for weight
                        System.out.print("Enter car weight in meters: ");
                        inputW = new Scanner(System.in);
                        weight = inputW.nextDouble();
                        inputAcceptedW = true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Please enter a number for the "
                                + "weight in tons.");
                    }
                }

                while(!inputAcceptedV)
                {
                    try
                    {
                        System.out.print("Enter value in dollars: ");
                        inputV = new Scanner(System.in);
                        value = inputV.nextDouble();
                        inputAcceptedV = true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Please enter a number for the value"
                                + " in dollars.");
                    }
                }

                System.out.print("Enter is product dangerous? (y/n): ");
                inputD = new Scanner(System.in);
                danger = inputD.nextLine().charAt(0);

                if(danger=='y')
                    dangerous=true;
                else if(danger=='n')
                    dangerous=false;
                else
                {
                    while(danger!='n'&&danger!='y')
                    {
                        System.out.print("Please enter y if the product is "
                                + "dangerous. Enter n if otherwise.");
                        inputD = new Scanner(System.in);
                        danger = inputD.nextLine().charAt(0);
                    }
                }

                p = new ProductLoad(name, weight, value, dangerous);

                try
                {
                    t.getCursorData().setLoad(p);

                    t.addValue(value);
                    t.addWeight(weight);
                    if(dangerous)
                        t.setDangerous(dangerous);

                    System.out.println();

                    System.out.println(weight + " tons of " + name + " added to"
                            + " the current car.");
                }
                catch(NullPointerException e)
                {
                    System.out.println("The train car has not been "
                            + "initialized.");
                }
                System.out.println();
            }
            else if(letter.equalsIgnoreCase("T"))
            {
                System.out.println("\n"+t+"\n");
            }
            else if(letter.equalsIgnoreCase("F"))
            {
                try
                {
                    t.cursorForward();
                    System.out.println("\nCursor moved forward\n");
                }
                catch(EndOfListException E)
                {
                    System.out.println("\nNo next car, cannot move cursor "
                            + "forward.\n");
                }
            }
            else if(letter.equalsIgnoreCase("B"))
            {
                try
                {
                    t.cursorBackward();
                    System.out.println("\nCursor moved backward\n");
                }
                catch(StartOfListException E)
                {
                    System.out.println("\nAlready at the first car, cannot "
                            + "move cursor forward.\n");
                }
            }
            else if(letter.equalsIgnoreCase("S"))
            {
                System.out.print("\nEnter product name: ");
                inputN = new Scanner(System.in);
                name = inputN.nextLine();

                System.out.println();

                t.findProduct(name);
            }
            else if(letter.equalsIgnoreCase("D"))
            {
                System.out.println();
                t.removeDangerousCars();
                System.out.println();
            }
            else if(letter.equalsIgnoreCase("R"))
            {
                TrainCar c = t.removeCursor();
                if(c==null)
                    System.out.println("\nUnsuccessful attempt to unlink "
                            + "the car, the train has no car to begin with.\n");
                else
                {
                    if(c.getLoad()!=null)
                    {
                        System.out.println("\nCar successfully unlinked. The "
                                + "following load has been removed from the train:"
                                + "\n");

                        String table="    Name      Weight (t)     Value ($)   "
                                + "Dangerous\n";
                        table+="==============================================="
                                + "====\n";
                        table+=String.format("%9s%15.1f%14.2f",
                                c.getLoad().getName(), c.getLoad().getWeight(),
                                c.getLoad().getValue());

                        if(c.getLoad().isDangerous())
                            table+=String.format("%13s", "YES\n");
                        else
                            table+=String.format("%13s", "NO\n");

                        System.out.println(table);
                    }
                    else
                        System.out.println("\nCar successfully unlinked.\n");
                }
            }

            //print menu
            printMenu();

            //sets up for user input
            input = new Scanner(System.in);
            letter = input.nextLine();
        }
        System.out.print("\nProgram terminating sucessfully...");
    }

    //prints the menu
    public static void printMenu()
    {
        System.out.print("(F) Cursor Forward\n"
                + "(B) Cursor Backward\n"
                + "(I) Insert Car After Cursor\n"
                + "(R) Remove Car At Cursor\n"
                + "(L) Set Product Load\n"
                + "(S) Search For Product\n"
                + "(T) Display Train\n"
                + "(M) Display Manifest\n"
                + "(D) Remove Dangerous Cars\n"
                + "(Q) Quit\n\n"
                + "Enter a selection: ");
    }
}