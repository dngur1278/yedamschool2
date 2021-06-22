package 문제;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Po10157 {
	private static int K,C,R;
	private static int xx[] = {1, 0, -1, 0};
	private static int yy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str1[] = br.readLine().split(" ");
		
		C = Integer.parseInt(str1[0]);
		R = Integer.parseInt(str1[1]) + 1;
		K = Integer.parseInt(br.readLine());
		
		int d = -1;
		int i = 0;
		int x = 0;
		int y = 1;
		int check = 1;
		while(check > 0) {
			d = (d+1) % 4;
			
			if (d % 2 == 0) {
				R--;
			}else {
				C--;
			}
			
			check = d%2 == 0 ? R : C;
			for (int j = 0; j < check; j++) { 
				i++; 
				x += xx[d]; 
				y += yy[d]; 
				
				if (i == K) { 
					System.out.println(y + " " + x); 
					return; 
				}
			}
		}
		
		bw.write("0");
		bw.flush();
	}
}
