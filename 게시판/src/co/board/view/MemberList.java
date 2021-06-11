package co.board.view;

import java.util.Scanner;

import co.board.access.BoardAccess;
import co.board.access.BoardDAO;
import co.board.member.Member;

public class MemberList {
	
	BoardAccess memberList = new BoardDAO();
	Scanner scanner = new Scanner(System.in);
	
	public void loginTitle() {
		System.out.println("======  로그인(o) 회원가입(x) ======");
		System.out.println("1.로그인 | 2.회원가입(x) | 3.종료");
		System.out.println("=================================");
	}
	
	public void memberStart() {
		int memberMenu = 0;
		do {
			loginTitle();
			System.out.print("입력>>");
			memberMenu = scanner.nextInt(); scanner.nextLine();
			if (memberMenu == 1) {
				Member member = login();
				if (member != null) {
					System.out.println("로그인 성공");
					new BoardApp().start(member);
				}else {
					System.out.println("로그인 실패");
				}
			} else if (memberMenu == 2) {
				
			}
			
		} while (memberMenu != 3);
	}
	
	public Member login() {
		boolean result = false;
		
		System.out.print("아이디>>");
		String id = scanner.nextLine();
		System.out.print("비밀번호>>");
		String pass = scanner.nextLine();
		
		result = memberList.loginMember(id, pass);
		Member member = new Member();
		
		if (result) {
			member.setU_id(id);
			System.out.println(member.getU_id());
			member.setU_pass(pass);
			//new BoardApp().start();
		} else {
			System.out.println("로그인 실패");
			return null;
		}
		
		return member;
	}
}
