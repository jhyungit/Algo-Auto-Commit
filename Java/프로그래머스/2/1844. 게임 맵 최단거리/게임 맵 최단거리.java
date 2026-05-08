import java.util.*;
import java.io.*;

class Solution {
    static int[] dr = {0,0,-1,1};
    static int[] dc  = {-1,1,0,0};
    static boolean[][] visited;
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        int n = maps.length;
        int m = maps[0].length;
        
        visited = new boolean[n][m];
        
        answer = check(maps, n, m);
        return answer;
    }
    
    static int check(int[][] maps, int n, int m){
        Deque<int[]> dq = new ArrayDeque<int[]>();
        
        // 시작 좌표 추가
        dq.offer(new int[] {0, 0, 1});
        visited[0][0] = true;
        
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            
            int r = cur[0];
            int c = cur[1];
            int cnt = cur[2];
            if(r == n-1 && c == m-1) return cnt;
            
            for(int i = 0; i < 4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                // 방문했거나 벽이면
                if(maps[nr][nc] == 0 || visited[nr][nc]) continue;
                
                dq.offer(new int[] {nr,nc,cnt + 1});
                visited[nr][nc] = true;
            }
        }
        return -1;
    }
}