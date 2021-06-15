package game.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.tetris.view.SpeedLevelView;
import game.tetris.view.TetrisView;

public class Start extends JFrame  {
	JButton b1,b2,b3;
	JFrame frm;
	JLabel lb1;
	SpeedLevelView speedLevelView = new SpeedLevelView();
	
	public Start() {		
		frm = new JFrame();					// 프레임 생성
		frm.setTitle("테트리스 게임");
		frm.setSize(500,500);				// 프레임 크기 설정
		frm.setLocationRelativeTo(null);	// 프레임을 화면 가운데 배치
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 레이아웃 설정
		frm.getContentPane().setLayout(null);
		
		b1 = new JButton("Game Setting");	// 버튼 생성
		b2 = new JButton("Game Rank");	// 버튼 생성
		b3 = new JButton("Game Exit");	// 버튼 생성
		
		b1.setBounds(75, 240, 350 ,50);
		b2.setBounds(75, 300, 350 ,50);
		b3.setBounds(75, 360, 350 ,50);
		
		frm.getContentPane().add(b1);
		frm.getContentPane().add(b2);
		frm.getContentPane().add(b3);
		
		Font font = new Font("Aharoni 굵게", Font.BOLD, 40);		// 라벨에 사용할 폰트 설정
		lb1 = new JLabel("TETRIS....", SwingConstants.CENTER);	// 라벨 안의 글 가운데 정렬
		lb1.setBounds(75, 100, 350, 50);						// 라벨 크기 설정
		lb1.setFont(font);										// 라벨에 폰트 추가
		lb1.setForeground(Color.DARK_GRAY);						// 라벨 글색 추가
		frm.getContentPane().add(lb1);							// 프레임에 라벨 추가
		
		// Game Setting버튼 눌렀을 때
		b1.addActionListener(event -> {
			speedLevelView.setVisible(true);
			
		});
		
		// Game Rank버튼 눌렀을 때
		b2.addActionListener(event ->{
			new Rank(null);
		});
		
		// Game Exit버튼 눌렀을 때
		b3.addActionListener(event -> {
			System.exit(0);
		});
		
		// 프레임이 보이도록 설정!!!!!
		frm.setVisible(true);
	}
}
