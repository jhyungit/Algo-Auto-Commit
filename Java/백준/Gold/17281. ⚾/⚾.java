import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int[] tasun = new int[9]; // tasun[타순] = 등번호
	static boolean[] used = new boolean[10]; // 등번호 1~9번 사용 여부
	static int ining[][] = new int[50][10]; // ining[이닝][선수번호] = 0~4(삼진~홈런)
	
	static int N;
	static int ans = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 이닝 수
		

		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j =1; j<10; j++) {
				ining[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		perm(0);
		System.out.println(ans);

	}
	
	static void perm(int idx){
		// 9명 로스터 완성.
		if(idx == 9) {
			// 9명의 라인업 tasun완성. 점수 계산로직 만들기
			ans = Math.max(ans, game());
			return;
		}
		
		// 4번타자는 무조건 등번호 1번 
		if(idx == 3) {
			tasun[idx] = 1;
			perm(idx+1);
			return;
		}
		
		for(int back_num=2; back_num<=9; back_num++) {
			// 사용했다면
			if(used[back_num]) continue;
			used[back_num] = true;
			tasun[idx] = back_num;
			perm(idx+1);
			used[back_num] = false;
		}
	}
	
	static int game() {
		int score = 0;
		int tazaIdx = 0; //타석에 들어가는 타자 인덱스
		
		for (int i = 0; i<N; i++) {
			int outCount = 0;
			int base = 0; // 시작은 빈 베이스(비트마스크 활용)
			
			while(outCount!=3) {
				int player = tasun[tazaIdx];
				int hit = ining[i][player];
				
				if(hit==0) { // 아웃이면
					outCount++;
				} else {
					base <<= hit; // 루타만큼 기존 주자들 진루
					
					base |= (1<<(hit-1)); // 타자주자 추가
					
					// 홈 지난 비트들 득점
					int home = base & ~(0b111);
					score += Integer.bitCount(home);
					
					base &= 0b111;
				}
				
				tazaIdx++;
				if(tazaIdx == 9) tazaIdx = 0;
			}
		}
		return score;

		
	}

}
