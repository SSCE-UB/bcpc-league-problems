import java.io.*;
import java.util.*;

public class solution {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int q = fs.nextInt();

        long[] lst = new long[n];
        for (int i = 0; i < n; i++) {
            lst[i] = fs.nextLong();
        }

        StringBuilder output = new StringBuilder();

        while (q-- > 0) {
            int op = fs.nextInt();

            if (op == 1) {
                int l = fs.nextInt();
                int r = fs.nextInt();
                long x = fs.nextLong();
                for (int i = l - 1; i < r; i++) {
                    lst[i] += x;
                }
            }
            else if (op == 2) {
                int l = fs.nextInt();
                int r = fs.nextInt();
                long x = fs.nextLong();
                for (int i = l - 1; i < r; i++) {
                    lst[i] -= x;
                }
            }
            else if (op == 3) {
                int l = fs.nextInt();
                int r = fs.nextInt();
                long mx = lst[l - 1];
                long mn = lst[l - 1];
                for (int i = l - 1; i < r; i++) {
                    mx = Math.max(mx, lst[i]);
                    mn = Math.min(mn, lst[i]);
                }
                output.append(mx - mn).append('\n');
            }
        }

        System.out.print(output.toString());
    }

    // Fast Input
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            while ((c = read()) <= ' ' && c != -1) ;
            boolean neg = false;
            if (c == '-') {
                neg = true;
                c = read();
            }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return neg ? -val : val;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}
