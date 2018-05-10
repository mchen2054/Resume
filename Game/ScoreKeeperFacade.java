package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreKeeperFacade extends FileNotFoundException
{
	private String name;
	private int score;
	private String addName;
	private int addScore;
	private String listName;
	private int listScore;
	private String list;
	private HeapEntry listHeap;
	
	private Heap h;
	private File scoreList;
	private Scanner scan;
	private FileWriter append;
	private BufferedWriter bufferWrite;
	
	public ScoreKeeperFacade() throws IOException
	{
		scoreList = new File("ScoreList.txt");
		scan = new Scanner(scoreList);
		append = new FileWriter(scoreList, true);
		bufferWrite = new BufferedWriter(append);
		h = new Heap();
		name = "";
		score = 0;
		addName = "";
		addScore = 0;
		listName = "";
		listScore = 0;
		list = "";
		listHeap = null;
	}//ScoreKeeperFacade

	public void newScore(int newScore, String insertName) throws IOException
	{
		score = newScore;
		name = insertName;
		bufferWrite = new BufferedWriter(append);
		
		bufferWrite.newLine();
		bufferWrite.write(name);
		bufferWrite.newLine();
		bufferWrite.write(Integer.toString(score));
		bufferWrite.flush();
	}// getScores
	
	public void saveAllScores() throws IOException
	{
		bufferWrite.close();
	}// saveAllScores

	public void fromFileToHeap() throws FileNotFoundException
	{	
		if(h.isEmpty())
		{
			while(scan.hasNextLine())
			{
				addName = scan.nextLine();
				addScore = Integer.parseInt(scan.nextLine());
				h.insert(new String(addName), addScore);
			}// while
		}
	}// fromFile
	
	public String printTop10()
	{
		int size = h.size();
		if(size < 10)
		{
			for (int i = 1; i < size + 1; i++)
			{
				listHeap = h.remove();
				listName = (String) listHeap.getElement();
				listScore = listHeap.getPriority();
				list = list + "#" + i + " " + listName + " " + listScore + "\n";
			}// for
			h = new Heap();
		}// if
		else
		{
			for (int i = 0; i < 10; i++)
			{
				listHeap = h.remove();
				listName = (String) listHeap.getElement();
				listScore = listHeap.getPriority();
				list = list + listName + " " + listScore + "\n";
			}// for
			h = new Heap();
		}// else
		return list;
	}// printTop10

}
