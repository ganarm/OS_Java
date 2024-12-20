/*
Q1. Implement FCFS CPU scheduling algorithm, given the list of processes, their CPU burst times and arrival times 
    (take inputs from the user like No. of processes etc.):
    1. Display/print the Gantt chart. 
    2. Print waiting time and turnaround 
        time for each process.
    3. Print average waiting time and 
        average turnaround time.
*/

import java.util.Scanner;

public class FCFS_Scheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Input the number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n]; // Arrival time
        int[] bt = new int[n]; // Burst time
        int[] wt = new int[n]; // Waiting time
        int[] tat = new int[n]; // Turnaround time
        int[] p = new int[n];  // Process IDs

        float avgwt = 0, avgtat = 0;

        // Input arrival time and burst time for each process
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the Arrival Time for Process " + i + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter the Burst Time for Process " + i + ": ");
            bt[i] = sc.nextInt();
            p[i] = i;  // Storing process IDs for later reference
        }

        // Sorting processes by arrival time using a simple sorting algorithm (bubble sort)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (at[i] > at[j]) {
                    // Swap the arrival times
                    int temp_at = at[i];
                    at[i] = at[j];
                    at[j] = temp_at;

                    // Swap the burst times
                    int temp_bt = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp_bt;

                    // Swap the process IDs
                    int temp_pid = p[i];
                    p[i] = p[j];
                    p[j] = temp_pid;
                }
            }
        }

        // Initializing waiting time for the first process
        wt[0] = 0;

        // Calculating waiting times for each process
        for (int i = 1; i < n; i++) {
            wt[i] = bt[i - 1] + wt[i - 1] - at[i];
            if (wt[i] < 0) {  // If waiting time is negative, set it to 0
                wt[i] = 0;
            }
        }

        // Calculating turnaround times and total times
        for (int i = 0; i < n; i++) {
            tat[i] = wt[i] + bt[i];
            avgwt += wt[i];
            avgtat += tat[i];
        }

        // Displaying Gantt Chart
        System.out.println("\nGantt Chart: ");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.print("|   P" + p[i] + "   ");
        }
        System.out.println("|");
        System.out.println("-------------------------------------------------");

        // Printing the time slots for Gantt chart
        int[] temp = new int[n + 1];
        temp[0] = 0;
        for (int i = 0; i < n; i++) {
            temp[i + 1] = temp[i] + bt[i];
        }
        for (int i = 0; i < n + 1; i++) {
            System.out.print(temp[i] + "        ");
        }
        System.out.println("\n");

        // Displaying process details
        System.out.println("Process | ArrivalTime | BurstTime | WaitingTime | TurnAroundTime ");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("   P%d   |%7d      |%7d    |%7d      | %7d\n", p[i], at[i], bt[i], wt[i], tat[i]);
        }

        // Displaying average times
        System.out.printf("\nAverage Waiting Time: %.2f", avgwt / n);
        System.out.printf("\nAverage Turnaround Time: %.2f\n", avgtat / n);

        sc.close();
    }
}
