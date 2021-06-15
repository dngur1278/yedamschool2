package game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Rank extends JFrame{
	
	ArrayList<Object> list = null;
	
	public Rank(ArrayList<Object> list) {
		super("RANK");
		this.list = list;
		
		JP panel = new JP();
		JButton btn = new JButton("CLOSE");
		
		btn.setLocation(155, 500);
		btn.setSize(90,30);
	
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		panel.setLayout(null);
		panel.add(btn);
		add(panel);
		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	class JP extends JPanel {
		
		public void JP() {
			setPreferredSize(new Dimension(400, 550));
			setBackground(Color.MAGENTA);
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			g.setFont(new Font("Aharoni 굵게", Font.PLAIN, 30));
			g.drawString("TETRIS RANK", 110, 40);
			g.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
			g.setColor(Color.red);
			g.drawString("RANK        DATE        USER   SCORE", 8, 80);
			g.setFont(new Font("휴먼엑스포", Font.PLAIN, 17));
			g.setColor(Color.black);
			
		}
	}
}
