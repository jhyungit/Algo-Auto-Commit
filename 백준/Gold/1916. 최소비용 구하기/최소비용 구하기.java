import java.io.*;
import java.util.*;

public class Main {
	static int N, M; // 도시 수, 버스 개수
	
	static List<int[]>[] graph;
	static int[] dist;
	static final int INF = 1_000_000_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		
		//그래프 초기화
		graph = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 거리 초기화
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		//버스 정보
		StringTokenizer st;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[s].add(new int[] {e, cost});
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());
		
		dijkstra(start);

		System.out.println(dist[goal]);
		
	}
	
	static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]);
		
		pq.offer(new int[] {start,0});
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int cost = cur[1];
			
			if(cost > dist[node]) continue; // 가지치기
			
			for(int[] next : graph[node]) {
				int nextNode = next[0];
				int nextCost = next[1];
				
				if(cost + nextCost < dist[nextNode]) {
					dist[nextNode] = cost + nextCost;
					pq.offer(new int[] {nextNode, dist[nextNode]});
				}
			}
 		}
	}

}
