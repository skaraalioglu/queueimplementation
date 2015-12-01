//********************************************************************
//  CircularArrayQueue.java       Authors: Lewis/Chase
//
//  Represents an array implementation of a queue in which the
//  indexes for the front and rear of the queue circle back to 0
//  when they reach the end of the array.
//********************************************************************

package jss2;


import datastructures.exceptions.EmptyCollectionException;
import java.util.Iterator;

public class CircularArrayQueue<T> implements QueueADT<T>
{
   private final int DEFAULT_CAPACITY = 100;
   private int front, rear, count;
   private T[] queue; 

   //-----------------------------------------------------------------
   //  Creates an empty queue using the default capacity.
   //-----------------------------------------------------------------
   public CircularArrayQueue()
   {
      front = rear = count = 0;
      queue = (T[]) (new Object[DEFAULT_CAPACITY]);
   }

   //-----------------------------------------------------------------
   //  Creates an empty queue using the specified capacity.
   //-----------------------------------------------------------------
   public CircularArrayQueue (int initialCapacity)
   {
      front = rear = count = 0;
      queue = ( (T[])(new Object[initialCapacity]) );
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the rear of the queue, expanding
   //  the capacity of the queue array if necessary.
   //-----------------------------------------------------------------
   public void enqueue (T element)
   {
      if (size() == queue.length) 
         expandCapacity();

      queue[rear] = element;

      rear = (rear+1) % queue.length;

      count++;
   }

   //-----------------------------------------------------------------
   //  Removes the element at the front of the queue and returns a
   //  reference to it. Throws an EmptyCollectionException if the
   //  queue is empty.
   //-----------------------------------------------------------------
   public T dequeue() throws EmptyCollectionException
   {
      if (isEmpty())
         throw new EmptyCollectionException ("queue");

      T result = queue[front];
      queue[front] = null;

      front = (front+1) % queue.length;

      count--;

      return result;
   }
   
   //-----------------------------------------------------------------
   //  Returns a reference to the element at the front of the queue.
   //  The element is not removed from the queue.  Throws an
   //  EmptyCollectionException if the queue is empty.  
   //-----------------------------------------------------------------
   public T first() throws EmptyCollectionException
   {
      if (isEmpty())
         throw new EmptyCollectionException ("queue"); 

      return queue[front];
   }

   //-----------------------------------------------------------------
   //  Returns true if this queue is empty and false otherwise. 
   //-----------------------------------------------------------------
   public boolean isEmpty()
   {
      return (count == 0);
   }
 
   //-----------------------------------------------------------------
   //  Returns the number of elements currently in this queue.
   //-----------------------------------------------------------------
   public int size()
   {
      return count;
   }


   //-----------------------------------------------------------------
   //  Returns a string representation of this queue. 
   //-----------------------------------------------------------------
  public String toString()
  {
    String result = "";
    int scan = 0;
 
    while(scan < count)
    {
     if(queue[scan]!=null)
     {
       result += queue[scan].toString()+"\n";
     }
    scan++;
    }

    return result;

  }

   //-----------------------------------------------------------------
   //  Creates a new array to store the contents of the queue with
   //  twice the capacity of the old one.
   //-----------------------------------------------------------------
  public void expandCapacity()
  {
    T[] larger = (T[])(new Object[queue.length *2]);   

    for(int scan=0; scan < count; scan++)
    {
      larger[scan] = queue[front];
      front=(front+1) % queue.length;
    }

    front = 0;
    rear = count;
    queue = larger;
  }
}
