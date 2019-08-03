package edu.kinath.home.threadcreation;

public class ThreadA extends Thread
{
    @Override
    public void run()
    {
        for( int i = 0 ; i < 100 ; i++ )
        {
            System.out.println("Hello " + i);
        }
    }
}
