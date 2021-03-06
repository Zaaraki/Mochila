
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
        int K[][] = new int[n + 1][W + 1]; // Armazena todas as soluções


        //Constrói tabela K[][] de baixo para cima
        for (i = 0; i <= n; i++) {  //Considerar um por um todos os itens
            for (w = 0; w <= W; w++) { //Tentando todas as possibilidades para encontrar as soluções para as combinações
                if (i == 0 || w == 0)  //Se o número de itens é zero ou w é zero,então 1a col e linha é 0
                    K[i][w] = 0;
                else if (wt[i - 1] <= w) { //Se não excedeu o peso, coloca na mochila
                    K[i][w] = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                }

                else // Excedeu o peso
                    K[i][w] = K[i - 1][w];
            }

        }

        System.out.print("Produtos escolhidos: ");
        for (int m = n, cap = W; m > 0 ; m--){
            if(cap >= wt[m-1]){
                if(K[m][cap] == (val[m-1] + K[n-1][cap - wt[m-1]])){
                    System.out.print(m+" " );
                    cap = cap- wt[m-1];
                }
            }
        }

        System.out.println();
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

        String path = "src/";

        for (int a = 1; a <=2 ; a++) {


            ArrayList<Integer> weightsVector = new ArrayList<Integer>(), valuesVector = new ArrayList<Integer>();
            int backpackSize = doParseData(path+"mochila0" + a + ".txt", weightsVector, valuesVector);

            int[] val = new int[valuesVector.size()];
            int[] peso = new int[weightsVector.size()];

            int qtdItem = val.length;

            for (int i = 0; i < valuesVector.size(); i++) {
                if (valuesVector.get(i) != null) {
                    val[i] = valuesVector.get(i);
                }
            }

            for (int i = 0; i < weightsVector.size(); i++) {
                if (weightsVector.get(i) != null) {
                    peso[i] = weightsVector.get(i);
                }
            }


            System.out.println("Instancia: mochila0" + a + ".txt" );
            System.out.println("Melhor solucao: " + knapSack(backpackSize, peso, val, qtdItem));


            System.out.println();
        }
    }
}