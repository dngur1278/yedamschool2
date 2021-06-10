package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDAO {
	// 필드로 선언
	static PreparedStatement psmt;
	static ResultSet rs;
	static Connection conn;

	// 1.조회 2.입력 3.수정 4.삭제 5. 전체리스트(각각 기능을 분리) -> 메소드로 기능 분리

	// 전체리스트
	public ArrayList<Person> getPersonList() {
		ArrayList<Person> personList = new ArrayList<>();
		connect(); // Connection객체 연결
		String sql = "select * from person";
		try {
			psmt = conn.prepareStatement(sql); // PreparedStatement 쿼리를 실행. 결과를 받는다.
			rs = psmt.executeQuery(); // 쿼리 실행결과를 가져오는 부분
			while (rs.next()) {
				// System.out.println(rs.getInt("id"));
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setAge(rs.getInt("age"));
				person.setPhone(rs.getString("phone"));

				personList.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return personList;
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
