package co.board.view;

import java.util.List;
import java.util.Scanner;

import co.board.access.BoardAccess;
import co.board.access.BoardDAO;
import co.board.model.Board;

public class BoardApp {

	BoardList boardList = new BoardList();

	Scanner scanner = new Scanner(System.in);

	public void start() {
		int menu;
		do {
			boardList.menuTitle();
			System.out.print("입력>>");
			menu = scanner.nextInt();
			switch (menu) {
			case 1: boardList.findAll(); break;
			case 2: boardList.insert(); break;
			case 3: boardList.update(); break;
			case 4: boardList.delete(); break;
			case 5: boardList.findOne(); break;
			case 6: boardList.comment(); break;
			}
		} while (menu != 0);
	}
}
