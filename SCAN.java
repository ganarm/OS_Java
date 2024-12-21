import java.util.Scanner;
import java.util.Arrays;

public class SCAN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSCAN DISK SCHEDULING ALGORITHM:\n");

        System.out.print("Enter Total Disk Size: ");
        int max = scanner.nextInt();

        System.out.print("Enter the Number of Requests: ");
        int n = scanner.nextInt();

        int[] q = new int[n];

        System.out.print("Enter the Request Sequence: ");
        for (int i = 0; i < n; i++) {
            q[i] = scanner.nextInt();
        }

        System.out.print("Enter Initial Head Position: ");
        int head = scanner.nextInt();

        System.out.print("Enter the Head Movement direction (for high 1 and for low 0): ");
        int move = scanner.nextInt();

        // Sort the request array
        Arrays.sort(q);

        int seek = 0;
        int index = 0;

        // Find the first request greater than the head position
        for (int i = 0; i < n; i++) {
            if (head < q[i]) {
                index = i;
                break;
            }
        }

        System.out.println("\nSequence of Head Movement:");
        if (move == 1) {
            System.out.print(head + " ");
            for (int i = index; i < n; i++) {
                seek += Math.abs(q[i] - head);
                head = q[i];
                System.out.print(q[i] + " ");
            }
            seek += Math.abs(max - q[n - 1] - 1);
            head = max - 1;
            System.out.print(head + " ");
            for (int i = index - 1; i >= 0; i--) {
                seek += Math.abs(q[i] - head);
                head = q[i];
                System.out.print(q[i] + " ");
            }
        } else {
            System.out.print(head + " ");
            for (int i = index - 1; i >= 0; i--) {
                seek += Math.abs(q[i] - head);
                head = q[i];
                System.out.print(q[i] + " ");
            }
            seek += Math.abs(q[0] - 0);
            head = 0;
            System.out.print(head + " ");
            for (int i = index; i < n; i++) {
                seek += Math.abs(q[i] - head);
                head = q[i];
                System.out.print(q[i] + " ");
            }
        }

        System.out.println("\n\nTotal Head Movements are " + seek + ".");
    }
}
