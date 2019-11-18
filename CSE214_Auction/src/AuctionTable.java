import big.data.DataSource;
import java.util.Hashtable;
import java.io.Serializable;
import java.util.Set;
import java.io.FileInputStream;

/**
 * The AuctionTable class uses a hash table that stores the database of open
 * auction to provide constant time insertion and deletion. It also includes a
 * function which will build an AuctionTable from a URL using the BigData
 * library. This function should connect the data source located at the URL,
 * read in all the data members for each record stored in the data source,
 * construct new Auction objects based on the data, and insert the objects in to
 * the table. Exceptions should be thrown if the data source could not be
 * connected to or contains invalid syntax structure.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stnoybrook.edu
 *  Stony Brook ID: 112167606
 *  Section: 06
 */

public class AuctionTable extends Hashtable<String, Auction> implements Serializable
{
    /**
     * Constructs an AuctionTable object by invoking constructor from Hashtable
     */
    public AuctionTable()
    {

    }

    /**
     * Uses the BigData library to construct an AuctionTable from a remote data
     * source.
     *
     * @param URL
     *  String representing the URL for the remote data source
     *
     * Preconditions:
     *  URL represents a data source which can be connected to using the BigData
     *  library.
     *  The data has proper syntax.
     *
     * @return
     *  The AuctionTable constructed from the remote data source.
     *
     * @throws IllegalArgumentException: Thrown if the URL does not represent a
     *  valid data source
     */
    public static AuctionTable buildFromURL(String URL) throws
            IllegalArgumentException
    {
        try{
            DataSource ds = DataSource.connect(URL).load();

            String[] sellerNames = ds.fetchStringArray("listing/seller_info/"
                    + "seller_name");
            String[] currentBids = ds.fetchStringArray("listing/auction_info/"
                    + "current_bid");
            String[] highBidderNames = ds.fetchStringArray("listing/"
                    + "auction_info/high_bidder/bidder_name");
            String[] id = ds.fetchStringArray("listing/auction_info/"
                    + "id_num");
            String[] timeLeft = ds.fetchStringArray("listing/auction_info/"
                    + "time_left");
            String[] memory = ds.fetchStringArray("listing/item_info/memory");
            String[] cpu = ds.fetchStringArray("listing/item_info/cpu");
            String[] hardDrive = ds.fetchStringArray("listing/item_info/"
                    + "hard_drive");

            AuctionTable table = new AuctionTable();

            for(int i=0; i<sellerNames.length; i++)
            {
                String itemInfo = cpu[i]+" - "+memory[i]+" - "+hardDrive[i];

                int indexD=timeLeft[i].indexOf("day");
                int indexH=timeLeft[i].indexOf("hour");
                int day=0;
                int hour=0;
                int total=0;
                day=Integer.parseInt(timeLeft[i].substring(0,indexD).trim());
                int indexC=timeLeft[i].indexOf(",");
                hour=Integer.parseInt(timeLeft[i].substring(indexC+2,indexH).
                        trim());
                total=day*24+hour;

                int bid = 0;
                int posComma = currentBids[i].indexOf(",");
                int posPeriod = currentBids[i].indexOf(".");

                if(posComma!=-1)
                    bid=Integer.parseInt(currentBids[i].substring(1,posComma)
                            +currentBids[i].substring(posComma+1,posPeriod));
                else
                    bid=Integer.parseInt(currentBids[i].substring(1,posPeriod));

                Auction a = new Auction(sellerNames[i].trim(), id[i].trim(),
                        highBidderNames[i].trim(), bid, itemInfo.trim(), total);

                table.put(id[i], a);
            }

            return table;
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Manually posts an auction and add it into the table.
     *
     * @param auctionID
     *  The unique key for this object
     * @param auction
     *  The auction to insert into the table with the corresponding auctionID
     *
     * Postconditions:
     *  The item will be added to the table if all given parameters are correct.
     *
     * @throws IllegalArgumentException
     *  If the given auctionID is already stored in the table.
     */
    public void putAuction(String auctionID, Auction auction) throws
            IllegalArgumentException
    {
        if(containsKey(auctionID))
            throw new IllegalArgumentException();
        else
            put(auctionID, auction);
    }


    /**
     * Get the information of an Auction that contains the given ID as key
     *
     * @param auctionID
     *  The unique key for this object
     *
     * @return
     *  An Auction object with the given key, null otherwise
     */
    public Auction getAuction(String auctionID)
    {
        return get(auctionID);
    }

    /**
     * Simulates the passing of time. Decrease the timeRemaining of all Auction
     * objects by the amount specified. The value cannot go below 0.
     *
     * @param numHours
     *  The number of hours to decrease the timeRemaining value by.
     *
     * Postconditions:
     *  All Auctions in the table have their timeRemaining timer decreased. If
     * the original value is less than the decreased value, set the value to 0.
     *
     * @throws IllegalArgumentException
     *  If the given numHours is non positive
     */
    public void letTimePass(int numHours) throws IllegalArgumentException
    {
        if(numHours<=0)
            throw new IllegalArgumentException();

        Set<String> setOfAuctions = keySet();

        for(String auctionID: setOfAuctions)
        {
            if(get(auctionID).getTimeRemaining()>0)
            {
                if(get(auctionID).getTimeRemaining()<numHours)
                    get(auctionID).decrementTimeRemaining(get(auctionID).
                            getTimeRemaining());
                else
                    get(auctionID).decrementTimeRemaining(numHours);
            }
        }
    }

    /**
     * Iterates over all Auction object in the table and removes them if they
     * are expired (timeRemaining == 0)
     *
     * Postconditions:
     *  Only open Auction remain in the table
     */
    public void removeExpiredAuctions()
    {
        Set<String> setOfAuctions = keySet();
        try
        {
            for(String auctionID: setOfAuctions)
            {
                if(get(auctionID).getTimeRemaining()==0)
                    remove(auctionID);
            }
        }
        catch(Exception e)
        {

        }
    }

    /**
     * Prints the AuctionTable in tabular form
     */
    public void printTable()
    {
        Set<String> setOfAuctions = keySet();

        String header = " Auction ID |      Bid   |        Seller         |    "
                + "      Buyer          |    Time   |  Item Info";
        String divider = "====================================================="
                + "=================================================================="
                + "=================================================================="
                + "==============";

        System.out.println(header+"\n"+divider);

        for(String auctionID: setOfAuctions)
        {
            System.out.println(get(auctionID));
        }
    }
}