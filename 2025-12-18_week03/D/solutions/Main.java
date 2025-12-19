import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        
        for (int t = 0; t < N; t++) {
            String s = br.readLine().trim();
            int n = s.length();
            int result = Integer.MAX_VALUE;
            
            for (int i = 0; i < n; i++) {
                boolean hasUpper = false;
                boolean hasLower = false;
                boolean hasDigit = false;
                
                for (int j = i; j < n; j++) {
                    char c = s.charAt(j);
                    if (Character.isUpperCase(c)) hasUpper = true;
                    if (Character.isLowerCase(c)) hasLower = true;
                    if (Character.isDigit(c)) hasDigit = true;
                    
                    int length = j - i + 1;
                    if (length >= 6 && hasUpper && hasLower && hasDigit) {
                        result = Math.min(result, length);
                        break;
                    }
                }
            }
            
            if (result == Integer.MAX_VALUE) {
                System.out.println(0);
            } else {
                System.out.println(result);
            }
        }
    }
}