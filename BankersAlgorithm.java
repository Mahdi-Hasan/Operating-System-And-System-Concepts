package bankers.algorithm;

import java.util.Scanner;

class SafetyState {

    int ser = 0;
    int[] safeorder = new int[50];

    synchronized void crts(int thID, int[][] allocate, int[][] need, int[] Available) {
        boolean e = false;
        for (int j = 0; j < 3; j++) {
            if (need[thID][j] > Available[j]) {
                e = true;
                break;
            }
        }
        if (!e) {

            ser++;

            for (int j = 0; j < 3; j++) {
                Available[j] += allocate[thID][j];
                allocate[thID][j] = 0;
                need[thID][j] = 100;
            }

            System.out.println("thread " + thID + " serial :" + ser);
            safeorder[ser] = thID;
            System.out.println("");
            System.out.print("Availabe : ");
            for (int j = 0; j < 3; j++) {
                System.out.print(Available[j] + " ");
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Allocation       Need");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(allocate[i][j] + " ");
                }
                System.out.print("          ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(need[i][j] + " ");
                }
                System.out.println("");
            }
            if (ser == 5) {
                for (int i = 1; i <= 5; i++) {
                    System.out.print(" th-> " + safeorder[i]);
                }
            }

        }
    }
}

class thread extends Thread {

    SafetyState st;
    int thId;
    int[][] Allocate = new int[5][3];
    int[][] Need = new int[5][3];
    int[] Available = new int[3];
    int[] work = new int[3];

    thread(SafetyState stpp, int thID, int[][] allocate, int[][] need, int[] available) {
        st = stpp;
        thId = thID;
        Allocate = allocate;
        Need = need;
        Available = available;
    }

    @Override
    public void run() {
        while (st.ser < 5) {
            st.crts(thId, Allocate, Need, Available);
        }

    }

}

public class BankersAlgorithm {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] Max = new int[5][3];
        int[][] Allocate = new int[5][3];
        int[][] Need = new int[5][3];
        int[] Available = new int[3];
        int[] work = new int[3];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                int b = in.nextInt();
                Allocate[i][j] = b;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                int a = in.nextInt();
                Max[i][j] = a;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                Need[i][j] = Max[i][j] - Allocate[i][j];
            }
        }
        for (int i = 0; i < 3; i++) {
            int b = in.nextInt();
            Available[i] = b;
            work[i] = b;
        }
        SafetyState st = new SafetyState();
        thread th0 = new thread(st, 0, Allocate, Need, Available);
        thread th1 = new thread(st, 1, Allocate, Need, Available);
        thread th2 = new thread(st, 2, Allocate, Need, Available);
        thread th3 = new thread(st, 3, Allocate, Need, Available);
        thread th4 = new thread(st, 4, Allocate, Need, Available);

        th0.start();
        th1.start();
        th2.start();
        th3.start();
        th4.start();
    }
}
/*
0 1 0      
2 0 0      
3 0 2      
2 1 1      
0 0 2      

 7 5 3
 3 2 2
 9 0 2
 2 2 2
 4 3 3

 3 3 2

*/
