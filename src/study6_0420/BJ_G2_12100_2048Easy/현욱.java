package study6_0420.BJ_G2_12100_2048Easy;

import java.io.*;
import java.util.*;
public class 현욱 {
    static class P12100 {
        BufferedReader br;
        StringTokenizer st;
        int N, board[][], answer;
        P12100() {
            input();
            go(0);
            System.out.println(answer);
        }
        void go(int count) {
            if (count == 5) {
                for (int i = 0; i < N; i++)
                    for (int j = 0; j < N; j++)
                        answer = Math.max(answer, board[i][j]);
                return;
            }
            for(int d=0; d<4; d++) {
                int[][] temp = copy(board);
                board = down(board);
                go(count + 1);
                board = copy(temp);
                board = rotate(board);
            }
        }
        int[][] copy(int[][] map) {
            int[][] temp = new int[N][N];
            for(int i=0; i<N; i++)
                temp[i] = map[i].clone();
            return temp;
        }
        int[][] down(int[][] map) {
            int[][] temp = new int[N][N];
            Queue<Integer> q = new ArrayDeque<>();
            for(int i=0; i<N; i++) {
                for(int j=N-1; j>=0; j--)
                    if(map[j][i]!=0) 
                        q.add(map[j][i]);
                int idx = N-1;
                while(!q.isEmpty()) {
                    int number = q.poll();
                    temp[idx][i] = number;
                    if(!q.isEmpty() && q.peek()==number)
                        temp[idx][i] += q.poll();
                    idx--;
                }
            }
            return temp;
        }
        int[][] rotate(int[][] map) {
            int[][] temp = new int[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    temp[N - j - 1][i] = map[i][j];
            return temp;
        }
        void input() {
            br = new BufferedReader(new InputStreamReader(System.in));
            try {
                N = Integer.parseInt(br.readLine());
                board = new int[N][N];
                for (int i = 0; i < N; i++) {
                    st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < N; j++)
                        board[i][j] = Integer.parseInt(st.nextToken());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new P12100();
    }
}