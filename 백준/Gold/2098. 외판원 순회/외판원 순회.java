import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] adjMatrix;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine().trim());
		
		adjMatrix = new int[n][n];
		dp = new int[n][1<<n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
		    Arrays.fill(dp[i], -1);
		}
		
		int ans = makeDP(0, 1); // (시작, 마스크)
		
		System.out.println(ans);
	}
	
	static int makeDP(int cur, int mask) {
		//  mask == 1111.. 모두 1이면 (모두 방문)
		if (mask == (1<<n) - 1) {
			if(adjMatrix[cur][0] == 0) return Integer.MAX_VALUE; // 갈 수 없는 경우
			return adjMatrix[cur][0];
		}
		
	    if (dp[cur][mask] != -1) return dp[cur][mask];  // 메모이제이션 체크
	    
	    int result = Integer.MAX_VALUE;
		
		for(int i = 0; i < n; i++) { // i는 다음 지점
			if((mask & (1<<i)) != 0) continue; // 이미 방문함
			if(adjMatrix[cur][i] == 0) continue;
			
			//미방문 도시 이동(재귀함수)
			int next = makeDP(i , mask | (1<<i));
			
			if(next != Integer.MAX_VALUE) {
				result = Math.min(result, adjMatrix[cur][i] + next); //최솟값 갱신
			}
		}
		dp[cur][mask] = result;
		return result;
	}
}
