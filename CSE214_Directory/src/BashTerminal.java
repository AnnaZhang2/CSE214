import java.util.Scanner;

/**
 * A class that allows an user to interact with a file system implemented by an
 * instance of DirectoryTree using the following commands.
 *
 *  @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  section: 06
 *  Stony Brook ID: 112167606
 */

public class BashTerminal
{
    //Runs a program which takes user inputs and builds a DirectoryTree using
    //the commands indicated
    public static void main(String[] args) throws IllegalArgumentException
    {
        System.out.println("Starting bash terminal.");

        String nameHost="[zhang127@ArrayOfSunshine]: $ ";
        DirectoryTree tree = new DirectoryTree();

        System.out.print(nameHost);
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();

        while(!(command.equals("pwd".trim()) || command.equals("ls".trim()) ||
                command.equals("ls -R".trim()) || command.equals("cd /".trim()) ||
                command.equals("exit".trim()) || command.equals("cd ..".trim()) ||
                (command.length()>6 && command.substring(0,6).equals("mkdir ")) ||
                (command.length()>3 && command.substring(0,3).equals("cd ")) ||
                (command.length()>6 && command.substring(0,6).equals("touch "))))
        {
            System.out.println("Invalid Input");
            System.out.print(nameHost);
            input = new Scanner(System.in);
            command = input.nextLine();
        }

        while(!command.equals("exit".trim()))
        {
            if(command.equals("pwd".trim()))
                System.out.println(tree.presentWorkingDirectory());
            else if(command.equals("ls".trim()))
                System.out.println(tree.listDirectory());
            else if(command.equals("cd /".trim()))
                tree.resetCursor();
            else if(command.equals("ls -R".trim()))
                tree.printDirectoryTree();
            else if(command.equals("cd ..".trim()))
                tree.moveCursorUp();
            else if(command.length()>3 &&
                    command.substring(0, 3).equals("cd "))
            {
                try
                {
                    boolean spaces=true;
                    for(int i=0; i<command.substring(3).length(); i++)
                    {
                        if(command.substring(3).charAt(i)!=' ')
                            spaces=false;
                    }

                    if(spaces==false)
                        tree.changeDirectoryPath(command.substring(3).trim());
                    else
                        System.out.println("Invalid Input");
                }
                catch(NotADirectoryException e)
                {
                    System.out.println("ERROR: Cannot change directory into "
                            + "file.");
                }
            }
            else if(command.length()>6 &&
                    command.substring(0, 6).equals("mkdir "))
            {
                try
                {
                    boolean spaces=true;
                    for(int i=0; i<command.substring(6).length(); i++)
                    {
                        if(command.substring(6).charAt(i)!=' ')
                            spaces=false;
                    }

                    if(spaces)
                        System.out.println("Invalid Input");
                    else
                        tree.makeDirectory(command.substring(6).trim());
                }
                catch(FullDirectoryException e)
                {
                    System.out.println("ERROR: Present directory is full.");
                }
                catch(NotADirectoryException e)
                {
                    System.out.println("ERROR: Cannot change directory into "
                            + "file.");
                }
            }
            else if(command.length()>6 &&
                    command.substring(0, 6).equals("touch "))
            {
                try
                {
                    boolean spaces=true;
                    for(int i=0; i<command.substring(6).length(); i++)
                    {
                        if(command.substring(6).charAt(i)!=' ')
                            spaces=false;
                    }

                    if(spaces)
                        System.out.println("Invalid Input");
                    else
                        tree.makeFile(command.substring(6).trim());
                }
                catch(FullDirectoryException e)
                {
                    System.out.println("ERROR: Present directory is full.");
                }
            }

            System.out.print(nameHost);
            input = new Scanner(System.in);
            command = input.nextLine();

            while(!(command.equals("pwd".trim()) || command.equals("ls".trim())
                    || command.equals("ls -R".trim()) || command.equals("cd /".trim())
                    || command.equals("exit".trim()) || command.equals("cd ..".trim())
                    || (command.length()>6 && command.substring(0,6).equals("mkdir "))
                    ||(command.length()>3 && command.substring(0,3).equals("cd ")) ||
                    (command.length()>6 && command.substring(0,6).equals("touch "))))
            {
                System.out.println("Invalid Input");
                System.out.print(nameHost);
                input = new Scanner(System.in);
                command = input.nextLine();
            }
        }

        System.out.println("Program terminating normally.");
    }
}