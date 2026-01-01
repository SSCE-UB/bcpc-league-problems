import java.io.*;
import java.util.*;

public class solution {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int s = fs.nextInt();

        ArrayList<String> order = new ArrayList<>(n + s + 5);
        HashMap<String, Integer> pos = new HashMap<>(n + s + 5);

        for (int i = 0; i < n; i++) {
            String name = fs.next();
            order.add(name);
            pos.put(name, i);
        }

        for (int q = 0; q < s; q++) {
            String op = fs.next();

            if (op.equals("insert")) {
                String name = fs.next();
                int p = fs.nextInt();
                order.add(p, name);
                for (int i = p; i < order.size(); i++) pos.put(order.get(i), i);
            } else if (op.equals("depart")) {
                String name = fs.next();
                int i = pos.get(name);
                pos.remove(name);
                order.remove(i);
                for (int j = i; j < order.size(); j++) pos.put(order.get(j), j);
            } else {
                String name = fs.next();
                int d = fs.nextInt();
                int i = pos.get(name);
                String bird = order.remove(i);
                int ins = i + d;
                order.add(ins, bird);
                int start = Math.min(i, ins);
                for (int j = start; j < order.size(); j++) pos.put(order.get(j), j);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(order.get(i));
        }
        System.out.print(sb.toString());
        System.out.print('\n');
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

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

        String next() throws IOException {
            int c;
            do { c = read(); } while (c <= ' ' && c != -1);
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
