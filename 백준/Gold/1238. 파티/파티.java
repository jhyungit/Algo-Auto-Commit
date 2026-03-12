import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, X; // 학생수, 도로수, 파티장소
	static List<int[]>[] graph;
	static int[] dist;
	static final int INF = 1_000_000_000;
	
	static int[] student;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		//학생 왕복 거리 초기화
		student = new int[N+1];
		
		//초기화
		graph = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 도로 정보 저장
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[u].add(new int[]{v,w});
		}
		
		//다익스트라
		// 파티장소 -> 모든 마을까지 최단거리 구하기
		dijkstra(X);
		
		// 집으로 돌아오는 최단거리
		comeBackDist();
		
		
		// 학생 각자 위치에서 파티장소까지 최단거리 구하기
		for(int village = 1; village <= N; village++) {
			dijkstra(village);
			student[village] += dist[X];
		}
		
		int ans = Integer.MIN_VALUE;
		for(int i = 1; i <= N; i++) {
			ans = Math.max(ans, student[i]);
		}
		
		System.out.println(ans);
	}
	
	static void dijkstra(int start) {
		// 거리 초기화
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
		pq.offer(new int[] {start, 0}); // 시작좌표, 거리
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int cost = cur[1];
			
			if(cost > dist[node]) continue;
			
			for(int[] next: graph[node]) {
				int nextNode = next[0];
				int nextCost = next[1];
				
				if(nextCost+cost < dist[nextNode]) {
					dist[nextNode] = nextCost+cost;
					pq.offer(new int[] {nextNode, dist[nextNode]});
				}
			}
		}
	}
	
	static void comeBackDist() {
		for(int i = 1; i <= N; i++) {
			student[i] = dist[i];
		}
	}

}
