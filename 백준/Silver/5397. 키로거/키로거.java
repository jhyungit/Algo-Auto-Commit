import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine().trim());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			
			sb.append(keyLog(s));
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	static StringBuilder keyLog(String s) {
		int cursor = 0;
		Deque<Character> leftStack = new ArrayDeque<>();
		Deque<Character> rightStack = new ArrayDeque<>();
		
		for(char ch : s.toCharArray()) {
			if(ch == '<') {
				if(cursor > 0) {
					cursor--;
					char x = leftStack.pollLast();
					rightStack.offerFirst(x);
				}
			}else if(ch == '>') {
				if(cursor >= 0 && rightStack.size() > 0) {
					cursor++;
					char x = rightStack.pollFirst();
					leftStack.offerLast(x);
				}
			}else if(ch == '-') {
				if(cursor > 0) {
					cursor--;
					leftStack.pollLast();
				}
			}else {
				leftStack.offerLast(ch);
				cursor++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(char ch : leftStack) {
			sb.append(ch);
		}
		for(char ch : rightStack) {
			sb.append(ch);
		}

		return sb;
	}

}
