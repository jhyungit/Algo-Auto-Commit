import java.io.*;
import java.util.*;

public class Main{
	static int M, N, K;
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		//------여기에 솔루션 코드를 작성하세요.------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 가로  M(Col), 세로 N(Row)
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			// 목표 마력석 개수
			K = Integer.parseInt(st.nextToken());
			
			// 마법석은 1, 아니면 0
			map = new int[N][M];
			visited = new boolean[N][M];

			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				map[r][c] = 1;
			}
			
			int count = 0;
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(map[r][c] == 1 && !visited[r][c]) {
						floodFill(r,c);
						count++;
					}
				}	
			}
			// 최소한의 채굴 골렘의 수
			System.out.println(count);
		}
	}
	
	static void floodFill(int r, int c) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {r,c});
		visited[r][c] = true;
		
		while(!dq.isEmpty()) {
			int[] q = dq.poll();
			int rr = q[0];
			int cc = q[1];
						
			for(int k = 0; k < 4; k++) {
				int nr = rr + dr[k];
				int nc = cc + dc[k];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc]==0) continue;
				
				visited[nr][nc] = true;
				dq.offer(new int[] {nr,nc});
			}
		}
	}
	
}