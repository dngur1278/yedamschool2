package co.board.access;

import java.util.ArrayList;
import java.util.List;

import co.board.model.Board;

public interface BoardAccess {
	// 전체리스트
	public ArrayList<Board> findAll();

	// 글 등록
	public void insert(String title, String content, String user);

	// 글 수정
	public void update(int id, String content, String b_user);

	// 글 삭제
	public void delete(int id, String b_suer);

	// 한건조회
	public Board findOne(int id);

	// 댓글 등록
	public Board commentInsert(int id, String content, String user);
	
	// 로그인
	public boolean loginMember(String id, String pass);

}
