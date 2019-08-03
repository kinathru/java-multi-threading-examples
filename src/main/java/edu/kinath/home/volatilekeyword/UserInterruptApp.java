package edu.kinath.home.volatilekeyword;

import java.util.Scanner;

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
    private boolean shutdown;

    @Override
    public void run()
    {
        while( !shutdown )
        {
            System.out.println("Hello ");
        }
    }

    public void shutdown()
    {
        shutdown = true;
    }
}
