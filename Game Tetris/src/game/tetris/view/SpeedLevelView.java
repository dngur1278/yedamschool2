package game.tetris.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import game.tetris.util.TetrisUtil;

public class SpeedLevelView extends JDialog{
	
	private JLabel setSpeedLevel;
	private JSlider speedLevel;
	private JButton jbStart;
	
	private int mSpeedLevel;
	
	public SpeedLevelView() {
		initWholeSetting();
		initMembers();
		setEvents();
	}
	
	// 화면 설정!!
	private void initWholeSetting() {
		setTitle("TETRIS - START");
		getContentPane().setLayout(null);
		setSize(300, 300);
		setLocation(TetrisUtil.getCenterPosition(this)); // setLocation으로 컴포넌트 위치 지정
		setResizable(false); // 창크기 고정
	}
	
	// 화면안에 들어갈 라벨, 슬라이더, 버튼 설정
	private void initMembers() {
		Font font = new Font("Aharoni 굵게", Font.BOLD, 20);
		setSpeedLevel = new JLabel("Set speed Level", SwingConstants.CENTER);
		setSpeedLevel.setBounds(55, 20, 180, 15);
		setSpeedLevel.setFont(font);
		
		getContentPane().add(setSpeedLevel);
		speedLevel = new JSlider();
		speedLevel.setMajorTickSpacing(1);	// 큰 눈금의 크기 설정
		speedLevel.setPaintLabels(true);	// 슬라이더의 라벨 보여주기(t/f)
		speedLevel.setPaintTicks(true);		// 슬라이더의 눈금 보여주기(t/f)
		speedLevel.setPaintTrack(false);	// 슬라이더의 트랙 보여주기(t/f)
		speedLevel.setValue(1);	
		speedLevel.setMinimum(1);		// 슬라이더의 Minimum 설정
		speedLevel.setMaximum(10);		// 슬라이더의 Maximum 설정
		speedLevel.setBounds(50, 50, 180, 62);
		getContentPane().add(speedLevel);
		jbStart = new JButton("GAME START");
		jbStart.setBounds(70,130,148,40);
		getContentPane().add(jbStart);
	}
	
	// 슬라이더의 눈금의 값을 int로 형변화해서 변수에 넣어준다
	private void setEvents() {
		speedLevel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!speedLevel.getValueIsAdjusting()) {
					// getValue => 현재 슬라이더 값 반환
					mSpeedLevel = (int) speedLevel.getValue();
				}
			}
		});
		
		// 버튼으로 게임 실행 화면으로 전환
		jbStart.addActionListener(event -> {
			new TetrisView(mSpeedLevel).setVisible(true);
			dispose(); // 현재 프레임 종료
		});
	}
}
