package edu.kinath.home.deadlockdemo;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner
{
    Account account1 = new Account();
    Account account2 = new Account();

    private Lock lock1 = new ReentrantLock( );
    private Lock lock2 = new ReentrantLock( );

    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException
    {
        while( true )
        {
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try
            {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            }
            finally
            {
                if( gotFirstLock && gotSecondLock )
                {
                    return;
                }

                if( gotFirstLock )
                {
                    firstLock.unlock();
                }
                if( gotSecondLock )
                {
                    secondLock.unlock();
                }
            }

            Thread.sleep( 1 );
        }
    }

    public void firstThread() throws InterruptedException
    {
        Random random = new Random();
        for( int i = 0 ; i < 10000 ; i++ )
        {
            try
            {
                acquireLocks( lock1, lock2 );
                Account.transfer( account1, account2, random.nextInt( 100 ) );
            }
            finally
            {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException
    {
        Random random = new Random();
        for( int i = 0 ; i < 10000 ; i++ )
        {
            try
            {
                acquireLocks( lock1, lock2 );
                Account.transfer( account2, account1, random.nextInt( 100 ) );
            }
            finally
            {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished()
    {
        System.out.println( "Account 1 balance : " + account1.getBalance() );
        System.out.println( "Account 2 balance : " + account2.getBalance() );
        System.out.println( "Total balance : " + ( account1.getBalance() + account2.getBalance() ) );
    }
}
