package co.board.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.board.model.Board;

public class BoardDAO implements BoardAccess {
	Scanner scanner = new Scanner(System.in);
	static Connection conn;
	static PreparedStatement psmt;
	static ResultSet rs;
	static String sql;

	// 전체조회
	public ArrayList<Board> findAll() {
		ArrayList<Board> boardList = new ArrayList();
		connect();
		sql = "select b_id, b_title, b_user from board";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();
				board.setB_id(rs.getInt("b_id"));
				board.setB_title(rs.getString("b_title"));
				board.setB_user(rs.getString("b_user"));

				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return boardList;
	}

	// 글 등록
	public void insert(String title, String content, String user) {
		connect();
		sql = "insert into board (b_title, b_content, b_user) values (?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, user);

			int result = psmt.executeUpdate();
			System.out.println("작성이 완료 되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// 글 수정
	public void update(int id, String content) {
		connect();
		sql = "update board set b_content = ? where b_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);
			psmt.setInt(2, id);

			int result = psmt.executeUpdate();
			if (result != 0) {
				System.out.println("글 내용이 수정되었습니다.");
			} else {
				System.out.println("없는 번호입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// 글 삭제
	public void delete(int id) {
		connect();
		sql = "delete from board where b_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);

			int result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	// 한건조회
	public Board findOne(int id) {
		connect();
		sql = "select * from board where b_id = ?";
		Board board = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				board = new Board();
				board.setB_id(rs.getInt("b_id"));
				board.setB_title(rs.getString("b_title"));
				board.setB_content(rs.getString("b_content"));
				board.setB_user(rs.getString("b_user"));
				board.setB_parent_id(rs.getInt("b_parent_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return board;
	}

	// 댓글 등록
	public Board comment(int id) {
		connect();
		sql = "select b_parent_id from board where b_id=?";
		Board board = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);

			rs = psmt.executeQuery();
			if (rs.next()) {
				board = new Board();
				board.setB_parent_id(rs.getInt("b_parent_id"));
				if (String.valueOf(board.getB_parent_id()) != null) {
					System.out.println("댓글이 등록 되어있습니다.");
				} else {
					System.out.print("댓글 등록>>");
					String comm = scanner.nextLine();
					int a = board.getB_id();
					board.setB_parent_id(a);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return board;
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
