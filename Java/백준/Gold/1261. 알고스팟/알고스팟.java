import java.io.*;
import java.util.*;

public class Main {
	static int M,N;
	static int[][] map;
	static int[][] dist;
	static int[][] d = {{0,1},{0,-1},{1,0},{-1,0}};
	
	static List<Integer> ans = new ArrayList<>();

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// NxM크기, 빈방 or 벽, 벽을 부수는 최소 횟수
		M = Integer.parseInt(st.nextToken()); // col 가로
		N = Integer.parseInt(st.nextToken()); // row 세로
		
		map = new int[N][M];
		dist = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < input.length(); j++) {
				int num = input.charAt(j) - '0';
				map[i][j] = num;
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		bfs01(0,0);
		System.out.println(dist[N-1][M-1]);
	}
	
	static void bfs01(int r, int c) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.addFirst(new int[] {r, c}); //좌표와 벽 부순 횟수
		dist[r][c] = 0;
		
		while(!dq.isEmpty()) {
			int[] q = dq.pollFirst();
			int rr = q[0];
			int cc = q[1];
						
			for(int k = 0; k < 4; k++) {
				int nr = rr + d[k][0];
				int nc = cc + d[k][1];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				int nextCost = dist[rr][cc] + map[nr][nc];
				
				if(dist[nr][nc] > nextCost) {
					dist[nr][nc] = nextCost;
					
					if(map[nr][nc] == 0) {
						dq.addFirst(new int[] {nr,nc});
					}else {
						dq.addLast(new int[] {nr,nc});
					}	
				}				
			}
		}
	}
	
}

