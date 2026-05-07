import java.io.*;
import java.util.*;

class Solution {
    // 우 하 대각우
    static final int[] dr = {0, 1, 1};
    static final int[] dc = {1, 0, 1};
    static char[][] grid;
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        grid = new char[m][n];
        
        for(int i = 0; i < m; i++){
            grid[i] = board[i].toCharArray();
        }
        
        while(true){
            int popped = check(m, n, grid);    
            if(popped == 0) break;
            answer += popped;
        }
        
        return answer;
    }
    
    static int check(int h, int w, char[][] grid){
        boolean[][] temp = new boolean[h][w];
        int cnt = 0;
        
        for(int r = 0; r < h-1; r++){
            for(int c = 0; c < w-1; c++){
                char cur = grid[r][c];
                int same = 1;
                
                if(cur == '.') continue;
                
                for(int i = 0; i < 3; i++){
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    // 하나라도 같지 않으면 탈출
                    if(cur != grid[nr][nc]) break;
                    same++;
                }
                
                if(same == 4){
                    temp[r][c] = true;
                    for(int i = 0; i < 3; i++){
                        int pr = r + dr[i];
                        int pc = c + dc[i];
                        temp[pr][pc] = true;
                    }
                }
            }
        }
        
        // 터트리기
        for(int r = 0; r < h; r++){
            for(int c = 0; c < w; c++){
                if (temp[r][c]){
                    grid[r][c] = '.';
                    cnt++;
                }
            }
        }
        
        gravity(grid, h, w);
        return cnt;
    }
    
    static void gravity(char[][] grid, int h, int w){
        // 각 열에 대해 중력 적용
        for(int c = 0; c < w; c++){
            int wr = h - 1; // 글자 놓을 자리
            
            // 아래에서 위로 훑으며 글자 찾기
            for(int r = h - 1; r >= 0; r--){
                if(grid[r][c] != '.'){
                    grid[wr][c] = grid[r][c];
                    wr--;
                }
            }
            
            // wr 위의 남은 자리 모두 '.'으로 채우기
            while(wr >= 0){
                grid[wr][c] = '.';
                wr--;
            }
        }
    }
}