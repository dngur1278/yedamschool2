package 문제;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Po10871 {

	static int n;
	static int x;
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//		
//		n = Integer.parseInt(st.nextToken());
//		x = Integer.parseInt(st.nextToken());
//		
//		StringBuilder sb = new StringBuilder();
//		
//		st = new StringTokenizer(br.readLine(), " ");
//		
//		for (int i=0; i < n; i++) {
//			int value = Integer.parseInt(st.nextToken());
//			
//			if (value < x) {
//				sb.append(value).append(' ');
//			}
//		}
//		System.out.println(sb);
		
		Scanner scanner = new Scanner(System.in);
		
		n = scanner.nextInt();
		x = scanner.nextInt();
		int[] arr = new int[n];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scanner.nextInt();
		}
		
		for (int i=0; i < n; i++) {
			if (arr[i] < x) {
				System.out.print(arr[i] + " ");
			}
		}
	}
}
