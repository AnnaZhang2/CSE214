/**
 * The TrainLinked List class implements a Double-Linked List ADT. It contains
 * references to the head and tail of the list, and a cursor variable. In
 * addition, it provides methods to perform insertion, deletion, search and
 * various other operations.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  Stony Brook ID: 112167606
 */

public class TrainLinkedList
{
    //instance variables
    private TrainCarNode head;
    private TrainCarNode tail;
    private TrainCarNode cursor;
    private int size;
    private double totalLength;
    private double totalValue;
    private double totalWeight;
    private boolean danger;

    /**
     * Constructs an instance of the TrainLinkedList with no TrainCar objects
     * in it.
     *
     * PostConditions:
     *  This TrainLinkedList has been initialized to an empty linked list.
     *  head, tail, and cursor are all set to null.
     */
    public TrainLinkedList()
    {
        head=null;
        tail=null;
        cursor=null;
        size=0;
        totalLength=0;
        totalValue=0;
        totalWeight=0;
        danger=false;
    }

    /**
     * Returns a reference to the TrainCar at the node currently referenced by
     * the cursor.
     *
     * Preconditions:
     *  The list is not empty (cursor is not null).
     *
     * @return
     *  The reference to the TrainCar at the node currently referenced by the
     *  cursor.
     */
    public TrainCar getCursorData()
    {
        if(cursor!=null)
            return cursor.getTrainCar();
        else
            throw new NullPointerException("cursor is null");
    }

    /**
     * Returns a reference to the TrainCar at the node currently referenced by
     * the cursor.
     *
     * @param car
     *  TrainCar reference.
     *
     * Preconditions:
     *  The list is not empty (cursor is not null).
     *
     * Postconditions:
     *  The cursor node now contains a reference to car as its data.
     */
    public void setCursorData(TrainCar car)
    {
        if(cursor==null)
            throw new NullPointerException("cursor is null.");
        else
            cursor.setTrainCar(car);
    }

    /**
     * Moves the cursor to point at the next TrainCarNode.
     *
     * Preconditions:
     *  The list is not empty (cursor is not null) and cursor does not
     *  currently reference the tail of the list.
     *
     * Postconditions:
     *  The cursor has been advanced to the next TrainCarNode, or has remained
     *  at the tail of the list.
     */
    public void cursorForward() throws EndOfListException
    {
        if(cursor==tail)
            throw new EndOfListException();
        else if(cursor==null)
            throw new NullPointerException("The cursor is null.");
        else
            cursor=cursor.getNext();
    }

    /**
     * Moves the cursor to point at the previous TrainCarNode.
     *
     * Preconditions:
     *  The list is not empty (cursor is not null) and cursor does not
     *  currently reference the head of the list.
     *
     * Postconditions:
     *  The cursor has been moved back to the previous TrainCarNode, or has
     *  remained at the head of the list.
     */
    public void cursorBackward() throws StartOfListException
    {
        if(cursor==head)
            throw new StartOfListException();
        else if(cursor==null)
            throw new NullPointerException("The cursor is null.");
        else
            cursor=cursor.getPrev();
    }

    /**
     *
     * @return
     *  Returns true if the cursor is at the tail.
     *  Returns false otherwise.
     */
    public boolean isAtEnd()
    {
        if(cursor==tail)
            return true;
        return false;
    }

    /**
     *
     * @return
     *  Returns true if the cursor is at the head.
     *  Returns false otherwise.
     */
    public boolean isAtStart()
    {
        if(cursor==head)
            return true;
        return false;
    }

    /**
     * Inserts a car into the train after the cursor position.
     *
     * @param newCar
     *  The new TrainCar to be inserted into the train.
     *
     * Preconditions:
     *  This TrainCar object has been instantiated.
     *
     * Postconditions:
     *  The new TrainCar has been inserted into the train after the position of
     *  the cursor.
     *
     *  All TrainCar objects previously on the train are still on the train,
     *  and their order has been preserved.
     *
     *  The cursor now points to the inserted car.
     *
     * Throws:
     *  IllegalArgumentException - Indicates that newCar is null.
     */
    public void insertAfterCursor(TrainCar newCar)
    {
        //if newCar has not been initialized
        if(newCar==null)
            throw new IllegalArgumentException("newCar has not been "
                    + "initialized");

        //declare and instantiate newC as a Wrapper of newCar
        TrainCarNode newC=new TrainCarNode(newCar);

        //if the list is empty
        if(head==null||cursor==null)
        {
            head=newC;
            tail=newC;
            cursor=newC;

            //updating values
            totalLength+=newCar.getLength();
            totalWeight+=newCar.getWeight();
            size++;
        }
        //if adds to the end
        else if(cursor==tail)
        {
            //connects the current TrainCar to the newCar
            cursor.setNext(newC);
            newC.setPrev(cursor);
            newC.setNext(null);
            //sets cursor to newC
            cursor=newC;
            //Move the tail pointer to newC
            tail=newC;

            //updating values
            totalLength+=newCar.getLength();
            totalWeight+=newCar.getWeight();
            size++;
        }
        //in the middle between head and tail
        else
        {
            newC.setNext(cursor.getNext());
            newC.setPrev(cursor);
            cursor.setNext(newC);
            cursor.getNext().setPrev(newC);

            //updating values
            totalLength+=newCar.getLength();
            totalWeight+=newCar.getWeight();
            size++;
        }
    }

    /**
     * Removes the TrainCarNode referenced by the cursor and returns the
     * TrainCar contained within the node.
     *
     * Preconditions:
     *  The cursor is not null.
     *
     * Postconditions:
     *  The TrainCarNode referenced by the cursor has been removed from the
     *  train.
     *
     *  The cursor now references the next node, or the previous node if no
     *  next node exists.
     *
     * @return
     *  TrainCar reference
     */
    public TrainCar removeCursor()
    {
        TrainCarNode temp=new TrainCarNode(cursor.getTrainCar());

        //if cursor is null
        if(cursor==null)
            throw new NullPointerException("The cursor is null.");

        TrainCarNode prev=cursor.getPrev();
        TrainCarNode next=cursor.getNext();

        String name="";
        double weight=0;
        double value=0;

        if(temp.getTrainCar().getLoad()!=null)
        {
            name=temp.getTrainCar().getLoad().getName();
            weight=temp.getTrainCar().getLoad().getWeight();
            value=temp.getTrainCar().getLoad().getValue();
        }

        if(size==1)
        {
            head=null;
            tail=null;
            cursor=null;

            totalLength=0;
            totalWeight=0;
            size=0;
        }
        else if(cursor==head)
        {
            cursor.setNext(next);
            cursor.setPrev(null);
            cursor=next;
            head=cursor;

            totalLength-=temp.getTrainCar().getLength();
            totalWeight-=temp.getTrainCar().getWeight();
            size--;
        }
        //if the cursor to be removed is at the end
        else if(cursor==tail) //tail
        {
            cursor.setPrev(prev.getPrev());
            cursor=prev;
            tail=prev;
            prev.setNext(null);

            totalLength-=temp.getTrainCar().getLength();
            totalWeight-=temp.getTrainCar().getWeight();
            size--;
        }
        //in the middle between head and tail
        else
        {
            TrainCarNode current=cursor;

            prev.setNext(next);
            next.setPrev(prev);
            current=null;
            cursor=next;

            totalLength-=temp.getTrainCar().getLength();
            totalWeight-=temp.getTrainCar().getWeight();
            size--;
        }

        return temp.getTrainCar();
    }

    /**
     * Determines the number of TrainCar objects currently on the train.
     *
     * @return
     *  The number of TrainCar objects on this train.
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns the total length of the train in meters.
     *
     * @return
     *  The sum of lengths of each TrainCar in the train.
     */
    public double getLength()
    {
        return totalLength;
    }

    /**
     * Returns the total value of product carried by the train
     *
     * @return
     *  The sum of the values of each TrainCar in the train.
     */
    public double getValue()
    {
        return totalValue;
    }

    /**
     * Adds the given value to the totalValue
     *
     * @param v
     *  The value of the load in dollars
     */
    public void addValue(double v)
    {
        totalValue+=v;
    }

    /**
     * Returns the total weight in tons of the train. Note that the weight of
     * the train is the sum of the weights of each empty TrainCar, plus the
     * weight of the ProductLoad carried by that car.
     *
     * @return
     *  The sum of the weight of each TrainCar plus the sum of the ProductLoad
     *  carried by that car.
     */
    public double getWeight()
    {
        return totalWeight;
    }

    /**
     * Adds the given weight to the totalWeight
     *
     * @param w
     *  The weight of the load in tons
     */
    public void addWeight(double w)
    {
        totalWeight+=w;
    }

    /**
     * Whether or not there is a dangerous product on one of the TrainCar
     * objects on the train.
     *
     * @return
     *  Returns true if the train contains at least one TrainCar carrying a
     *  dangerous ProductLoad, false otherwise.
     */
    public boolean isDangerous()
    {
        return danger;
    }

    /**
     * This will only be used when one of the ProductLoad is dangerous.
     *
     * @param d
     *  Whether the load is dangerous or not
     */
    public void setDangerous(boolean d)
    {
        danger=d;
    }

    /**
     * Searches the list for all ProductLoad objects with the indicated name
     * and sums together their weight and value (Also keeps track of whether
     * the product is dangerous or not), then prints a single ProductLoad
     * record to the console.
     *
     * @param name
     *  The name of the ProductLoad to find on the train
     */
    public void findProduct(String name)
    {
        //start at the beginning
        TrainCarNode current=head;

        double weight=0.0;
        double value=0.0;
        boolean dangerous=false;
        int count=0;
        String n="";

        while(current!=null)
        {
            if(current.getTrainCar().getLoad().getName().
                    equalsIgnoreCase(name))
            {
                if(current.getTrainCar().getLoad()!=null)
                {
                    weight+=current.getTrainCar().getLoad().getWeight();
                    value+=current.getTrainCar().getLoad().getValue();
                }
                else
                {
                    weight+=0;
                    value+=0;
                }

                count++;

                //makes sure that the boolean value of isDangerous is that for
                //the first match found
                if(count==1)
                {
                    n=current.getTrainCar().getLoad().getName();
                    dangerous=current.getTrainCar().getLoad().isDangerous();
                }
            }
            current=current.getNext();
        }

        if(count!=0)
        {
            System.out.println("The following products were found on "+count
                    +" cars:");

            String table="    Name      Weight (t)     Value ($)   "
                    + "Dangerous\n";
            table+="========================================="
                    + "==========\n";
            table+=String.format("%9s%15.1f%14.2f", n, weight, value);

            if(dangerous)
                table+=String.format("%13s", "YES\n");
            else
                table+=String.format("%13s", "NO\n");

            System.out.println(table);
        }
        else
            System.out.println("No record of "+name+" on board train.\n");
    }

    /**
     * Prints a neatly formatted table of the car number, car length, car weight,
     * load name, load weight, load value, and load dangerousness for all of the
     * car on the train.
     */
    public void printManifest()
    {
        //start at the beginning
        TrainCarNode current=head;

        int count=1;

        String table="    CAR:                               LOAD:\n";
        table+="      Num   Length (m)    Weight (t)  |    Name      "
                + "Weight (t)     Value ($)   Dangerous\n";
        table+="    ==================================+===================="
                + "===============================\n";

        while(current!=null)
        {
            if(current!=cursor)
                table+="    ";
            else
                table+=" -> ";

            table+=String.format("%4d%14.1f%14.1f%3s", count,
                    current.getTrainCar().getLength(),
                    current.getTrainCar().getWeight(), "|");

            if(current.getTrainCar().getLoad()==null)
                table+=String.format("%10s%14.1f%14.2f%12s", "Empty", 0.0, 0.0,
                        "NO");
            else
            {
                if(current.getTrainCar().getLoad().isDangerous())
                    table+=String.format("%10s%14.1f%14.2f%12s",
                            current.getTrainCar().getLoad().getName(),
                            current.getTrainCar().getLoad().getWeight(),
                            current.getTrainCar().getLoad().getValue(),
                            "YES");
                else
                    table+=String.format("%10s%14.1f%14.2f%12s",
                            current.getTrainCar().getLoad().getName(),
                            current.getTrainCar().getLoad().getWeight(),
                            current.getTrainCar().getLoad().getValue(),
                            "NO");
            }
            table+="\n";

            count++;
            current=current.getNext();
        }

        System.out.println(table);
    }

    /**
     * Removes all dangerous cars from the train, maintaining the order of the
     * cars in the train.
     *
     * Postconditions:
     *  All dangerous cars have been removed from this train.
     *
     *  The order of all non-dangerous cars must be maintained upon the
     *  completion of this method.
     */
    public void removeDangerousCars()
    {
        if(isDangerous())
        {
            //start at the beginning
            TrainCarNode current=head;

            while(current!=null)
            {
                if(current.getTrainCar().getLoad()!=null)
                {
                    if(current.getTrainCar().getLoad().isDangerous())
                    {
                        cursor=current;
                        removeCursor();
                    }
                }
                current=current.getNext();
            }
            danger=false;
            size--;
            System.out.println("Dangerous cars sucessfully removed from "
                    + "the train.");
        }
        else
            System.out.println("There are no dangerous cars to be removed.");
    }

    /**
     * Returns a neatly formatted String representation of the train.
     *
     * @return
     *  A neatly formatted string containing information about the train,
     *  including its size (number of cars), length in meters, weight in tons,
     *  value in dollars, and whether it is dangerous or not.
     */
    public String toString()
    {
        String output = "Train: " + size + " cars, " + totalLength + " meters, "
                + String.format("%4.1f", totalWeight) + " tons, $"
                + String.format("%1.2f", totalValue) + " value, ";

        if(danger)
            output+="dangerous";
        else
            output+="not dangerous";

        return output;
    }
}