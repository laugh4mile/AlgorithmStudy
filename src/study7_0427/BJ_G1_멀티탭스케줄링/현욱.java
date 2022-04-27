package 백준_멀티탭스케쥴링;
import java.io.*;
import java.util.*;
public class 현욱 {
	static class P1700 {
		BufferedReader br;
		StringTokenizer st;
		int N, K; 
		int e[];
		P1700() {
			input();
			solve();
		}
		void solve() {
			Set<Integer> multi = new LinkedHashSet<>();
			int answer = 0;
			for(int i=0; i<K; i++) {
				if(!multi.contains(e[i])) {
					if(multi.size()==N) { // 멀티탭이 꽉 차있다.
						Set<Integer> t = new LinkedHashSet<>(multi);
						for(int j=i+1; j<K; j++) {
							if(t.size()==1)
								break;
                            if(t.contains(e[j])) // 멀티탭에 있는거야?
                                t.remove(e[j]); // 빼는 목록에서 제외
						}
						multi.remove(t.toArray()[0]); // 빼는 목록에서 가장 첫번째 멀티탭에서 제거해준다.
						multi.add(e[i]); // 그 다음 연결
						answer++; // 정답 +1
					} else {
						multi.add(e[i]); // 현재 더 꽂을 수 있다.
					}
				}
			}
			System.out.println(answer); // 정답 출력
		}
		void input() {
			br = new BufferedReader(new InputStreamReader(System.in));
			try {
                
				st = new StringTokenizer(br.readLine());
				N = Integer.parseInt(st.nextToken());
				K = Integer.parseInt(st.nextToken());
				e = new int[K];
				st = new StringTokenizer(br.readLine());
				for(int i=0; i<K; i++)
					e[i] = Integer.parseInt(st.nextToken());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new P1700();
	}
} 