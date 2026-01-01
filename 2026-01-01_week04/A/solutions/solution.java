import java.io.*;
import java.util.*;

public class solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long z = Long.parseLong(st.nextToken());

            boolean ok = ((x & y & ~z) | (x & z & ~y) | (y & z & ~x)) == 0;
            sb.append(ok ? "YES" : "NO").append('\n');
        }

        System.out.print(sb.toString());
    }
}
