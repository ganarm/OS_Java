import java.util.Scanner;

class Process {
    int at, bt, wt, tat, pid, pt, bt1;
}

public class PreemptivePriority {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter The No of Process: ");
        int n = sc.nextInt();

        Process[] p = new Process[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = new Process();
            System.out.print("\nEnter the Arrival Time for Process " + i + ": ");
            p[i].at = sc.nextInt();

            System.out.print("\nEnter the Burst Time for Process " + i + ": ");
            p[i].bt = sc.nextInt();

            System.out.print("\nEnter the Priority for Process " + i + ": ");
            p[i].pt = sc.nextInt();

            p[i].pid = i;
            p[i].bt1 = p[i].bt;  // Store the original burst time for later use
            temp[i] = p[i].bt;    // Store burst time in temp[] for later calculation
        }

        int t, count = 0, shopt_p;
        int avgtat = 0, avgwt = 0;
        int[] ganttChart = new int[100];
        int ganttIndex = 0;

        System.out.println("\n\n\nGantt Chart:");
        System.out.println("-------------------------------------------------");

        // Execute the scheduling algorithm
        for (t = 0; count != n; t++) {
            shopt_p = -1;

            // Find the process with the highest priority that is ready to execute
            for (int i = 0; i < n; i++) {
                if (p[i].at <= t && p[i].bt > 0) {
                    if (shopt_p == -1 || p[i].pt < p[shopt_p].pt) {
                        shopt_p = i;
                    }
                }
            }

            // Decrease the burst time for the selected process
            if (shopt_p != -1) {
                p[shopt_p].bt--;
                ganttChart[ganttIndex++] = shopt_p; // Store the process in Gantt chart

                // Print the process execution in the Gantt chart
                System.out.print(" P" + shopt_p + " |");

                if (p[shopt_p].bt == 0) {
                    count++;
                    p[shopt_p].wt = t + 1 - p[shopt_p].at - temp[shopt_p];  // Calculate waiting time
                    p[shopt_p].tat = t + 1 - p[shopt_p].at;                 // Calculate turnaround time
                    avgwt += p[shopt_p].wt;
                    avgtat += p[shopt_p].tat;
                }
            }
        }

        System.out.println("|");
        System.out.println("-------------------------------------------------");

        // Print the results in a table format
        System.out.println("\nProcess | ArrivalTime | BurstTime | WaitingTime | TurnAroundTime ");
        System.out.println("--------------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("   P%d   |%7d      |%7d    |%7d      | %7d \n", i, p[i].at, p[i].bt1, p[i].wt, p[i].tat);
        }

        // Print average waiting time and average turnaround time
        System.out.println("\n\n\n\nAverage Waiting Time: " + avgwt / n);
        System.out.println("Average Turnaround Time: " + avgtat / n);

        // Print the Gantt chart process order
        System.out.println("\nGantt Chart Execution Order: ");
        for (int i = 0; i < ganttIndex; i++) {
            System.out.print("P" + ganttChart[i] + " ");
        }
        System.out.println();
        
        sc.close();
    }
}
