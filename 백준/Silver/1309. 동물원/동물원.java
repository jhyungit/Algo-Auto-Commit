import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] dp;
	static final int Mod = 9901;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine().trim());
		dp = new int[n+1][3];
		
		System.out.println(solution(n));
	}
	
	static int solution(int n) {
		
		// 세로 1칸이면 0, L, R 각각 1개씩
		Arrays.fill(dp[1], 1);
		
		// L, R을 각각 1,2로 하면
		for(int i = 2; i<= n; i++) {
			dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % Mod;
			// L에 놓는 경우  = 이전이 빈 거 + 이전이 R
			dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % Mod;
			// R에 놓는 경우 = 이전이 빈 거 + 이전이 L
			dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % Mod;
		}
		return Arrays.stream(dp[n]).sum() % Mod;
	}
}
