package game.tetris.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;

public class TetrisUtil {
	
	public static Point getCenterPosition(Window window) {
		// 컴퓨터 화면의 중앙에 위치하게 하기 위해서 사용
		Dimension wholeScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension currentWindowSize = window.getSize();
		int left = (wholeScreenSize.width / 2) - (currentWindowSize.width / 2);
		int top = (wholeScreenSize.height / 2) - (currentWindowSize.height / 2);
		return new Point(left, top);
	}
}
