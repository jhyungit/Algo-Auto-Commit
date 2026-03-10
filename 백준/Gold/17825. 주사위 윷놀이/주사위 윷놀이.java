import java.io.*;
import java.util.*;

public class Main {
	static int[] dices;
	static int[] horses = {0,1,2,3};
	static int[] selected = new int[10];
	
	static int[] next = new int[33]; // 빨간 화살표 총32개
	static int[] blue = new int[33];
	
	static int[] score = {
			0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40, //바깥 쪽 길(0~20)
			0, // 21 도착
			13,16,19,25, // -> 왼쪽 길(22~25)
			22,24, // 아래쪽 위 길(26~27)
			28,27,26, // <-오른쪽 길(28~30)
			30,35 // 위쪽 위 길(31~32)
	};
	
	static int ans = Integer.MIN_VALUE;
	static final int GOAL = 21;

	// 10, 20, 30
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 빨간 화살표 방향
		for(int i = 0; i < 21; i++) {
			next[i] = i+1;
		}
		
		// 10에서 빠지는 길
		for(int i = 22; i < 25; i++) {
			next[i] = i+1;
		}
		
		// 20에서 빠지는 길
		next[26] = 27;
		next[27] = 25;
		
		// 30에서 빠지는 길
		next[28] = 29;
		next[29] = 30;
		next[30] = 25;
		
		// 가운데 합류 후
		next[25] = 31;
		next[31] = 32;
		next[32] = 20;
		
		// 파란색 화살표 방향
		Arrays.fill(blue, -1);
		blue[5] = 22;
		blue[10] = 26;
		blue[15] = 28;

		
		dices = new int[10];
		
		for(int i = 0; i < 10; i++) {
			dices[i] = Integer.parseInt(st.nextToken());
		}
		
		// 말 4개를 10번 선택하는 경우의 수(중복 순열)
		perm(0); // depth
		
		System.out.println(ans);
		
	}
	
	static void perm(int depth) {
		if(depth == 10) {
			calcScore();
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			selected[depth] = i;
			perm(depth+1);
		}
	}
	
	static void calcScore() {
		int[] pos = new int[4];
		int total = 0;
		
		for(int turn = 0; turn < 10; turn++) {
			int horse = selected[turn];
			int num = dices[turn];
			
			if (pos[horse] == GOAL) return;
			
			int nextLoc = move(pos[horse], num); // 현재 위치, 주사위 숫자
			
			//다른 말과 겹치면 가지치기
			for(int other = 0; other < 4; other++) {
				if(other == horse) continue;
				if(nextLoc != GOAL && pos[other] == nextLoc) return;
			}
			pos[horse] = nextLoc;
			total += score[nextLoc];
		}
		
		ans = Math.max(ans, total);
		return;
	}
	
	static int move(int loc, int dist) {
		int cur = loc;
		
		for(int i = 0; i < dist; i++) {
			if(i == 0 && blue[cur] != -1) { //파란 지름길
				cur = blue[cur];
			}else {
				cur = next[cur];
			}
			
			if(cur == GOAL) break;
		}
		return cur;
	}

}
