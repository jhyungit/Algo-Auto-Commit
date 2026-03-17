import java.io.*;
import java.util.*;

public class Main {
	static int N, R, C;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		
		while(N > 0) {
			
			int half = 1<<(N-1);
			int oneSize = half * half; // 한 사분면의 칸 개수
			// 1, 2
			// 3, 4 의 위치별 곱해주는 값.
			int pos = -1;
			
			if(R < half && C < half) { // 1번 위치
				pos = 0;
			}
			if(R < half && C >= half) { // 2번 위치
				pos = 1;
				C -= half;
				
			}
			if(R >= half && C < half) { // 3번 위치
				pos = 2;
				R -= half;
			}
			if(R >= half && C >= half) { // 4번 위치
				pos = 3;
				R -= half;
				C -= half;
			}
			
			ans += pos* oneSize;
			N--;
		}
		
		System.out.println(ans);
		
	}

}
