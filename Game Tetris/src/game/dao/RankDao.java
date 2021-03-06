package game.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.tools.javac.Main;

public class RankDao {
	static PreparedStatement psmt;
	static ResultSet rs;
	static Connection conn;
	
	// 랭킹 출력!!! 
	public ArrayList<RankData> Rank() {
		ArrayList<RankData> rankList = new ArrayList<>();
		connect();
		String sql = "select * from rank order by score desc";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			int rank = 0;
			while (rs.next()) {
				rank++;
				RankData rankData = new RankData();
				rankData.setName(rs.getString("name"));
				rankData.setScore(rs.getInt("score"));
				rankData.setRank(rank);

				rankList.add(rankData);
				
				if (rankList.size() == 10) {
					break;
				} else {
					continue;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rankList;

	}
	
	// 점수와 이름을 등록
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
		String url = "jdbc:sqlite:C:\\Users\\admin\\git\\yedamschool2\\Game Tetris\\src\\game\\db\\sample.db";
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
