import java.io.*;
import java.util.*;

public class solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashMap<Long, Long> cnt = new HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(st.nextToken());

            for (int p = 1; p <= 31; p++) {
                long power = 1L << p;
                long need = power - x;
                ans += cnt.getOrDefault(need, 0L);
            }

            cnt.put(x, cnt.getOrDefault(x, 0L) + 1);
        }

        System.out.println(ans);
    }
}
