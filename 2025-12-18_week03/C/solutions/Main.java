import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        
        int[] positions = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(positions);
        
        int count = 0;
        int lastKept = positions[0];
        
        for (int i = 1; i < n; i++) {
            if (positions[i] - lastKept < L) {
                count++;
            } else {
                lastKept = positions[i];
            }
        }
        
        System.out.println(count);
    }
}