package co.board.view;

import java.util.List;
import java.util.Scanner;

import co.board.access.BoardAccess;
import co.board.access.BoardDAO;
import co.board.member.Member;
import co.board.model.Board;


public class BoardList {
	
	BoardAccess boardList = new BoardDAO();
	Scanner scanner = new Scanner(System.in);
	MemberList memberList = new MemberList();
	
	public void menuTitle() {
		System.out.println("===========================           게시판 관리 프로그램           =============================");
		System.out.println("1:전체 리스트 | 2:글등록 | 3:글수정 | 4:글삭제 | 5:한건조회 | 6:댓글등록 | 7:댓글수정 | 8:댓글삭제 | 0:종료!!"); 
		System.out.println("==============================================================================================");
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

	public void insert(String id) {
		System.out.print("제목>>");
		String title = scanner.nextLine();
		System.out.print("글내용>> ");
		String content = scanner.nextLine();
		String user = id;
		System.out.println(user);
		boardList.insert(title, content, user);
	}
	
	public void update(String id) {
		System.out.print("글번호>> ");
		int b_id = scanner.nextInt();
		scanner.nextLine();
		System.out.print("변경할 글 내용>> ");
		String content = scanner.nextLine();
		String b_user = id;
		boardList.update(b_id, content, b_user);
	}
	
	public void delete(String id) {
		System.out.print("삭제할 글번호>> ");
		int b_id = scanner.nextInt();
		scanner.nextLine();
		String b_user = id;
		boardList.delete(b_id, b_user);
	}
	
	public void findOne(String id) {
		System.out.print("찾을 글번호>> ");
		int b_id = scanner.nextInt();
		scanner.nextLine();
		Board board = boardList.findOne(b_id);
		System.out.println();
		System.out.printf("<<%d번글의 상세정보>>\n", b_id);
		System.out.printf("글번호: %s 제목: %s 작성자: %s \n", board.getB_id(), board.getB_title(), board.getB_user());
		System.out.printf("내용: %s\n", board.getB_content());
		System.out.printf("댓글: %d", board.getB_parent_id());
		System.out.println();
		System.out.println();
		CommentList commentList = new CommentList();
		commentList.u_id = id;
	}
	
	public void comment() {
		System.out.print("댓글 등록할 글번호>> ");
		int id = scanner.nextInt();
		scanner.nextLine();
		Board board = boardList.findOne(id);
		
		System.out.print("댓글 단놈>> ");
		String user = scanner.nextLine();
		System.out.print("댓글입력>> ");
		String content = scanner.nextLine();
		
		Board board1 = boardList.commentInsert(board.getB_id(), content, user);
		System.out.println("댓글이 등록 되었습니다.");	
	}
}
