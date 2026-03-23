import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] arr;
	
	static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		tree = new int[N * 4];
		segmentTree(1, 1, N); // node, start, end
		
		M = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int mode = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(mode == 1) update(1, 1, N, a, b); // a번 째 값 b로 변경
			else{
				// 구간 최소값 구하기
				int minValue = getMin(1, 1, N, a, b);
				sb.append(minValue).append('\n');
			}
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
	
	static void update(int node, int start, int end, int idx, int v) {
		if(start == end) {
			tree[node] = v;
			return;
		}
		
		int mid = (start + end) / 2;
		if(idx <= mid) { // 왼쪽
			update(node * 2, start, mid, idx, v);
		}else { // 오른쪽
			update(node * 2 + 1, mid + 1, end, idx, v);
		}
		
		//부모 갱신
		tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
	}
	
	static int getMin(int node, int start, int end, int left, int right) {
		// 범위 밖
		if(right < start || left > end) {
			return Integer.MAX_VALUE;
		}
		
		// 범위가 완전 포함
		if(left <= start && end <= right) {
			return tree[node];
		}
		
		//범위가 일부 포함
		int mid = (start + end) / 2;
		int leftMin = getMin(node * 2, start , mid, left, right);
		int rightMin = getMin(node * 2 + 1, mid + 1, end, left, right);
		return Math.min(leftMin, rightMin);
	}

}
