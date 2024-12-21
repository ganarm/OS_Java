import java.util.Scanner;

public class RoundRobinScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int NOP, quant, sum = 0, count = 0, y, wt = 0, tat = 0;
        float avg_wt, avg_tat;
        int[] at = new int[10];
        int[] bt = new int[10];
        int[] temp = new int[10];
        int[] ganttChart = new int[100];
        int ganttIndex = 0;
        int currentTime = 0;

        System.out.print("Total number of processes in the system: ");
        NOP = scanner.nextInt();
        y = NOP;

        for (int i = 0; i < NOP; i++) {
            System.out.println("\nEnter the Arrival and Burst time of Process[" + (i + 1) + "]");
            System.out.print("Arrival time is: ");
            at[i] = scanner.nextInt();
            System.out.print("Burst time is: ");
            bt[i] = scanner.nextInt();
            temp[i] = bt[i];
        }

        System.out.print("Enter the Time Quantum for the process: ");
        quant = scanner.nextInt();

        System.out.println("\nProcess No\tBurst Time\tTAT\tWaiting Time");

        int i = 0;
        while (y != 0) {
            if (temp[i] <= quant && temp[i] > 0) {
                sum = sum + temp[i];
                temp[i] = 0;
                count = 1;

                for (int j = currentTime; j < sum; j++) {
                    ganttChart[ganttIndex++] = i + 1;
                }

                currentTime = sum;
            } else if (temp[i] > 0) {
                temp[i] = temp[i] - quant;
                sum = sum + quant;

                for (int j = currentTime; j < sum; j++) {
                    ganttChart[ganttIndex++] = i + 1;
                }

                currentTime = sum;
            }

            if (temp[i] == 0 && count == 1) {
                y--;
                System.out.println("\nProcess No[" + (i + 1) + "]\t\t" + bt[i] + "\t\t\t" + (sum - at[i]) + "\t\t\t" + (sum - at[i] - bt[i]));
                wt = wt + sum - at[i] - bt[i];
                tat = tat + sum - at[i];
                count = 0;
            }

            if (i == NOP - 1) {
                i = 0;
            } else if (at[i + 1] <= sum) {
                i++;
            } else {
                i = 0;
            }
        }

        avg_wt = (float) wt / NOP;
        avg_tat = (float) tat / NOP;
        System.out.println("\nGantt Chart Execution Order: ");
        System.out.println("-------------------------------------------------");
        
        for (int k = 0; k < ganttIndex; k++) {
            System.out.print("P" + ganttChart[k] + " ");
        }

        System.out.println("\n-------------------------------------------------");

        System.out.println("\nAverage Turn Around Time: \t" + avg_tat);
        System.out.println("Average Waiting Time: \t" + avg_wt);
    }
}
