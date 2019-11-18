import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Simulator class contains the main method that tests your simulation.
 *
 * @author Anna Zhang
 *  email: anna.zhang.2@stnoybrook.edu
 *  Stony Brook ID: 112167606
 */

public class Simulator
{
    public static final int MAX_PACKETS = 3;

    /**
     * the main() method will prompt the user for inputs to the simulator. It
     * will then run the simulator, and outputs the result. Prompt the user
     * whether he/she wants to run another simulation
     */
    public static void main(String[] args)
    {
        System.out.println("Starting simulator...\n");

        int numIntRouters=0, maxBufferSize=0, minPacketSize=0, maxPacketSize=0;
        int bandwidth=0, duration=0;
        double arrivalProb=0;

        boolean inputIAccepted=false;
        boolean inputAAccepted=false;
        boolean inputBAccepted=false;
        boolean inputMinAccepted=false;
        boolean inputMaxAccepted=false;
        boolean inputBandAccepted=false;
        boolean inputDAccepted=false;

        while(!inputIAccepted)
        {
            System.out.print("Enter the number of Intermediate routers: ");
            Scanner inputI = new Scanner(System.in);

            try
            {
                numIntRouters = inputI.nextInt();
                inputIAccepted=true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("\n***Enter an INTEGER NUMBER for the number"
                        + " of Intermediate routers.***\n");
            }
        }

        while(!inputAAccepted)
        {
            System.out.print("\nEnter the arrival probability of a packet: ");
            Scanner inputA = new Scanner(System.in);

            try
            {
                arrivalProb = inputA.nextDouble();
                inputAAccepted=true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("\n***Enter a DECIMAL for the arrival "
                        + "probability of a packet.***");
            }
        }

        while(!inputBAccepted)
        {
            System.out.print("\nEnter the maximum buffer size of a router: ");
            Scanner inputB = new Scanner(System.in);
            try
            {
                maxBufferSize = inputB.nextInt();
                inputBAccepted=true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("\n***Enter an INTEGER NUMBER for the "
                        + "maximum buffer size of a router.***");
            }
        }

        while(!inputMinAccepted)
        {
            System.out.print("\nEnter the minimum size of a packet: ");
            Scanner inputMin = new Scanner(System.in);
            try
            {
                minPacketSize = inputMin.nextInt();
                inputMinAccepted=true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("\n***Enter an INTEGER NUMBER for the "
                        + "minimum size of a packet.***");
            }
        }

        while(!inputMaxAccepted)
        {
            System.out.print("\nEnter the maximum size of a packet: ");
            Scanner inputMax = new Scanner(System.in);
            try
            {
                maxPacketSize = inputMax.nextInt();
                inputMaxAccepted=true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("\n***Enter an INTEGER NUMBER for the "
                        + "maximum size of a packet.***");
            }
        }

        while(!inputBandAccepted)
        {
            System.out.print("\nEnter the bandwidth size: ");
            Scanner inputBand = new Scanner(System.in);
            try
            {
                bandwidth = inputBand.nextInt();
                inputBandAccepted=true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("\n***Enter an INTEGER NUMBER for the "
                        + "bandwidth size.***");
            }

        }

        while(!inputDAccepted)
        {
            System.out.print("\nEnter the simulation duration: ");
            Scanner inputD = new Scanner(System.in);
            try
            {
                duration = inputD.nextInt();
                inputDAccepted=true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("\n***Enter an INTEGER NUMBER for the "
                        + "simulation duration.***");
            }
        }

        Simulator.simulate(duration, arrivalProb, minPacketSize, maxPacketSize
                , numIntRouters, maxBufferSize, bandwidth);

//        System.out.println("\nDo you want to try another simulation? (y/n): ");
//        Scanner inputYN = new Scanner(System.in);
//        String yn = inputYN.nextLine();
//        
//        while(yn.equalsIgnoreCase("y"))
//        {
//            Simulator.simulate(duration, arrivalProb, minPacketSize,
//              maxPacketSize, numIntRouters, maxBufferSize, bandwidth);
//            
//            inputYN = new Scanner(System.in);
//            yn = inputYN.nextLine();
//        }
    }

    /**
     * Runs the simulator as described in the specs and calculates the average
     * time each packet spends within the network.
     *
     * @return
     *  Returns the average time each packet spends within the network
     */
    public static double simulate(int duration, double arrivalProb, int minSize,
                                  int maxSize, int numIntRouters, int maxBufferSize, int bandwidth)
    {
        //Level 1 router
        Router dispatcher = new Router();
        //Level 2 routers
        ArrayList<Router> routers = new ArrayList<Router>();
        for(int j=0; j<numIntRouters; j++)
        {
            routers.add(new Router());
        }
        //fake router that stores packets ready to be sent but weren't able to
        //due to bandwidth
        Router fake = new Router();
        //to locate the router the packet is in
        int in=0;
        //Contains the running sum of the total time each packet is in the
        //network; service time per packet: timeDest - timeArrive
        //when a packet counter=0, dequeue it from router queue and add time to
        //to total time
        //ignore leftover Packets in network when simulation time is up
        int totalServiceTime=0;
        //contains the total number of packets that has been successfully
        //forwarded to the destination
        //when packet counter=0, dequeue it from router queue and increase count
        //by 1
        int totalPacketsArrived=0;
        //number of packets that have been dropped due to a congested network
        //only happens when senPacketTo(...) throws an exception
        int packetsDropped=0;
        //average time each packet spends within the network
        double avgWaitTime=0;
        //keeps track of packets created
        int packet=0;
        //keeps track of bandwidth without changing bandwidth
        int b=bandwidth;

        for(int i=1; i<=duration; i++)
        {
            if(arrivalProb>0&&arrivalProb<1)
            {
                System.out.println("\nTime: " + i);

                int index=0;

                //for dispatcher router
                for(int j=0; j<=MAX_PACKETS-1; j++)
                {
                    if(Math.random()<arrivalProb)
                    {
                        packet++;

                        dispatcher.add(new Packet(randInt(minSize, maxSize),
                                packet));
                        dispatcher.get(index).setID(packet);
                        dispatcher.get(index).setTimeArrive(i);

                        System.out.println("Packet "
                                + dispatcher.get(index).getID()
                                + " arrives at dispatcher with size "
                                + dispatcher.get(index).getPacketSize() + ".");

                        index++;
                    }
                }
                if(dispatcher.isEmpty())
                    System.out.println("No packets arrived.");

                //for intermediate router
                while(!dispatcher.isEmpty())
                {
                    try
                    {
                        int routerNum=Router.sendPacketTo(routers,
                                maxBufferSize);
                        Packet removed = dispatcher.remove(0);
                        routers.get(routerNum).enqueue(removed);
                        System.out.println("Packet " + removed.getID() + " sent"
                                + " to Router " + (routerNum+1) + ".");
                    }
                    catch(FullRouterBufferException e)
                    {
                        for(int j=0; j<dispatcher.size(); j++)
                        {
                            dispatcher.dequeue();
                            packetsDropped++;
                        }
                    }
                }

                //Decrements timeToDest by 1
                for(int j=0; j<routers.size(); j++)
                {
                    Packet p = routers.get(j).peek();
                    if(p!=null)
                    {
                        if(p.getTimeToDest()>0)
                            p.decrementTimeToDest();

                        p.incrementSimNum();

                        if(p.getTimeToDest()==0)
                        {
                            if(b>0)
                            {
                                Packet removed;
                                if(!fake.isEmpty())
                                {
                                    fake.dequeue();
                                    removed = routers.get(in).dequeue();
                                    totalServiceTime += removed.getSimNum();
                                    totalPacketsArrived++;
                                    b--;
                                }
                                else
                                {
                                    removed = routers.get(j).dequeue();
                                    totalServiceTime += removed.getSimNum();
                                    totalPacketsArrived++;
                                    b--;
                                }

                                p.decrementSimNum();

                                System.out.println("Packet " + removed.getID()
                                        + " has successfully reached its destination:"
                                        + " +"+removed.getSimNum());
                            }
                            else
                            {
                                fake.enqueue(p);
                                p.decrementSimNum();
                            }
                        }

                    }
                }
            }
            else
                System.out.println("No packets arrived.");

            for(int k=1; k<=routers.size(); k++)
            {
                System.out.println("R" + k + " " + routers.get(k-1));
            }

            b=bandwidth;
        }

        System.out.println("\nSimulation ending...");
        System.out.println("Total Service Time: " + totalServiceTime);
        System.out.println("Total packets served: " + totalPacketsArrived);
        System.out.println("Average Service Time per packet: "
                + String.format("%.2f", (double)totalServiceTime/totalPacketsArrived));
        System.out.println("Total Packets Dropped: "+packetsDropped);
        return avgWaitTime;
    }

    /**
     * Helper method that can generate a random number between minVal and
     * maxVal, inclusively.
     *
     * @param minVal
     *  Minimum value of the range.
     *
     * @param maxVal
     *  Maximum value of the range.
     *
     * @return
     *  Returns randomly generated number from minVal to maxVal, inclusively
     */
    private static int randInt(int minVal, int maxVal)
    {
        return (int)(Math.random()*(maxVal-minVal+1)+minVal);
    }
}