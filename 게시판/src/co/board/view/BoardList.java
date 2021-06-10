package co.board.view;

import java.util.List;
import java.util.Scanner;

import co.board.access.BoardAccess;
import co.board.access.BoardDAO;
import co.board.model.Board;


public class BoardList {
	
	BoardAccess boardList = new BoardDAO();
	Scanner scanner = new Scanner(System.in);
	
	public void menuTitle() {
		System.out.println("=====   게시판 관리 프로그램   ======");
		System.out.println("1:전체 리스트 | 2:글등록 | 3:글수정"); 
		System.out.println("4:글삭제 | 5:한건조회 | 6:댓글등록");
		System.out.println("7:댓글수정 | 8:댓글삭제 | 0:종료!!");
		System.out.println("=================================");
	}

	public void findAll() {
		List<Board> list = boardList.findAll();
		System.out.println("<전체 게시글 목록>");
		for (Board board : list) {
			System.out.printf("글번호: %s 제목: %s 작성자: %s", board.getB_id(), board.getB_title(), board.getB_user());
			System.out.println();
		}
		System.out.println();
	}

	public void insert() {
		System.out.print("제목>>");
		String title = scanner.nextLine();
		System.out.print("작성자>> ");
		String user = scanner.nextLine();
		System.out.print("글내용>> ");
		String content = scanner.nextLine();
		boardList.insert(title, content, user);
	}
	
	public void update() {
		System.out.print("글번호>> ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.print("변경할 글 내용>> ");
		String content = scanner.nextLine();
		boardList.update(id, content);
	}
	
	public void delete() {
		System.out.print("삭제할 글번호>> ");
		int id = scanner.nextInt();
		scanner.nextLine();
		boardList.delete(id);
	}
	
	public void findOne() {
		System.out.print("찾을 글번호>> ");
		int id = scanner.nextInt();
		scanner.nextLine();
		Board board = boardList.findOne(id);
		System.out.println();
		System.out.printf("<<%d번글의 상세정보>>\n", id);
		System.out.printf("글번호: %s 제목: %s 작성자: %s \n", board.getB_id(), board.getB_title(), board.getB_user());
		System.out.printf("내용: %s\n", board.getB_content());
		System.out.printf("댓글: %d", board.getB_parent_id());
		System.out.println();
		System.out.println();
	}
	
	public void comment() {
		System.out.print("댓글 등록할 글번호>> ");
		int id = scanner.nextInt();
		scanner.nextLine();
		Board board = boardList.comment(id);
		System.out.println("댓글 등록이 완료되었습니다.");
	}
}
