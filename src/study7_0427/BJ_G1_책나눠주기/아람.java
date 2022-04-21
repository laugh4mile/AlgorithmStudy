import java.io.*;
import java.util.*;

public class Main {
    
    static int T, N, M;
    static int[] owners;
    static int[][] students;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        
        for(int t = 1; t <= T; t++) {
            int cnt = 0;
            st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            owners = new int[N + 1];
            students = new int[M + 1][2];
            
            for(int i = 1; i <= M; i++) {
                st = new StringTokenizer(in.readLine());
                
                for(int j = 0; j < students[i].length; j++)
                    students[i][j] = Integer.parseInt(st.nextToken());
                
                if(matching(i, new boolean[N + 1]))
                    cnt++;
            }
            
            sb.append(cnt).append("\n");
        }
        
        System.out.println(sb);
        
    }
    
    private static boolean matching(int idx, boolean[] visited) {
        for(int i = students[idx][0]; i <= students[idx][1]; i++) {
            if(!visited[i]) {
                visited[i] = true;
                
                if(owners[i] == 0 || matching(owners[i], visited)) {
                    owners[i] = idx;
                    return true;
                }
            }
        }
        
        return false;
    }
    
}
