package edu.kinath.home.volatilekeyword;

import java.util.Scanner;

/**
 * This demo is for explaining the usage of 'volatile' keyword.
 *
 * Different implementations of java might save the status of the thread variables in cache. This caching would prevent the thread from reading
 * the correct value. 'volatile' variables are not cached. Therefore thread will always read the current value of the variable.
 *
 */
public class UserInterruptApp
{
    public static void main( String[] args )
    {
        Processor p1 = new Processor();
        p1.start();

        Scanner scanner = new Scanner( System.in );
        scanner.nextLine();

        p1.shutdown();

    }
}

class Processor extends Thread
{
    private volatile boolean shutdown;

    @Override
    public void run()
    {
        while( !shutdown )
        {
            System.out.println("Hello ");

            try
            {
                Thread.sleep( 100 );
            }
            catch( InterruptedException e )
            {
                e.printStackTrace();
            }
        }
    }

    public void shutdown()
    {
        shutdown = true;
    }
}
