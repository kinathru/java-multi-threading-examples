package edu.kinath.home.threadcreation;

public class ThreadB implements Runnable
{
    public void run()
    {
        for( int i = 0 ; i < 100 ; i++ )
        {
            System.out.println("Hello " + i);
        }
    }
}
