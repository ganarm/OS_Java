/*
Q2. Implement SRTF CPU scheduling algorithm, given the list of processes, their CPU burst times and arrival times (take inputs from the user like No. of processes etc.):
    1. Use FCFS to schedule processes having the same CPU burst.
    2. Display/print the Gantt chart.  
    3. Print waiting time and turnaround time for each process.
    4. Print average waiting time and average turnaround time.
*/

import java.util.*;

class Process {
    int at, bt, wt, tat, pid, rem_bt;
}

public class SRTF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, count = 0, t = 0, complete = 0;
        float avgwt = 0, avgtat = 0;

        // Input number of processes
        System.out.print("Enter the number of processes: ");
        n = sc.nextInt();

        Process[] p = new Process[n];

        // Input Arrival Time and Burst Time
        for (int i = 0; i < n; i++) {
            p[i] = new Process();
            System.out.print("Enter Arrival Time for Process " + i + ": ");
            p[i].at = sc.nextInt();
            System.out.print("Enter Burst Time for Process " + i + ": ");
            p[i].bt = sc.nextInt();
            p[i].pid = i;
            p[i].rem_bt = p[i].bt;
        }

        // Sort the processes by Arrival Time
        Arrays.sort(p, Comparator.comparingInt(p1 -> p1.at));

        // Initialize a Gantt chart array to track process execution
        List<Integer> ganttChart = new ArrayList<>();

        System.out.println("\nGantt Chart:");
        System.out.println("-------------------------------------------");
        System.out.print("Time -> ");

        // Main scheduling loop
        while (count < n) {
            // Sort processes by remaining burst time (shortest remaining time first)
            List<Process> readyQueue = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (p[i].at <= t && p[i].rem_bt > 0) {
                    readyQueue.add(p[i]);
                }
            }

            if (!readyQueue.isEmpty()) {
                // Sort the ready queue based on remaining burst time and arrival time
                readyQueue.sort(Comparator.comparingInt((Process p1) -> p1.rem_bt)
                        .thenComparingInt(p1 -> p1.at));

                Process currentProcess = readyQueue.get(0);
                ganttChart.add(currentProcess.pid);
                currentProcess.rem_bt--;
                t++;

                // If the process is finished, calculate its WT and TAT
                if (currentProcess.rem_bt == 0) {
                    currentProcess.tat = t - currentProcess.at;
                    currentProcess.wt = currentProcess.tat - currentProcess.bt;
                    avgwt += currentProcess.wt;
                    avgtat += currentProcess.tat;
                    count++;
                }
            } else {
                t++; // No process is ready to execute, increment time
            }
        }

        // Print the Gantt Chart
        for (int pid : ganttChart) {
            System.out.print("P" + pid + " ");
        }

        System.out.println("\n-------------------------------------------");

        // Print Waiting Time and Turnaround Time for each process
        System.out.println("\nProcess | Arrival Time | Burst Time | Waiting Time | Turnaround Time");
        System.out.println("---------------------------------------------------------------");
        for (Process pr : p) {
            System.out.println("P" + pr.pid + "      | " + pr.at + "            | " + pr.bt +
                    "          | " + pr.wt + "            | " + pr.tat);
        }

        // Print Average Waiting Time and Average Turnaround Time
        System.out.println("\nAverage Waiting Time: " + (avgwt / n));
        System.out.println("Average Turnaround Time: " + (avgtat / n));
    }
}
/*
Enter the number of processes: 4
Enter Arrival Time for Process 0: 0
Enter Burst Time for Process 0: 2
Enter Arrival Time for Process 1: 3
Enter Burst Time for Process 1: 2
Enter Arrival Time for Process 2: 1
Enter Burst Time for Process 2: 5
Enter Arrival Time for Process 3: 9
Enter Burst Time for Process 3: 3

Gantt Chart:
-------------------------------------------
Time -> P0 P0 P2 P1 P1 P2 P2 P2 P2 P3 P3 P3 
-------------------------------------------

Process | Arrival Time | Burst Time | Waiting Time | Turnaround Time
---------------------------------------------------------------
P0      | 0            | 2          | 0            | 2
P2      | 1            | 5          | 3            | 8
P1      | 3            | 2          | 0            | 2
P3      | 9            | 3          | 0            | 3

Average Waiting Time: 0.75
Average Turnaround Time: 3.75
*/
