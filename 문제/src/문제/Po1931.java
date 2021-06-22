package 문제;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Po1931 {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();	// 회의 수
		// 회의 시작과 종료시간을 넣을 배열 선언
		int[][] arr = new int[n][2];
		for(int i=0; i < n; i++) {
			arr[i][0] = scanner.nextInt();	// 회의 시작시간
			arr[i][1] = scanner.nextInt();  // 회의 종료시간
		}
		// Arrays.sort() : 기본적으로 오름차순 정렬
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] start, int[] end) {
				if(start[1] == end[1]) {
					// 만약 비교하는 값의 종료시간이 같으면 시작시간으로 정렬
					return Integer.compare(start[0], end[0]);
				}
				// 종료시간에 따라 정렬
				return Integer.compare(start[1], end[1]);
			}
		});
		
		int count = 0;	// 회의실 최대 사용 갯수
		int end = -1; 	// 다음시작 시간과 비교할 변수
		
		for(int i=0; i < n; i++) {
			// 현재 시작시간이 이전 종료시간보다 늦을 경우
			if(arr[i][0] >= end) {
				// 현재 종료시간을 다음 시작시간과 비교하기 위해 저장
				end = arr[i][1];
				count++;
			}
		}
		System.out.println(count);
	}
}
