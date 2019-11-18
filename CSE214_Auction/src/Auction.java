import big.data.DataSource;
import java.io.Serializable;

/**
 * The Auction class represents an active auction currently in the database. The
 * Auction class should contain member variables for the seller's name, the
 * current bid, the time remaining (in hours), current bidder's name,
 * information about the item, and the unique ID for the auction. In addition,
 * the class should implement a toString() method, which should print all of the
 * data members in a neat tabular form.
 *
 * The Auction class can only be altered by a single member method called
 * newBid(), which takes in the name of a bidder and their bid value. This
 * method checks to see if the bid value is greater than the current bid, and if
 * it is, replaces the current bid and buyer name.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stnoybrook.edu
 *  Stony Brook ID: 112167606
 *  Section: 06
 */
public class Auction implements Serializable
{
    private int timeRemaining;
    private double currentBid;
    private String auctionID, sellerName, buyerName, itemInfo;
    /**
     * Constructs an Auction object.
     *
     * @param auctionID
     *  unique ID for the auction
     * @param sellerName
     *  Name of the seller
     * @param buyerName
     *  Name of the buyer
     * @param itemInfo
     *  Information about the item
     * @param timeRemaining
     *  time remaining until the auction is over in hours
     */
    public Auction(String sellerName, String auctionID, String buyerName,
                   double currentBid, String itemInfo, int timeRemaining)
    {
        this.timeRemaining=timeRemaining;
        this.currentBid=currentBid;
        this.auctionID=auctionID;
        this.sellerName=sellerName;
        this.buyerName=buyerName;
        this.itemInfo=itemInfo;
    }

    /**
     * Returns the time remaining until auction is over.
     *
     * @return
     *  Returns the time remaining in hour.
     */
    public int getTimeRemaining()
    {
        return timeRemaining;
    }

    /**
     * Returns the current bid.
     *
     * @return
     *  Returns the amount of current bid.
     */
    public double getCurrentBid()
    {
        return currentBid;
    }

    /**
     * Returns the unique ID of the auction.
     *
     * @return
     *  Returns the unique ID of the auction.
     */
    public String getAuctionID()
    {
        return auctionID;
    }

    /**
     * Returns the name of the seller.
     *
     * @return
     *  Returns the String of the seller name.
     */
    public String getSellerName()
    {
        return sellerName;
    }

    /**
     * Returns the name of the buyer.
     *
     * @return
     *  Returns the String of the buyer name.
     */
    public String getBuyerName()
    {
        return buyerName;
    }

    /**
     * Returns the information of the item being auctioned.
     *
     * @return
     *  Returns the information of the item being auctioned.
     */
    public String getItemInfo()
    {
        return itemInfo;
    }

    /**
     * Decreases the time remaining for this auction by the specified amount.
     * If time is greater than the current remaining time for the auction, then
     * the time remaining is set to 0 (i.e. no negative times).
     *
     * Postconditions:
     *  timeRemaining has been decremented by the indicated amount and is
     *  greater than or equal to 0.
     *
     * @param time
     *  Specified amount of time to decrement the time remaining by
     */
    public void decrementTimeRemaining(int time)
    {
        if(timeRemaining>0)
            timeRemaining-=time;
        else
            timeRemaining=0;
    }

    /**
     * Makes a new bid on this auction. If bidAmt is larger than currentBid,
     * then the value of currentBid is replaced by bidAmt and buyerName is
     * replaced by bidderName.
     *
     * Preconditions:
     *  The auction is not closed (i.e. timeRemaining > 0).
     *
     * Postconditions:
     *  currentBid reflects the largest bid placed on this object. If the
     *  auction is closed, throw a ClosedAuctionException.
     *
     * @param bidderName
     *  The name of the bidder.
     *
     * @param bidAmt
     *  The amount of money bid
     *
     * @throws ClosedAuctionException: Thrown if the auction is closed and no
     *  more bids can be placed (i.e. timeRemaining==0)
     */
    public void newBid(String bidderName, double bidAmt) throws
            ClosedAuctionException
    {
        if(timeRemaining==0)
            throw new ClosedAuctionException();
        else if(bidAmt>currentBid)
        {
            currentBid=bidAmt;
            buyerName=bidderName;
            System.out.println("Bid accepted.\n");
        }
        else
            System.out.println("Bid not accepted.\n");
    }

    /**
     * Returns string of data members in tabular form.
     *
     * @return
     *  Returns string of data members in tabular form.
     */
    public String toString()
    {
        String info="";

        info += "  "+auctionID+" | $";
        if(currentBid==0)
            info+=String.format("%9s", "");
        else
            info+=String.format("%9.2f", currentBid);
        info+=" | ";
        info+=String.format("%-22s", sellerName);
        info+="|  ";
        if(buyerName==null)
            info+=String.format("%-23s","");
        else
            info+=String.format("%-23s",buyerName);
        info+="|";
        info+=String.format("%10s",timeRemaining+" hours");
        info+=" | ";
        info+=String.format("%10s", itemInfo);
        return info;
    }
}