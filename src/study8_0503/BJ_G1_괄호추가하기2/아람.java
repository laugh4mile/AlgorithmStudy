import java.io.*;
import java.util.*;

public class 아람 {
    
    static int N, ans = Integer.MIN_VALUE;
    static char[] arr;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        arr = in.readLine().toCharArray();
        
        select(0, new boolean[N / 2]);
        
        System.out.println(ans);
        
    }
    
    private static void select(int idx, boolean[] sel) {
        if(idx >= sel.length) {
            Queue<Character> exrs = new LinkedList<>();
            Stack<Character> temp = new Stack<>();
            
            for(int i = 0; i < N; i++) {
                if(arr[i] >= '0' && arr[i] <= '9')
                    exrs.offer(arr[i]);
                else if(sel[i / 2]) {
                    exrs.offer(arr[i + 1]);
                    exrs.offer(arr[i++]);
                }
                else {
                    temp.push(arr[i]);
                    continue;
                }
                
                if(i == N - 1 || (!sel[i / 2] && (arr[i + 1] == '+' || arr[i + 1] == '-')))
                        while(!temp.isEmpty())
                            exrs.offer(temp.pop());
            }
            
            Stack<Integer> stack = new Stack<>();
            
            while(!exrs.isEmpty()) {
                char c = exrs.poll();
                
                if(c >= '0' && c <= '9')
                    stack.push(c - '0');
                else {
                    int a = stack.pop(), b = stack.pop();
                    stack.push(calc(c, b, a));
                }
            }
            
            ans = Math.max(ans, stack.pop());
            return;
        }
        
        if(arr[idx * 2 + 1] != '*') {
            sel[idx] = true;
            select(idx + 2, sel);
            sel[idx] = false;
        }
        
        select(idx + 1, sel);
    }
    
    private static int calc(char op, int a, int b) {
        switch(op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            default:
                return a * b;
        }
    }
    
}
