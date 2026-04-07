import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static int[] coins, dp;
	static final int INF = 1_000_000_000;
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		coins = new int[n];
		dp = new int[k+1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		for(int i = 0; i < n; i++) {
			int coin = Integer.parseInt(br.readLine().trim());
			coins[i] = coin;
		}
		
		System.out.println(solution(0));
	}
	
	static int solution(int cur) {

		for(int j = 1; j <= k; j++) {
			for(int i  = 0; i < n; i++) {
				if(j - coins[i] >= 0) dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
			}
		}
		
		return dp[k] == INF ? -1 : dp[k];
	}
}
