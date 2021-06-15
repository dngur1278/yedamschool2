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
	
	private void initWholeSetting() {
		setTitle("TETRIS - START");
		getContentPane().setLayout(null);
		setSize(300, 300);
		setLocation(TetrisUtil.getCenterPosition(this));
		setResizable(false);
	}
	
	private void initMembers() {
		Font font = new Font("Aharoni 굵게", Font.BOLD, 20);
		setSpeedLevel = new JLabel("Set speed Level", SwingConstants.CENTER);
		setSpeedLevel.setBounds(55, 20, 180, 15);
		setSpeedLevel.setFont(font);
		
		getContentPane().add(setSpeedLevel);
		speedLevel = new JSlider();
		speedLevel.setMajorTickSpacing(1);
		speedLevel.setPaintLabels(true);
		speedLevel.setPaintTicks(true);
		speedLevel.setPaintTrack(false);
		speedLevel.setValue(1);
		speedLevel.setMinimum(1);
		speedLevel.setMaximum(10);
		speedLevel.setBounds(50, 50, 180, 62);
		getContentPane().add(speedLevel);
		jbStart = new JButton("GAME START");
		jbStart.setBounds(70,130,148,40);
		getContentPane().add(jbStart);
	}
	
	private void setEvents() {
		speedLevel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!speedLevel.getValueIsAdjusting()) {
					mSpeedLevel = (int) speedLevel.getValue();
				}
			}
		});
		
		jbStart.addActionListener(event -> {
			new TetrisView(mSpeedLevel).setVisible(true);
			dispose();
		});
	}
}
