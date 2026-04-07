import java.io.*;
import java.util.*;

public class Main {
	static long[][][] dp;
	static int[][] map;
	static int n;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine().trim());
		map = new int[n][n];
		// dp[s][r][c] 이전상태, 행, 열
		dp = new long[3][n][n]; 
		for(int r = 0; r < n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c = 0; c < n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(solution());
	}
	
	static long solution() {
		// 초기 값: 파이프 끝이 (가로, 0, 1)
		dp[0][0][1] = 1;
		
		// 0(가로), 1(세로), 2(대각)
		for(int r = 0; r < n; r++) {
			for(int c = 2; c < n; c++) {
				if(map[r][c] == 1) continue; // 벽일 때
				
				// 가로로 도착: 가로 or 대각선으로 온 경우
				dp[0][r][c] = dp[0][r][c-1] + dp[2][r][c-1];
				
				// 세로로 도착: 세로  or 대각선으로 온 경우
				if(r > 0) {
					dp[1][r][c] = dp[1][r-1][c] + dp[2][r-1][c];
				}
				
				// 대각선으로 도착: 가로, 세로, 대각선 다 상관없음
				if(r > 0 && map[r-1][c] != 1 && map[r][c-1] != 1) {
					dp[2][r][c] = dp[0][r-1][c-1] + dp[1][r-1][c-1] + dp[2][r-1][c-1];
				}
				
			}
		}
		long ans = dp[0][n-1][n-1] + dp[1][n-1][n-1] + dp[2][n-1][n-1];
		return ans;
	}
}
