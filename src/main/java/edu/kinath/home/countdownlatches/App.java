package edu.kinath.home.countdownlatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App
{
    public static void main( String[] args )
    {
        CountDownLatch countDownLatch = new CountDownLatch( 2 );
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for( int i = 0 ; i < 3 ; i++ )
        {
            executor.submit( new Processor( countDownLatch ) );
        }
        try
        {
            countDownLatch.await(); // Awaits until the countdown latch counts down to 0.
        }
        catch( InterruptedException e )
        {
            e.printStackTrace();
        }
        System.out.println("Completed");
    }
}

class Processor implements Runnable
{
    CountDownLatch latch; // Countdown latch is Thread Safe

    public Processor( CountDownLatch latch )
    {
        this.latch = latch;
    }

    public void run()
    {
        System.out.println("Started...");
        try
        {
            Thread.sleep(3000);
        }
        catch( InterruptedException e )
        {
            e.printStackTrace();
        }
        System.out.println("Task completed....");
        latch.countDown();
        System.out.println("Latch Count : " + latch.getCount());
    }
}
