package study6_0420.BJ_G2_12100_2048Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 아람 {

    static class Number implements Comparable<Number> {

        int r, c, num;

        public Number(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + r;
            result = prime * result + c;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Number other = (Number) obj;
            if (r != other.r)
                return false;
            if (c != other.c)
                return false;
            return true;
        }

        @Override
        public int compareTo(Number o) {
            if(this.r == o.r)
                return Integer.compare(this.c, o.c);

            return Integer.compare(this.r, o.r);
        }

    }

    static int N, ans;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(in.readLine());

        int[][] map = new int[N][N];
        LinkedList<Number> list = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] > 0)
                    list.offer(new Number(i, j, map[i][j]));
            }
        }

        dfs(0, map, list);
        System.out.println(ans);

    }

    private static void dfs(int turn, int[][] map, LinkedList<Number> list) {
        if(turn == 5) {
            Collections.sort(list, (o1, o2) -> Integer.compare(o2.num, o1.num));
            ans = Math.max(ans, list.getFirst().num);
            return;
        }

        turn++;
        Collections.sort(list);

        for(int d = 0; d < 4; d++)
            move(turn, map, list, d);
    }

    private static void move(int turn, int[][] map, LinkedList<Number> list, int d) {
        int[][] newMap = new int[N][N];
        int[] united = new int[N];
        LinkedList<Number> newList = new LinkedList<>();

        if(d == 0) {
            for(int i = 0; i < list.size(); i++) {
                Number n = list.get(i);

                if(newMap[united[n.c]][n.c] == 0)
                    newList.offer(new Number(united[n.c], n.c, newMap[united[n.c]][n.c] = n.num));
                else if(newMap[united[n.c]][n.c] == n.num) {
                    Number newNum = new Number(united[n.c], n.c, newMap[united[n.c]++][n.c] *= 2);
                    newList.remove(newNum);
                    newList.offer(newNum);
                }
                else
                     newList.offer(new Number(++united[n.c], n.c, newMap[united[n.c]][n.c] = n.num));
            }
        }
        else if(d == 1) {
            Arrays.fill(united, N - 1);

            for(int i = list.size() - 1; i >= 0; i--) {
                Number n = list.get(i);
                
                if(newMap[united[n.c]][n.c] == 0)
                    newList.offer(new Number(united[n.c], n.c, newMap[united[n.c]][n.c] = n.num));
                else if(newMap[united[n.c]][n.c] == n.num) {
                    Number newNum = new Number(united[n.c], n.c, newMap[united[n.c]--][n.c] *= 2);
                    newList.remove(newNum);
                    newList.offer(newNum);
                }
                else
                     newList.offer(new Number(--united[n.c], n.c, newMap[united[n.c]][n.c] = n.num));
            }
        }
        else if(d == 2) {
            for(int i = 0; i < list.size(); i++) {
                Number n = list.get(i);
                
                if(newMap[n.r][united[n.r]] == 0)
                    newList.offer(new Number(n.r, united[n.r], newMap[n.r][united[n.r]] = n.num));
                else if(newMap[n.r][united[n.r]] == n.num) {
                    Number newNum = new Number(n.r, united[n.r], newMap[n.r][united[n.r]++] *= 2);
                    newList.remove(newNum);
                    newList.offer(newNum);
                }
                else
                     newList.offer(new Number(n.r, ++united[n.r], newMap[n.r][united[n.r]] = n.num));
            }
        }
        else {
            Arrays.fill(united, N - 1);
            
            for(int i = list.size() - 1; i >= 0; i--) {
                Number n = list.get(i);
                
                if(newMap[n.r][united[n.r]] == 0)
                    newList.offer(new Number(n.r, united[n.r], newMap[n.r][united[n.r]] = n.num));
                else if(newMap[n.r][united[n.r]] == n.num) {
                    Number newNum = new Number(n.r, united[n.r], newMap[n.r][united[n.r]--] *= 2);
                    newList.remove(newNum);
                    newList.offer(newNum);
                }
                else
                     newList.offer(new Number(n.r, --united[n.r], newMap[n.r][united[n.r]] = n.num));
            }
        }

        dfs(turn, newMap, newList);
    }

}