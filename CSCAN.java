import java.util.Scanner;

public class CSCAN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nC-SCAN DISK SCHEDULING ALGORITHM:\n");

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
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (q[j] > q[j + 1]) {
                    int temp = q[j];
                    q[j] = q[j + 1];
                    q[j + 1] = temp;
                }
            }
        }

        int seek = 0;
        int index = 0;

        // Find the first request greater than the head position
        for (int i = 0; i < n; i++) {
            if (head < q[i]) {
                index = i;
                break;
            }
        }

        System.out.println("\nSequence of head movement:");
        if (move == 1) {
            System.out.print(head + " ");
            for (int i = index; i < n; i++) {
                seek += Math.abs(q[i] - head);
                head = q[i];
                System.out.print(q[i] + " ");
            }
            seek += Math.abs(max - q[n - 1] - 1);
            seek += Math.abs(max - 1);
            System.out.print((max - 1) + " ");
            head = 0;
            System.out.print(head + " ");
            for (int i = 0; i < index; i++) {
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
            seek += Math.abs(max - 1);
            head = max - 1;
            System.out.print(head + " ");
            for (int i = n - 1; i >= index; i--) {
                seek += Math.abs(q[i] - head);
                head = q[i];
                System.out.print(q[i] + " ");
            }
        }

        System.out.println("\n\nTotal Head Movements are " + seek);
    }
}
