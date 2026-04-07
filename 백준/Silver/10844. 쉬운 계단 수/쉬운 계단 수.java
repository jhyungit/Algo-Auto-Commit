import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static long[][] dp;
	static final int INF = -1_000_000_000;
	static final long Mod = 1_000_000_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		
		// 길이, 마지막 숫자
		dp = new long[n+1][10];
		
		
		System.out.println(solution(n));
	}
	
	static long solution(int n) {
		Arrays.fill(dp[0], 0);
		Arrays.fill(dp[1], 1);
		dp[1][0] = 0;
		
		// i = 길이, j = 마지막 숫자
		// dp[i][j]
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j < 10; j++) {
				if(j == 0) dp[i][j] = dp[i-1][1] % Mod;
				else if(j == 9) dp[i][j] = dp[i-1][8] % Mod;
				else dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % Mod;
			}
		}
		
		long cnt = 0;
		for(int i = 0; i < 10; i++) {
			cnt += dp[n][i] % Mod;
		}
		return cnt % Mod;
	}
}
