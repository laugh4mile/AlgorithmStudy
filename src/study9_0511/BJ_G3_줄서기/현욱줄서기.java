import java.io.*;
import java.util.*;
public class 현욱줄서기 {
    static class P14864 {
        BufferedReader br;
        StringTokenizer st;
        StringBuilder sb;
        int N, M, student[];
        Set<Integer> card;
        P14864(){
            input();
            solve();
            System.out.println(sb);
        }
        void solve() {
            sb = new StringBuilder();
            if(check()) {
                for(int i=0; i<N; i++) 
                    sb.append(student[i]).append(" ");
            } else {
                sb.append(-1);
            }
        }
        boolean check() {
            card = new HashSet<>();
            for(int i=0; i<N; i++) {
                if(student[i]<1 || student[i]>N)
                    return false;
                if(card.contains(student[i]))
                    return false;
                card.add(student[i]);
            }
            return card.size()==N;
        }
        void input() {
            br = new BufferedReader(new InputStreamReader(System.in));
            try {
                st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
                student = new int[N];
                for(int i=0; i<N; i++)
                    student[i] = i+1;
                for(int i=0; i<M; i++) {
                    st = new StringTokenizer(br.readLine());
                    int s1 = Integer.parseInt(st.nextToken())-1;
                    int s2 = Integer.parseInt(st.nextToken())-1;
                    student[s1]++;
                    student[s2]--;
                }
            } catch (Exception e) {
            }
        }
    }
    public static void main(String[] v) {
        new P14864();
    }
}