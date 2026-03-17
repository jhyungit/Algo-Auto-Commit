import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	static int a,b, cost;
	static int[] parent;
	static List<int[]> graph = new ArrayList<>();
	
	static int findParent(int x) {
		if(parent[x] == x) return parent[x];
		return parent[x] = findParent(parent[x]);
	}
	
	static void unionParent(int a, int b) {
		int x = findParent(a);
		int y = findParent(b);
		
		if(x<y) parent[y] = x;
		else parent[x] = y;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 집의 개수
		N = Integer.parseInt(st.nextToken());
		// 길의 개수
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i; // 부모 초기화
		}
		
		// M개의 줄에 A-B cost
		for(int i = 0; i <M; i++) {
			st = new StringTokenizer(br.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			graph.add(new int[] {a,b,cost});
		}
		
		// cost 기준 오름차순 정렬
		graph.sort( (a,b) -> Integer.compare(a[2], b[2]));
		
		int mstCost = 0;
		int maxEdge = 0;
		for(int[] list: graph) {
			int start = list[0];
			int end = list[1];
			int dist = list[2];
			
			if(findParent(start) != findParent(end)) {
				unionParent(start,end); // 경로 압축
			}else { // 사이클 발생
				continue;
			}
			maxEdge = (maxEdge > dist) ? maxEdge : dist;
			mstCost += dist;
		}
		
		System.out.println(mstCost - maxEdge);
	}

}
