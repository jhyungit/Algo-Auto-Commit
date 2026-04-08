import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine().trim());
		
		int[] r = new int[n];
		int[] c = new int[n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			r[i] = Integer.parseInt(st.nextToken());
			c[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[n][n];
		
		// len == 1(행렬 2개)
		for(int len = 1; len < n; len++) {
			for(int i = 0; i + len < n; i++) {
				int j = i + len;
				dp[i][j] = Integer.MAX_VALUE;
				for(int k = i; k < j; k++) {
					// k로 구간을 나누면 r[i] * c[k] * c[j] 발생(두 행렬 곱하는 비용)
					int cost = dp[i][k] + dp[k + 1][j] + r[i] * c[k] * c[j];
					dp[i][j] = Math.min(dp[i][j], cost);
				}
			}
		}
		
		System.out.println(dp[0][n-1]);
	}
}
