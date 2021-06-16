package game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import game.dao.RankData;

public class Rank extends JFrame{
	
	private JButton b1;
	private JFrame frm;
	private JLabel lb;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	public Rank(ArrayList<RankData> list) {
		frm = new JFrame();
//		JPanel panel = new JPanel();
		frm.setTitle("GAME RANK");
		frm.setSize(400, 500);
		frm.setLocationRelativeTo(null);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.getContentPane().setLayout(null);
		
		b1 = new JButton("CLOSE");
		
		b1.setBounds(150, 400, 100, 30);
		
		Font font1 = new Font("Aharoni 굵게", Font.BOLD, 30);
		Font font2 = new Font("Aharoni 굵게", Font.BOLD, 15);
		lb = new JLabel("RANK", SwingConstants.CENTER);
		lb.setBounds(138, 30 ,100, 30);
		lb.setFont(font1);
		// 글자색 변경
		lb.setForeground(Color.DARK_GRAY);
		
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		textArea.setBounds(18, 80, 350, 300);
		textArea.setFont(font2);
//		textArea.setCaretPosition(textArea.getDocument().getLength()); // 스크롤 기능 구현
		frm.getContentPane().add(lb);
		frm.getContentPane().add(scrollPane);
		frm.getContentPane().add(textArea);
		frm.getContentPane().add(b1);
		
		
		for (RankData rankData : list) {
			textArea.append(rankData.toString());
		}
		textArea.append("\n\n\n\n                   랭킹은 10위까지만 출력됩니다.");
		frm.setVisible(true);
		
		b1.addActionListener(event -> {
			frm.setVisible(false);
		});
	}
	
}
