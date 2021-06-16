package game.dao;

public class RankData {
	private String name;
	private int score;
	private int rank;
	

	public RankData() {}
	
	public RankData(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "랭킹: "+ rank + "위 \t닉네임: " + name + "   점수: " + score +"\n";
	}
}
