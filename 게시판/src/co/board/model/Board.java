package co.board.model;

public class Board {
	private int b_id;
	private String b_title;
	private String b_content;
	private String b_user;
	private int b_parent_id;
	
	public int getB_id() {
		return b_id;
	}
	
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	
	public String getB_title() {
		return b_title;
	}
	
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	
	public String getB_content() {
		return b_content;
	}
	
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	
	public String getB_user() {
		return b_user;
	}
	
	public void setB_user(String b_user) {
		this.b_user = b_user;
	}
	

	public int getB_parent_id() {
		return b_parent_id;
	}

	public void setB_parent_id(int b_parent_id) {
		this.b_parent_id = b_parent_id;
	}

	@Override
	public String toString() {
		return "=====================================================\n"+"게시글 번호: " + b_id + " 제목: " + b_title + 
				" 작성자: " + b_user + "\n" + "내용: " + b_content+"\n"
				+"=====================================================\n";
		
	}
	
	
	
	
}
