
package algorithms;

import java.util.Random;

/**
 * Random number object is an array of numbers that object
 * can shuffle if requested.
 * @author Henri Korpela
 */
public class Random_Numbers {
     private int[] numbers;
     private int add_counter;
     private Random random;
     
     /**
      * Creates new Random_Numbers with array of given size.
      * @param size size of the array.
      */
     public Random_Numbers(int size)
     {
         this.numbers = new int[size];
         this.random = new Random();
         this.add_counter = 0;
     }
     
     /**
      * Fills array with numbers with zero to
      * arrays size - 1. Added numbers are in order.
      */
     public void default_ini()
     {
         for(int i = 0;i < this.numbers.length;i ++)
         {
             this.numbers[i] = i;
         }
     }
     
     /**
      * Return number that is in arrays given index.
      * If index is bigger than array size returns
      * number in index given index - array size.
      * @param index index that number is retrieved from.
      * @return number in given index.
      */
     public int get_number(int index)
     {
         if(index > this.numbers.length - 1)
         {
             return this.numbers[index - this.numbers.length];
         }
         return this.numbers[index];
     }
     
     /**
      * Shuffles array.
      */
     public void shuffle()
     {
         for(int i = 0;i < this.numbers.length;i ++)
         {
             int swap_1 = this.random.nextInt(this.numbers.length);
             int swap_2 = this.random.nextInt(this.numbers.length);
             this.swap(swap_1, swap_2);
         }
     }
     
     /**
      * Adds given number to the array.
      * @param number number to be added.
      */
     public void add_number(int number)
     {
         this.numbers[this.add_counter] = number;
         this.add_counter ++;
     }
     
     /**
      * returns size of the array.
      * @return size of the array.
      */
     public int size()
     {
         return this.numbers.length;
     }
     
     private void swap(int a, int b)
     {
         int temp = this.numbers[a];
         this.numbers[a] = this.numbers[b];
         this.numbers[b] = temp;
     }
}
