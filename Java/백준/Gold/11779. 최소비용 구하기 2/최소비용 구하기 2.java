import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m; 
	static final int INF = 1_000_000_000;
	
	static int start, end;
	
	static List<int[]>[] graph;
	static int[] dist;
	
	static int parent[];
	static List<Integer> path = new ArrayList<>();

	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 도시, 버스
		n = Integer.parseInt(br.readLine().trim());
		m = Integer.parseInt(br.readLine().trim());
		
		//그래프 초기화
		graph = new ArrayList[n+1];
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 시작, 도착, 거리
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[u].add(new int[] {v,w});
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());	
		
		parent = new int[n+1];
		
		
		//출력
		// 출발->도착 최소 비용
		dijkstra(start);		
		System.out.println(dist[end]);
		
		// 최소비용 경로 포함 도시 개수(출발->도착 도시 포함)
		cityCnt(end);
		System.out.println(path.size());
		
		// 경로 출력(여러 가지면 아무거나 하나)
		StringBuilder sb = new StringBuilder();
		Collections.reverse(path);
		for(int node : path) {
			sb.append(node).append(' ');
		}
		
		System.out.println(sb);
	}
	
	static void dijkstra(int start) {
		dist = new int[n+1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]);
		
		dist[start] = 0;
		pq.add(new int[]{start, 0});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int cost = cur[1];
			
			if(dist[node] < cost) continue;
			
			for(int[] next:graph[node]) {
				int nextNode = next[0];
				int nextCost = next[1];
				
				if(nextCost+cost < dist[nextNode]) {
					parent[nextNode] = node;
					dist[nextNode] = nextCost+cost;
					pq.offer(new int[] {nextNode, dist[nextNode]});
				}
			}
		}
	}
	
	static void cityCnt(int node) {
		
		if(start == node) {
			path.add(start);
			return;
		}
		path.add(node);
		cityCnt(parent[node]);
	}

}