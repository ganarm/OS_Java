import java.util.Scanner;
import java.lang.Math;

public class SSTF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] q = new int[100];
        int n, seek = 0, head, count = 0;
        
        System.out.print("Enter the Number of Requests: ");
        n = sc.nextInt();
        
        System.out.print("Enter the Request Sequence: ");
        for (int i = 0; i < n; i++) {
            q[i] = sc.nextInt();
        }
        
        System.out.print("Enter Initial Head Position: ");
        head = sc.nextInt();
        
        System.out.println("Sequence of Head Movement:");
        
        while (count != n) {
            int min = 1000, diff, index = -1;
            for (int i = 0; i < n; i++) {
                diff = Math.abs(q[i] - head);
                if (min >= diff) {
                    min = diff;
                    index = i;
                }
            }
            seek += min;
            head = q[index];
            System.out.print(q[index] + " ");
            q[index] = 1000; // Mark this request as completed
            count++;
        }
        
        System.out.println("\nTotal head movement is: " + seek);
        sc.close();
    }
}
