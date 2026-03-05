import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static boolean[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 학생수N, 비교수M
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N+1][N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int small = Integer.parseInt(st.nextToken());
			int big = Integer.parseInt(st.nextToken());
			
			map[small][big] = true; // small < big
		}
		
		// 플로이드 워셜( i < k 작고 k < j이면 i < j)
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				if(!map[i][k]) continue; // 가지치기
				for(int j = 1; j <= N; j++) {
					if(map[k][j]) map[i][j] = true;
				}
			}
		}

		int ans = 0;
		for(int i = 1; i <= N; i++) {
			int cnt = 0;
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				if(map[i][j] || map[j][i]) { // i < j 거나 j < i 이면
					cnt++;
				}
			}
			if(cnt == N-1) ans++;
		}

		System.out.println(ans);
		
	}
}
