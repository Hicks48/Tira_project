
package algorithms;

import java.util.Random;

/**
 *
 * @author Henri Korpela
 */
public class Random_Numbers {
     private int[] numbers;
     private int add_counter;
     private Random random;
     
     public Random_Numbers(int ammount)
     {
         this.numbers = new int[ammount];
         this.random = new Random();
         this.add_counter = 0;
     }
     
     public void default_ini()
     {
         for(int i = 0;i < this.numbers.length;i ++)
         {
             this.numbers[i] = i;
         }
     }
     
     public int get_number(int index)
     {
         return this.numbers[index];
     }
     
     public void shuffle()
     {
         for(int i = 0;i < this.numbers.length;i ++)
         {
             int swap_1 = this.random.nextInt(this.numbers.length);
             int swap_2 = this.random.nextInt(this.numbers.length);
             this.swap(swap_1, swap_2);
         }
     }
     
     public void add_number(int number)
     {
         this.numbers[this.add_counter] = number;
         this.add_counter ++;
     }
     
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
