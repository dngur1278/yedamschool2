package co.board.view;

import java.util.List;
import java.util.Scanner;

import co.board.access.BoardAccess;
import co.board.access.BoardDAO;
import co.board.member.Member;
import co.board.model.Board;

public class BoardApp {

	BoardList boardList = new BoardList();

	Scanner scanner = new Scanner(System.in);

	public void start(Member member) {
		int menu;
		do {
			String id = member.getU_id();
			boardList.menuTitle();
			System.out.print("입력>>");
			menu = scanner.nextInt();
			switch (menu) {
			case 1: boardList.findAll(); break;
			case 2: boardList.insert(id); break;
			case 3: boardList.update(id); break;
			case 4: boardList.delete(id); break;
			case 5: boardList.findOne(id); break;
			case 6: boardList.comment(); break;
			}
		} while (menu != 0);
	}
}
