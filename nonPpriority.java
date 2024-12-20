/*
Implement non-preemptive Priority CPU scheduling algorithm, given the 
list of processes, their CPU burst times and arrival times 
(take inputs from the user like No. of processes etc.):
    1. Display/print the Gantt chart.
    2. Print waiting time and turnaround time for each process.
    3. Print average waiting time and average turnaround time.
    4. Schedule the same priority processes with the Round Robin scheduling algorithm.
*/

import java.util.*;

class Process {
    int at, bt, wt, tat, pid, pt, bt1;
}

public class nonPpriority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, avgwt = 0, avgtat = 0, count = 0, t = 0, quantum;
        
        System.out.print("\nEnter The No of Processes: ");
        n = sc.nextInt();
        Process[] p = new Process[n];
        
        // Input Arrival Time, Burst Time and Priority
        for (int i = 0; i < n; i++) {
            p[i] = new Process();
            System.out.print("\nEnter the Arrival Time for Process " + i + ": ");
            p[i].at = sc.nextInt();
            System.out.print("\nEnter the Burst Time for Process " + i + ": ");
            p[i].bt = sc.nextInt();
            System.out.print("\nEnter the Priority for Process " + i + ": ");
            p[i].pt = sc.nextInt();
            p[i].pid = i;
            p[i].bt1 = p[i].bt;
        }

        System.out.print("Enter the Time Quantum for Round Robin: ");
        quantum = sc.nextInt();

        // Sorting processes based on arrival time
        Arrays.sort(p, Comparator.comparingInt(p1 -> p1.at));

        System.out.println("\n\n\nGantt Chart: ");
        System.out.println("-------------------------------------------------");

        int t1 = 0;
        int shopt_p = -1;
        int completed = 0;
        while (completed < n) {
            // Find process with highest priority
            shopt_p = -1;
            for (int i = 0; i < n; i++) {
                if (p[i].bt > 0 && p[i].at <= t1) {
                    if (shopt_p == -1 || p[i].pt < p[shopt_p].pt) {
                        shopt_p = i;
                    }
                }
            }

            if (shopt_p != -1) {
                // Execute the process (Non-preemptive)
                System.out.print(" P" + p[shopt_p].pid + " |");
                t1 += p[shopt_p].bt;
                p[shopt_p].bt = 0;  // Process completed
                p[shopt_p].wt = t1 - p[shopt_p].at - p[shopt_p].bt1;
                p[shopt_p].tat = t1 - p[shopt_p].at;
                avgwt += p[shopt_p].wt;
                avgtat += p[shopt_p].tat;
                completed++;
            }
        }

        System.out.println("|");
        System.out.println("-------------------------------------------------");

        // Print the results
        System.out.println("\nProcess | ArrivalTime | BurstTime | WaitingTime | TurnAroundTime ");
        System.out.println("--------------------------------------------------------------------");
        for (Process pr : p) {
            System.out.println("   P" + pr.pid + "   |" + pr.at + "      |" + pr.bt1 +
                    "    |" + pr.wt + "      | " + pr.tat);
        }

        System.out.println("\nAverage Waiting Time : " + (avgwt / n));
        System.out.println("Average Turnaround Time : " + (avgtat / n));

        // Round Robin Scheduling for same priority processes
        System.out.println("\n\nRound Robin Scheduling for same priority processes:");
        for (int i = 0; i < n - 1; i++) {
            if (p[i].pt == p[i + 1].pt) {
                // Implement Round Robin Scheduling for same priority processes
                int[] rem_bt = new int[n];
                for (int j = 0; j < n; j++) {
                    rem_bt[j] = p[j].bt1;
                }

                int q = 0;
                while (q < n) {
                    for (int j = 0; j < n; j++) {
                        if (rem_bt[j] > 0) {
                            if (rem_bt[j] > quantum) {
                                System.out.print(" P" + (j + 1) + " |");
                                rem_bt[j] -= quantum;
                                q++;
                            }
                        }
                    }
                }
            }
        }
    }
}
