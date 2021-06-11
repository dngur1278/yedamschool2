package co.memo.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import co.memo.model.Memo;

// MemoAccess를 구현하는 클래스입니다.
// 기능을 작성하세요.
public class MemoDAO implements MemoAccess {

	Scanner scanner = new Scanner(System.in);
	static Connection conn;
	static PreparedStatement psmt;
	static ResultSet rs;
	static String sql;

	public void write(String date, String title, String content) {
		connect();
		sql = "insert into memo values(?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, date);
			psmt.setString(2, title);
			psmt.setString(3, content);

			int result = psmt.executeUpdate();
			System.out.println(result + "건의 메모가 작성되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void update(String title, String content) {
		connect();
		sql = "update memo set content=? where title=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);
			psmt.setString(2, title);
			int r = psmt.executeUpdate();
			if (r == 0) {
				System.out.println("변경할 제목의 메모가 없습니다.");
			} else {
				System.out.println(title + "의 메모내용이 변경되었습니다.");
				System.out.println("변경된 메모 내용: " + content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void delete(String date) {
		connect();
		sql = "delete from memo where date=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, date);
			int r = psmt.executeUpdate();
			if (r == 0) {
				System.out.printf("%s의 메모가 없습니다.\n", date);
				System.out.println("삭제 실패");
			} else {
				System.out.println(date + "의 메모가 삭제되었습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public ArrayList<Memo> FindAll() {
		ArrayList<Memo> memoList1 = new ArrayList();
		connect();
		sql = "select date, title, content from memo";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Memo memo1 = new Memo();
				memo1.setDate(rs.getString("date"));
				memo1.setTitle(rs.getString("title"));
				memo1.setContent(rs.getString("content"));

				memoList1.add(memo1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return memoList1;
	}

	public ArrayList<Memo> FindByDate(String date) {
		ArrayList<Memo> memoList2 = new ArrayList();
		connect();
		sql = "select * from memo where date=?";
		Memo memo2 = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, date);
			rs = psmt.executeQuery();
			while (true) {
				if (rs.next()) {
					memo2 = new Memo();
					memo2.setDate(rs.getString("date"));
					memo2.setTitle(rs.getString("title"));
					memo2.setContent(rs.getString("content"));

					memoList2.add(memo2);
				} else {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return memoList2;
	}

	public ArrayList<Memo> FindByContent(String content) {
		ArrayList<Memo> memoList3 = new ArrayList();
		connect();
		sql = "select * from memo where content like '%"+content+"%'";
		Memo memo3 = null;
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (true) {
				if (rs.next()) {
					memo3 = new Memo();
					memo3.setDate(rs.getString("date"));
					memo3.setTitle(rs.getString("title"));
					memo3.setContent(rs.getString("content"));
					
					memoList3.add(memo3);
				}else {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return memoList3;
	}

	public static void connect() {
		String url = "jdbc:sqlite:C:/sqlite/db/sample.db";
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
