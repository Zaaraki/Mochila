
package mochila;

/**
 * @author amaro
 */

import com.sun.deploy.util.ArrayUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Mochila {

    static int max(int a, int b) {
        return (a > b) ? a : b;
    } // Máximo de dois inteiros

    static int knapSack(int W, int wt[], int val[], int n) // valor max que pode ser colocado na mochila de cap M
    {

        int i, w;
        int K[][] = new int[n + 1][W + 1];

        //Constrói tabela K[][] de baixo para cima
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }

        }
        return K[n][W];
    }

    public static int doParseData(String filePathIn, ArrayList<Integer> weightsVector, ArrayList<Integer> valuesVector) {
        InputStream fileIn;
        try {
            fileIn = new FileInputStream(filePathIn);
            try {
                BufferedReader streamIn = new BufferedReader(new InputStreamReader(fileIn));

                String[] main = streamIn.readLine().split(" "); // Vector size, backpack capacity
                String weights[] = streamIn.readLine().split(" "); // Vector of weights
                String values[] = streamIn.readLine().split(" "); // Vector of values

                // First line should have 2 values
                if (main.length != 2) {
                    streamIn.close();
                    return -1;
                }

                int vectorSize = Integer.parseInt(main[0]);
                int backpackSize = Integer.parseInt(main[1]);

                // Vector should have same length estabilished on 'vectorSize' value
                if (weights.length != vectorSize || values.length != vectorSize) {
                    streamIn.close();
                    return -1;
                }

                // Initialize vectors
                for (int i = 0; i < vectorSize; i++) {
                    weightsVector.add(Integer.parseInt(weights[i]));
                    valuesVector.add(Integer.parseInt(values[i]));
                }

                streamIn.close();
                return backpackSize;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(">> [ERROR] Error while reading the input file.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(">> [ERROR] File does not exists.");
        }
        return -1;
    }

    // Driver program to test above function
    public static void main(String args[]) {
//        int vi[] = new int[]{1, 6, 18, 22, 28, 40, 60}; //VALORES
//        int pi[] = new int[]{1, 2, 5, 6, 7, 9, 11}; //PESOS
//        int M = 23; //CAPACIDADE
//        int n = vi.length; //NÚMERO DE OBJETOS

        ArrayList<Integer> weightsVector = new ArrayList<Integer>(), valuesVector = new ArrayList<Integer>();
        int backpackSize = doParseData("src/mochila02.txt", weightsVector, valuesVector);

        int [] val= new int[valuesVector.size()];
        int [] peso= new int[weightsVector.size()];

        int qtdItem = val.length;

        for(int i = 0; i < valuesVector.size(); i++) {
            if (valuesVector.get(i) != null) {
                val[i] = valuesVector.get(i);
            }
        }

        for(int i = 0; i < weightsVector.size(); i++) {
            if (weightsVector.get(i) != null) {
                peso[i] = weightsVector.get(i);
            }
        }

        for (int i = 0; i < val.length; i++) {
            System.out.println(val[i]);
        }


        for (int i = 0; i < peso.length; i++) {
            System.out.println(peso[i]);
        }

        System.out.println( backpackSize + " " + qtdItem);

        System.out.println("Melhor solucao: " + knapSack(backpackSize, peso, val, qtdItem));
    }
}