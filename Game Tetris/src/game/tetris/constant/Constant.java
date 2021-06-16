package game.tetris.constant;

public interface Constant {
	
	// enum
	// 1. 클래스처럼 보이게 하는 상수
	// 2. 서로 관련있는 상수들끼리 모아 상수들을 대표할 수 있는 이름으로 타입을 정의하는 것
	// 3. Enum 클래스 형을 기반으로 한 클래스형 선언 
	public enum GameStatus {
		PLAYING, END
	}
	
	public interface Direction {
		public static final int SIZE = 4;
		
		public static final int UP = 0;
		public static final int RIGHT = 1;
		public static final int DOWN = 2;
		public static final int LEFT = 3;
	}
	
	public enum ProcessType {
		DIRECTION, DIRECT_DOWN, AUTO
	}
	
	public interface KeyCode {
		public static final int UP = 38;
		public static final int LEFT = 37;
		public static final int RIGHT = 39;
		public static final int DOWN = 40;
		public static final int SPACE_BAR = 32;
	}
	
	public enum BoardType {
		EMPTY, MOVING_BLOCK, FIXED_BLOCK, LEFT_WALL, RIGHT_WALL,
		BOTTOM_WALL, TOP_WALL, LEFT_TOP_EDGE, RIGHT_TOP_EDGE, 
		LEFT_BOTTOM_EDGE, RIGHT_BOTTOM_EDGE
	}
	
	public static final int MAX_SPEED_LEVEL = 10;
	public static final int MIN_SPEED_LEVEL = 1;
}
