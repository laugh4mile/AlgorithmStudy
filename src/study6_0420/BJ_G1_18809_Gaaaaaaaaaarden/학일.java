package study6_0420.BJ_G1_18809_Gaaaaaaaaaarden;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 학일 {
	static int row, col, G, R, ans = 0;
	static int GREEN = 0, RED = 1, FLOWER = 2;
	static int[][] board;
	static Culture[][] cBoard;
	static ArrayList<int[]> spray;
	static boolean[] used;
	static Stack<Culture> que;
	static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception{
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken()); col = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken()); R = Integer.parseInt(st.nextToken());

		board = new int[row][col];
		cBoard = new Culture[row][col];
		spray = new ArrayList<>();
		que = new Stack<>();
		
		for (int r=0; r<row; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<col; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if (board[r][c] == 2) spray.add(new int[]{r, c});
			}
		}
		used = new boolean[spray.size()];
		makeAns(G, 0, GREEN);
		System.out.print(ans);
	}

	public static void makeAns(int cnt, int idx, int color) {

		if (cnt == 0) {
			if (color == GREEN) makeAns(R, 0, RED);
			else{
				Deque<Culture> tmpQ = new ArrayDeque<>(que);;
				Culture[][] tmpBoard = new Culture[row][col];
				for (int r=0; r<row; r++)
					for (int c=0; c<col; c++) {
						tmpBoard[r][c] = cBoard[r][c];
					}
				ans = Math.max(ans, bfs(tmpQ, tmpBoard));
			}
			return;
		}

		if (idx == spray.size()) return;
		
		if (!used[idx]) {
			int[] rc = spray.get(idx);
			Culture culture = new Culture(rc[0], rc[1], 0, color);
			que.push(culture); used[idx] = true; cBoard[rc[0]][rc[1]] = culture;
			makeAns(cnt-1, idx+1, color);
			que.pop(); used[idx] = false; cBoard[rc[0]][rc[1]] = null;
		}

		makeAns(cnt, idx+1, color);
	}

	public static int bfs(Queue<Culture> q, Culture[][] b) {

		int flower = 0;

		while (!q.isEmpty()) {
			Culture now = q.poll();
			if (now.color == FLOWER) continue;
			
			for (int i=0; i<4; i++) {
				int nr = now.r+dr[i], nc = now.c+dc[i];
				if (0 <= nr && nr < row && 0 <= nc && nc < col && board[nr][nc] > 0) {
					if (b[nr][nc] == null) {
						Culture next = new Culture(nr, nc, now.t+1, now.color);
						b[nr][nc] = next;
						q.add(next);
					} else {
						if (b[nr][nc].t == now.t+1 && b[nr][nc].color != now.color && b[nr][nc].color != FLOWER) {
							b[nr][nc].color = FLOWER;
							flower ++;
						}
					}
				}
			}
		}
		return flower;
	}

	public static class Culture {
		int r, c, t, color;
		Culture(int r, int c, int t, int color){
			this.r = r; this.c = c; this.t = t; this.color = color;
		}
	}
}
