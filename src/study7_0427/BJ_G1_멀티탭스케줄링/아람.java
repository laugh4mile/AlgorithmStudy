import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 아람 {
    
    static int N, K, ans;
    static int[] multitap, products;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        multitap = new int[N];
        products = new int[K];
        
        st = new StringTokenizer(in.readLine());
        
        for(int i = 0; i < K; i++)
            products[i] = Integer.parseInt(st.nextToken());
        
        in();
        System.out.println(ans);
        
    }
    
    private static void in() {
        int idx = 0;
        
        first: for(int i = 0; i < K; i++) {
            for(int p : multitap)
                if(p == products[i])
                    continue first;
            
            if(idx == N)
                multitap[out(i + 1)] = products[i];
            else
                multitap[idx++] = products[i];
        }
    }
    
    private static int out(int p) {
        ans++;
        
        int idx = 0, order = 0;
        
        first: for(int i = 0; i < N; i++) {
            for(int j = p; j < K; j++) {
                if(products[j] == multitap[i]) {
                    if(j > order) {
                        idx = i;
                        order = j;
                    }
                    
                    continue first;
                }
            }
            
            return i;
        }
        
        return idx;
    }
    
}
