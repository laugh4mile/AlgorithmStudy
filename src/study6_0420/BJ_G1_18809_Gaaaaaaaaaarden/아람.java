package study6_0420.BJ_G1_18809_Gaaaaaaaaaarden;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아람 {

        static class Fluid {

        int r, c, color, s;

        public Fluid(int r, int c, int color, int s) {
            this.r = r;
            this.c = c;
            this.color = color;
            this.s = s;
        }

    }

    static int N, M, G, R, ans;
    static int[][] map, delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static List<int[]> list;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        list = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());

            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2)
                    list.add(new int[] {i, j});
            }
        }

        int[][] sel = new int[2][];

        sel[0] = new int[G];
        sel[1] = new int[R];

        combination(0, 0, sel, new boolean[list.size()], 0);
        System.out.println(ans);

    }

    private static void combination(int idx, int k, int[][] sel, boolean[] visited, int color) {
        if(color == 0 && k == G) {
            combination(0, 0, sel, visited, 1);
            return;
        }

        if(color == 1 && k == R) {
            Queue<Fluid> queue = new LinkedList<>();

            for(int i = 0; i < sel.length; i++) {
                for(int j = 0; j < sel[i].length; j++) {
                    int[] pos = list.get(sel[i][j]);
                    queue.offer(new Fluid(pos[0], pos[1], i, 0));
                }
            }

            bfs(queue);
            return;
        }

        for(int i = idx; i < visited.length; i++) {
            if(!visited[i]) {
                sel[color][k] = i;
                visited[i] = true;
                combination(i + 1, k + 1, sel, visited, color);
                visited[i] = false;
            }
        }
    }

    private static void bfs(Queue<Fluid> queue) {
        int cnt = 0;
        Fluid[][] garden = new Fluid[N][M];

        for(Fluid f : queue)
            garden[f.r][f.c] = f;

        while(!queue.isEmpty()) {
            Fluid f = queue.poll();

            for(int d = 0; d < delta.length; d++) {
                int nr = f.r + delta[d][0], nc = f.c + delta[d][1];

                if(check(nr, nc) && map[nr][nc] != 0) {
                    if(garden[nr][nc] == null) {
                        garden[nr][nc] = new Fluid(nr, nc, f.color, f.s + 1);
                        queue.offer(garden[nr][nc]);
                    }
                    else if(garden[nr][nc].color != 2 && garden[nr][nc].color != f.color && garden[nr][nc].s == f.s + 1) {
                        cnt++;
                        garden[nr][nc].color = 2;
                        queue.remove(garden[nr][nc]);
                    }
                }
            }
        }

        ans = Math.max(ans, cnt);
    }

    private static boolean check(int r, int c) {
        if(r >= 0 && r < N && c >= 0 && c < M)
            return true;

        return false;
    }   

}