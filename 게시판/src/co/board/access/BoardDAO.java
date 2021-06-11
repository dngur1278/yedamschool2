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
			System.out.println(result + "건 작성이 완료 되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// 글 수정
	public void update(int id, String content, String b_user) {
		connect();
		sql = "update board set b_content = ? where b_id = ? amd b_user = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);
			psmt.setInt(2, id);
			psmt.setString(3, b_user);

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
	public void delete(int id, String b_user) {
		connect();
		sql = "delete from board where b_id = ? and b_user = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.setString(2, b_user);

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
	public Board commentInsert(int id, String content, String user) {
		connect();
		sql = "insert into board (b_title, b_content, b_user, b_parent_id) values('댓글',?,?,?)";
		Board board = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);
			psmt.setString(2, user);
			psmt.setInt(3, id);
			int r  = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return board;
	}
	
	//로그인 
	public boolean loginMember(String id, String pass) {
		boolean result = false;
		connect();
		sql = "select * from member where u_id=? and u_pass=?";
		
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
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
