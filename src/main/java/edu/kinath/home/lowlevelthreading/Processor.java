package edu.kinath.home.lowlevelthreading;

import java.util.LinkedList;
import java.util.Random;

public class Processor
{
    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException
    {
        int value = 0;

        while( true )
        {
            synchronized( lock )
            {
                if( list.size() == LIMIT )
                {
                    lock.wait();
                }
                list.add( value++ );
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException
    {
        Random random = new Random( );
        while( true )
        {
            synchronized( lock )
            {
                if( list.isEmpty() )
                {
                    lock.wait();
                }

                System.out.print( "List size is : " + list.size() );
                Integer value = list.removeFirst();
                System.out.println( ", value is " + value );
                lock.notify();
            }

            Thread.sleep( random.nextInt( 1000 ) );
        }
    }
}
