import java.io.*;
import java.util.*;

public class solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int[] phi = new int[N + 1];
        for (int i = 1; i <= N; i++)
            phi[i] = i;

        for (int i = 2; i <= N; i++) {
            if (phi[i] == i) { // i عدد اول است
                for (int j = i; j <= N; j += i) {
                    phi[j] -= phi[j] / i;
                }
            }
        }

        long totalPhi = 0;
        long oddPhi = 0;

        for (int i = 1; i <= N; i++) {
            totalPhi += phi[i];
            if ((i & 1) == 1) oddPhi += phi[i];
        }

        long answer = 2 * totalPhi - 1 + oddPhi;
        System.out.println(answer);
    }
}
