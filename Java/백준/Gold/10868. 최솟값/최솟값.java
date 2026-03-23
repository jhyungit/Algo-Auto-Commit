import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	static int[] arr;
	static int[] tree;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine().trim());
		}
		
		tree = new int[N * 4];
		segmentTree(1, 1, N); // node, start, end
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			sb.append(getMin(1, 1, N, left, right)).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static int segmentTree(int node, int start, int end) {
		if(start == end) {
			tree[node] = arr[start];
			return tree[node];
		}

		int mid = (start + end) / 2;
		
		int leftMin = segmentTree(node * 2, start, mid);
		int rightMin = segmentTree(node * 2 + 1, mid + 1, end);
		
		tree[node] = Math.min(leftMin, rightMin);
		return tree[node];
	}
	
	// node, start, end: 현재 보고 있는 구간
	// left, right: 내가 구하고 싶은  구간
	static int getMin(int node, int start, int end, int left, int right) {
		// 현재 구간이 아예 안 겹치면
		if(right < start || end < left) {
			return Integer.MAX_VALUE;
		}
		
		// 내가 구하는 구하는 구간에 완전히 포함 될 때
		if(left <= start && end <= right) {
			return tree[node];
		}
		
		// 일부만 겹치면 양쪽 자식으로 내려감
		int mid = (start + end) / 2;
		
		int leftMin = getMin(node * 2, start, mid, left, right);
		int rightMin = getMin(node * 2 + 1, mid + 1, end, left, right);
		
		return Math.min(leftMin, rightMin);
	}

}
