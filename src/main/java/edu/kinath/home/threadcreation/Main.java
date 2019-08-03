package edu.kinath.home.threadcreation;

import org.junit.Test;

public class Main
{
    @Test
    public void usingThreadClass()
    {
        ThreadA t1 = new ThreadA();
        ThreadA t2 = new ThreadA();

        t1.start();
        t2.start();
    }

    @Test
    public void usingRunnable()
    {
        Thread t1 = new Thread(new ThreadB());
        Thread t2 = new Thread(new ThreadB());

        t1.start();
        t2.start();
    }

    @Test
    public void usingAnonymousInnerClass()
    {
        Thread t1 = new Thread( new Runnable()
        {
            public void run()
            {
                for(int i = 0 ; i < 100 ; i++)
                {
                    System.out.println("Hello " + i);
                }
            }
        } );

        Thread t2 = new Thread( new Runnable()
        {
            public void run()
            {
                for(int i = 0 ; i < 100 ; i++)
                {
                    System.out.println("World " + i);
                }
            }
        } );

        t1.start();
        t2.start();
    }
}
