import java.util.Arrays;

public class MyAverage {
    public static void main(String[] args) {
        // StdOut.printf("hello world!\n", Math.PI);
        System.out.println("hello world!");
        double sum = 0.0;
        int cnt = 0;
        while (!StdIn.isEmpty()) {
            double d = StdIn.readDouble();
            sum += d;
            ++cnt;
        }
        double avg = sum / cnt;
        StdOut.printf("Average is %.5f\n", avg);
    }
}
