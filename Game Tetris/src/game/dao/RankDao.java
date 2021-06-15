package game.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankDao {
	static PreparedStatement psmt;
	static ResultSet rs;
	static Connection conn;
	
	public ArrayList<RankData> Rank() {
		ArrayList<RankData> rankList = new ArrayList<>();
		connect();
		String sql = "select * from rank";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				RankData rankData = new RankData();
				rankData.setName(rs.getString("name"));
				rankData.setScore(rs.getInt("score"));
				
				rankList.add(rankData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rankList;
		
	}
	
	public static void insertRank(String name, int score) {
		connect();
		String sql = "insert into rank values(?, ?) ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setInt(2, score);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
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
