/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mochila;

/**
 *
 * @author amaro
 */
public class Mochila {
 
    static int max(int a, int b) { return (a > b)? a : b; } // Máximo de dois inteiros
      
   // Returns the maximum value that can be put in a knapsack of capacity W
    static int knapSack(int W, int wt[], int val[], int n) // valor max que pode ser colocado na mochila de cap M
    {
         int i, w;
     int K[][] = new int[n+1][W+1];
      
     // Build table K[][] in bottom up manner
     for (i = 0; i <= n; i++)
     {
         for (w = 0; w <= W; w++)
         {
             if (i==0 || w==0)
                  K[i][w] = 0;
             else if (wt[i-1] <= w)
                   K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
             else
                   K[i][w] = K[i-1][w];
         }
      }
      
      return K[n][W];
    }
 
   
    // Driver program to test above function
    public static void main(String args[])
    {
    int vi[] = new int[]{1, 6, 18, 22, 28, 40, 60}; //VALORES
    int pi[] = new int[]{1, 2, 5, 6, 7, 9, 11}; //PESOS
    int  M = 23; //CAPACIDADE
    int n = vi.length; //NÚMERO DE OBJETOS
    System.out.println(knapSack(M, pi, vi, n));
    }
}