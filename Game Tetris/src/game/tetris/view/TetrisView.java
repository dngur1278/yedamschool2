package game.tetris.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.dao.RankDao;
import game.tetris.constant.Constant;
import game.tetris.controller.TetrisManager;
import game.tetris.util.TetrisUtil;

public class TetrisView extends JFrame{
	
	private static final String TETRIS_BACKGROUND_MUSIC_FILE_NAME =".\\res\\tetris_background_music.wav";
	
	private long mCurrentTimeMilliSecond = 0;
	private int mInitialSpeedLevel;
	private Clip mSoundClip;
	private TetrisManager mTetrisManager;
	private Constant.ProcessType mProcessType;
	private int mDirection;
	private Constant.GameStatus mGameStatus;
	private boolean mIsKeyPressed;
	RankDao rankDao = new RankDao();
	
	public TetrisView(int initialSpeedLevel) {
		mInitialSpeedLevel = initialSpeedLevel;
		initWholeSetting();
		initMembers();
		setEvents();
	}
	
	public void start() {
		mSoundClip.start();
		mSoundClip.loop(Clip.LOOP_CONTINUOUSLY);
		repaint();
	}
	
	public void process(Constant.ProcessType processType, int direction) {
		if (processType == Constant.ProcessType.DIRECTION) {
			mTetrisManager.changeBoardByDirection(direction);
		} else if (processType == Constant.ProcessType.DIRECT_DOWN) {
			mTetrisManager.processDirectDown();
		} else if (processType == Constant.ProcessType.AUTO) {
			mTetrisManager.changeBoardByAuto();
		}
		
		if (mTetrisManager.isReachedToBottom()) {
			if (processType == Constant.ProcessType.DIRECTION
					&& direction == Constant.Direction.LEFT
					&& (mTetrisManager
							.checkValidPosition(Constant.Direction.LEFT) == Constant.BoardType.EMPTY)
					|| processType == Constant.ProcessType.DIRECTION
					&& direction == Constant.Direction.RIGHT
					&& (mTetrisManager
							.checkValidPosition(Constant.Direction.RIGHT) == Constant.BoardType.EMPTY)) {
				mTetrisManager.changeBoardByDirection(direction);
			}
			if (mTetrisManager.processReachedCase() == Constant.GameStatus.END) {
				end();
			}
		}
		mTetrisManager.processDeletingLines();
		repaint();
	}
	
	public void end() {
		int speed_level = mTetrisManager.getSpeedLevel();
		int delete_line_count = mTetrisManager.getDeletedLineCount();
		int score = mTetrisManager.getmScore();
		mGameStatus = Constant.GameStatus.END;
		mSoundClip.stop();
		JOptionPane.showMessageDialog(null, "               level : " + speed_level 
				+ "          deleted lines : " + delete_line_count 
			    +"\n               score: " + score 
				, "TETRIS - END", JOptionPane.PLAIN_MESSAGE);
		
		String name = (String) JOptionPane.showInputDialog(this, "name", "랭킹등록", JOptionPane.PLAIN_MESSAGE);
		System.out.println(name);
		
		rankDao.insertRank(name, score);
		
		dispose();
	}
	
	public void paint(Graphics g) {
		Image buffer = createImage(getWidth(), getHeight());
		Graphics graphics = buffer.getGraphics();
		graphics.setColor(Color.black);
		Font font = graphics.getFont();
		graphics.setFont(new Font(font.getName(), Font.BOLD, 30));
		mTetrisManager.print(graphics);
		g.drawImage(buffer, 0, 0, this);
	}
	
	public void initWholeSetting() {
		setTitle("TETRIS - ING...");
		getContentPane().setLayout(null);
		setSize(800, 600);
		setLocation(TetrisUtil.getCenterPosition(this));
		setResizable(false);
	}

	private void initMembers() {
		try {
			mSoundClip = AudioSystem.getClip();
			mSoundClip.open(AudioSystem.getAudioInputStream(new File(
					TETRIS_BACKGROUND_MUSIC_FILE_NAME)));
		} catch (LineUnavailableException lue) {
			lue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (UnsupportedAudioFileException uafe) {
			uafe.printStackTrace();
		}
		mTetrisManager = new TetrisManager(mInitialSpeedLevel);
		new Thread(new Runnable() {

			@Override
			public void run() {
				start();
				mGameStatus = Constant.GameStatus.PLAYING;
				mIsKeyPressed = false;
				while (mGameStatus != Constant.GameStatus.END) {
					mProcessType = Constant.ProcessType.AUTO;
					mDirection = Constant.Direction.DOWN;
					mCurrentTimeMilliSecond = System.currentTimeMillis();
					while (true) {
						if (mIsKeyPressed) {
							mIsKeyPressed = false;
							break;
						}
						if (!mIsKeyPressed
								&& System.currentTimeMillis()
										- mCurrentTimeMilliSecond > getDownMilliSecond()) {
							mProcessType = Constant.ProcessType.AUTO;
							mDirection = Constant.Direction.DOWN;
							break;
						}
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					process(mProcessType, mDirection);
				}
			}
		}).start();
	}

	
	private void setEvents() {
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				mIsKeyPressed = true;
				switch (e.getKeyCode()) {
				case Constant.KeyCode.UP:
					mProcessType = Constant.ProcessType.DIRECTION;
					mDirection = Constant.Direction.UP;
					break;
				case Constant.KeyCode.LEFT:
					mProcessType = Constant.ProcessType.DIRECTION;
					mDirection = Constant.Direction.LEFT;
					break;
				case Constant.KeyCode.RIGHT:
					mProcessType = Constant.ProcessType.DIRECTION;
					mDirection = Constant.Direction.RIGHT;
					break;
				case Constant.KeyCode.DOWN:
					mProcessType = Constant.ProcessType.DIRECTION;
					mDirection = Constant.Direction.DOWN;
					break;
				case Constant.KeyCode.SPACE_BAR:
					mProcessType = Constant.ProcessType.DIRECT_DOWN;
					break;
				}
			}
		});
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				end();
			}
		});
	}
	
	public long getDownMilliSecond() {
		return mTetrisManager.getDownMilliSecond();
	}
}