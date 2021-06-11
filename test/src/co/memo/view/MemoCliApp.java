package co.memo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.memo.access.MemoAccess;
import co.memo.access.MemoDAO;
import co.memo.model.Memo;

public class MemoCliApp {

	MemoAccess access = new MemoDAO(); // 데이터베이스 입출력 처리하세요.
	Scanner scanner = new Scanner(System.in);

	public void start() {
		int menuNum;

		do {
			menuTitle();
			System.out.print("입력 > ");
			menuNum = scanner.nextInt();
			scanner.nextLine();
			switch (menuNum) {
			case 1: insert(); break;
			case 2: update(); break;
			case 3: delete(); break;
			case 4: findAll(); break;
			case 5: FindByDate(); break;
			case 6: FindByContent(); break;
			}
		} while (menuNum != 0);
		System.out.println("프로그램 종료.");
	}

	private void insert() {
		System.out.print("작성일>>");
		String date = scanner.nextLine();
		System.out.print("제목>>");
		String title = scanner.nextLine();
		System.out.print("내용>>");
		String content = scanner.nextLine();

		access.write(date, title, content);
	}

	private void update() {
		System.out.print("수정할 메모의 제목>>");
		String title = scanner.nextLine();
		System.out.print("내용 변경>>");
		String content = scanner.nextLine();

		access.update(title, content);

	}

	private void delete() {
		System.out.print("삭제할 메모의 작성일>>");
		String date = scanner.nextLine();

		access.delete(date);
	}

	private void findAll() {
		List<Memo> list1 = access.FindAll();
		System.out.println("<전체 메모 조회>");
		for (Memo memo1 : list1) {
			System.out.printf("작성일: %s  제목: %s\n", memo1.getDate(), memo1.getTitle());
			System.out.printf("메모내용: %s\n", memo1.getContent());
			System.out.println();
		}
		System.out.println();
	}

	private void FindByDate() {
		System.out.print("찾고싶은 메모의 작성일>>");
		String date = scanner.nextLine();

		List<Memo> list2 = access.FindByDate(date);
		if (list2.size() == 0) {
			System.out.printf("%s의 메모가 없습니다.", date);
		} else {
			for (Memo memo2 : list2) {
				System.out.printf("<<%s 메모 제목과 내용>>\n", date);
				System.out.printf("제목: %s\n", memo2.getTitle());
				System.out.printf("메모내용: %s", memo2.getContent());
				System.out.println();
			}
		}
		System.out.println();
	}

	private void FindByContent() {
		System.out.print("찾고 싶은 메모의 내용>>");
		String content = scanner.nextLine();

		List<Memo> list3 = access.FindByContent(content);
		if (list3.size() == 0) {
			System.out.printf("%s가 포함된 메모의 내용이 없습니다.", content);
		} else {
			for (Memo memo3 : list3) {
				System.out.printf("<<%s가 포함된 메모의 작성일과 제목>>\n", content);
				System.out.printf("작성일: %s  제목: %s\n", memo3.getDate(), memo3.getTitle());
				System.out.printf("메모내용: %s", memo3.getContent());
				System.out.println();
			}
		}
		System.out.println();
	}

	private void menuTitle() {
		System.out.println("== 메모 관리 ==");
		System.out.println("=1. 메모 추가");
		System.out.println("=2. 메모 수정");
		System.out.println("=3. 메모 삭제");
		System.out.println("=4. 전체 조회");
		System.out.println("=5. 날짜로 조회");
		System.out.println("=6. 내용으로 조회");
		System.out.println("=0. 종료");
		System.out.println("===============");
	}
}
