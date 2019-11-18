import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * The AuctionSystem class will allow the user to interact with the database by
 * listing open auctions, make bids on open auctions, and create new auctions
 * for different items. In addition, the class should provide the functionality
 * to load a saved (serialized) AuctionTable or create a new one if a saved
 * table does not exist.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stnoybrook.edu
 *  Stony Brook ID: 112167606
 *  Section: 06
 */

public class AuctionSystem implements Serializable
{
    private static AuctionTable auctionTable;
    private static String username;

    /**
     * The method should first prompt the user for a username. This should be
     * stored in username. The rest of the program will be executed on behalf of
     * this user.
     */
    public static void main(String[] args) throws IOException
    {
        System.out.println("Starting...");

        try
        {
            FileInputStream file = new FileInputStream("auction.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            AuctionTable auctions;
            auctions = (AuctionTable) inStream.readObject();

            if(auctions==null)
            {
                System.out.println("No previous auction table detected.");
                System.out.println("Creating new table...");
                auctionTable=new AuctionTable();
            }
            else
            {
                System.out.println("Loading previous AuctionTable...");
                auctionTable=auctions;
            }
        }
        catch(Exception e)
        {
            System.out.println("No previous auction table detected.");
            System.out.println("Creating new table...");
            auctionTable=new AuctionTable();
        }

        System.out.print("\nPlease select a username: ");
        Scanner inputUser=new Scanner(System.in);
        username=inputUser.nextLine();

        System.out.println();
        printMenu();

        System.out.print("\nPlease select an option: ");
        Scanner inputOption=new Scanner(System.in);
        String option=inputOption.nextLine();

        while(!(option.equalsIgnoreCase("D") || option.equalsIgnoreCase("A") ||
                option.equalsIgnoreCase("B") || option.equalsIgnoreCase("I") ||
                option.equalsIgnoreCase("P") || option.equalsIgnoreCase("R") ||
                option.equalsIgnoreCase("T") || option.equalsIgnoreCase("Q")))
        {
            System.out.println("\nInvalid Input\n");
            printMenu();
            System.out.print("Please select an option: ");
            inputOption = new Scanner(System.in);
            option = inputOption.nextLine();
        }

        while(!option.equalsIgnoreCase("Q"))
        {
            if(option.equalsIgnoreCase("D"))
            {
                System.out.print("Please enter a URL: ");
                Scanner inputURL = new Scanner(System.in);
                String url = inputURL.nextLine();

                System.out.println("\nLoading...");
                try
                {
                    auctionTable=AuctionTable.buildFromURL(url);
                    System.out.println("Auction data loaded successfully!\n");
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println("Auction data cannot be loaded.\n");
                }
            }
            else if(option.equalsIgnoreCase("P"))
            {
                System.out.println();
                auctionTable.printTable();
                System.out.println();
            }
            else if(option.equalsIgnoreCase("B"))
            {
                System.out.print("Please enter an Auction ID: ");
                Scanner inputID = new Scanner(System.in);
                String id = inputID.nextLine();

                Auction current = auctionTable.getAuction(id);
                if(current!=null)
                {
                    double currentBid=0;
                    if(current.getTimeRemaining()!=0)
                    {
                        System.out.println("\nAuction "+id+" is OPEN");
                        if(current.getCurrentBid()!=0)
                            System.out.println("    Current Bid: $ "
                                    + String.format("%.2f",current.getCurrentBid()));
                        else
                            System.out.println("    Current Bid: None");

                        System.out.print("\nWhat would you like to bid?: ");
                        Scanner inputBid=new Scanner(System.in);
                        currentBid=inputBid.nextDouble();
                    }
                    try
                    {
                        current.newBid(username, currentBid);
                    }
                    catch(ClosedAuctionException e)
                    {
                        System.out.println("\nAuction "+id+" is CLOSED");
                        if(current.getCurrentBid()!=0)
                            System.out.println("    Current Bid: "
                                    + String.format("%9.2f", current.getCurrentBid()));
                        else
                            System.out.println("    Current Bid: None");
                        System.out.println("\nYou can no longer bid on this"
                                + " item.\n");
                    }
                }
            }
            else if(option.equalsIgnoreCase("A"))
            {
                System.out.println("\nCreating new Auction as "+username+".");

                System.out.print("Please enter an Auction ID: ");
                Scanner inputID=new Scanner(System.in);
                String id=inputID.nextLine();

                System.out.print("Please enter an Auction time (hours): ");
                Scanner inputTime=new Scanner(System.in);
                int time=inputTime.nextInt();

                System.out.print("Please enter some Item Info: ");
                Scanner inputInfo=new Scanner(System.in);
                String itemInfo=inputInfo.nextLine();

                Auction newAuction = new Auction(username, id, null, 0, itemInfo, time);
                auctionTable.putAuction(id, newAuction);

                System.out.println("\nAuction "+id+" inserted into table.\n");
            }
            else if(option.equalsIgnoreCase("T"))
            {
                System.out.print("How many hours should pass: ");
                Scanner inputTime = new Scanner(System.in);
                int time=inputTime.nextInt();

                auctionTable.letTimePass(time);
                System.out.println("\nTime passing...");
                System.out.println("Auction times updated.\n");
            }
            else if(option.equalsIgnoreCase("I"))
            {
                System.out.print("Please enter an Auction ID: ");
                Scanner inputID = new Scanner(System.in);
                String id=inputID.nextLine();

                Auction current = auctionTable.getAuction(id);
                System.out.println("\nAuction "+id);
                System.out.println("    Seller: "+current.getSellerName());
                System.out.println("    Buyer: "+current.getBuyerName());
                System.out.println("    Time: "+current.getTimeRemaining());
                System.out.println("    Info: "+current.getItemInfo()+"\n");
            }
            else if(option.equalsIgnoreCase("R"))
            {
                System.out.println("\nRemoving expired auctions...");
                auctionTable.removeExpiredAuctions();
                System.out.println("All expired auctions removed.\n");
            }

            printMenu();

            System.out.print("\nPlease select an option: ");
            inputOption=new Scanner(System.in);
            option=inputOption.nextLine();

            while(!(option.equalsIgnoreCase("D") || option.equalsIgnoreCase("A") ||
                    option.equalsIgnoreCase("B") || option.equalsIgnoreCase("I") ||
                    option.equalsIgnoreCase("P") || option.equalsIgnoreCase("R") ||
                    option.equalsIgnoreCase("T") || option.equalsIgnoreCase("Q")))
            {
                System.out.println("\nInvalid Input\n");
                printMenu();
                System.out.print("\nPlease select an option: ");
                inputOption = new Scanner(System.in);
                option = inputOption.nextLine();
            }
        }

        System.out.println("\nWriting Auction Table to file...");

        FileOutputStream file = new FileOutputStream("auction.obj");
        ObjectOutputStream outStream = new ObjectOutputStream(file);
        outStream.writeObject(auctionTable);

        System.out.println("Done!");
        System.out.println("\nGoodbye.");
    }

    /**
     * Prints menu
     */
    public static void printMenu()
    {
        System.out.println("Menu:\n(D) - Import Data from URL\n" +
                "(A) - Create a New Auction\n" +
                "(B) - Bid on an Item\n" +
                "(I) - Get Info on Auction\n" +
                "(P) - Print All Auctions\n" +
                "(R) - Remove Expired Auctions\n" +
                "(T) - Let Time Pass\n" +
                "(Q) - Quit");
    }
}