package co.board.view;

import java.util.Scanner;

public class CommentList {
	
	String u_id;
	
	Scanner scanner = new Scanner(System.in);
	
	public void commentList() {
		System.out.println("=======  댓글 관리 프로그램  =======");
		System.out.println("1.댓글 등록 | 2.댓글 수정 | 3.댓글삭제");
		System.out.println("================================");
	}
	
	public void commerStart() {
		commentList();
		System.out.print("입력>>");
		int com = scanner.nextInt(); scanner.nextLine();
		if (com == 1) {
			insertComment();
		}
	}
	
	public void insertComment() {
		
	}

}
