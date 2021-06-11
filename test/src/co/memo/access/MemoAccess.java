package co.memo.access;

import java.util.ArrayList;

import co.memo.model.Memo;

public interface MemoAccess {
	public void write(String date, String title, String content);
	public void update(String title, String content);
	public void delete(String title);
	public ArrayList<Memo> FindAll();
	public ArrayList<Memo> FindByDate(String date);
	public ArrayList<Memo> FindByContent(String content);
}
