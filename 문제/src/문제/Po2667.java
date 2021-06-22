package 문제;

import java.util.Arrays;
import java.util.Scanner;

public class Po2667 {
	// 집의 존재 여부를 입력할 2차원 배열 
	private static int[][] map = new int[25][25];
	// 탐색할 집의 방문 여부 체크
	private static boolean[][] visit = new boolean[25][25];
	private static int[] dx = {0, -1, 0, 1}; 	// (0,-1)좌 (0,1)우
	private static int[] dy = {-1, 0, 1, 0};	// (-1,0)상 (1,0)하
	// 방문한 단지 번호 (연결된 단지가 아닐경우 1씩 증가)
	private static int count = 1;
	private static int N;		// 지도 크기
	
	// 상,하,좌,우 4방향 체크하면서 DFS(깊이우선)탐색
	public static void dfs(int x, int y) {
		// 방문한 집 => count로 표시(1, 2, 3, ... 하나씩 증가)
		map[x][y] = count;
		visit[x][y] = true; 	// 방문한 집 -> true 체크
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];		// 좌우 체크
			int ny = y + dy[i];		// 상하 체크
			
			// nx, ny = 좌표의 범위, 지도 N*N크기이므로 x, y좌표가 0보단 커야하고 N보단 작아야함
			if (nx>=0 && ny>=0 && nx<N && ny<N) {
				if (map[nx][ny] == 1 && visit[nx][ny] == false) {
					// 1이면서 방문하지 않은 곳은 DFS(깊이우선)로 돌리기: 재귀함수
					dfs(nx, ny);
				}
			}
		}
	}// dfs 
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();	// 지도의 크기
		for(int i = 0; i < N; i++) {	// 2차원 행렬 입력받기
			String str = scanner.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && visit[i][j] == false) {
					// 1이면서 방문하지 않았을 때 dfs메소드 호출하고 단지수 카운트
					dfs(i,j);
					// dfs 재귀함수가 끝날 때 -> 탐색이 끝났으므로, 다음 인접된 집의 단지 번호를 +1 해줘야 함
					count++;
				}
			}
		}
		
		System.out.println(count-1); 	// 단지수 출력
		int[] arr = new int[count];		// 단지수 만큼 집의 수 설정
		for (int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] != 0) {	// 지도에 집이 있을 때
					// 집이 있는 map[i][j]의 경우 count로 저장되었으므로, 단지 번호가 저장
					arr[map[i][j]]++;	 
				}
			}
		}
		Arrays.sort(arr);
		for (int i = 1; i < count; i++) {
			System.out.println(arr[i]); 	// 각 단지번호 별로 단지수 출력
		}
		scanner.close();
	}
}
