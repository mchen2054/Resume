//Author: Michelle Chen
//Course: Computer Science II: Section 2
//Phase II

package Game;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Game
{
	private Grid grid; // holds the grid used to store and display images
	private int userRow; // row the user-controlled image appears in, on the left edge of the grid
	private int msElapsed; // total number of milliseconds that have elapsed
	private int timesGet; // total number of times the user has gotten the things they're supposed to get
							// in the game (collided with the images you want to hit)
	private int timesAvoid; // total number of times the user has been hit buy the things they're supposed
							// to avoid
	private static int score = 0; // keeps track of the score of the player
	private int correctQuestions = 0; // counts the questions the player gets correct
	
	Question[] level1Pool = new Question[25]; // level 1 question array
	Question[] level2Pool = new Question[25]; // level 2 question array
	Question[] level3Pool = new Question[10]; // level 3 question array
	
	static ImageIcon questionIcon = new ImageIcon("question.gif");
	static ImageIcon pikachuIcon = new ImageIcon("pikachurun.gif");

	public Game() throws FileNotFoundException
	{
		grid = new Grid(11, 16, 3000);
		userRow = 10;
		msElapsed = 0;
		timesGet = 0;
		timesAvoid = 0;
		updateTitle();
		grid.setImage(new Location(userRow, 0), "pikachurun.gif");
	}// Game

	public void play() throws FileNotFoundException
	{
		fillQuestions();
		
		while (!isGameOver()) 
		{
			grid.pause(100);
			handleKeyPress();

			if (msElapsed % 300 == 0) 
			{
				scrollLeft();
				populateRightEdge();
			} // if

			// Generates questions after 8000 milliseconds
			if(msElapsed % 8000 == 0 && msElapsed != 0)
			{
				questionGenerator();
			}// if
			updateTitle();
			msElapsed += 100;
		} // while
	}// play
	
	public void fillQuestions() throws FileNotFoundException
	// Reads in the text files containing the questions, options, and answers
	// and puts them into each question array by making them Question objects
	{
		String question = "";
		String choiceA, choiceB, choiceC, choiceD;
		choiceA = choiceB = choiceC = choiceD = "";
		String answer = "";
		int i, j, k; // to insert into array spots
		i = j = k = 0;

		// LEVEL ONE QUESTIONS
		Scanner level1 = new Scanner(new File("questions1.txt"));
		while (level1.hasNextLine()) 
		{
			question = level1.nextLine();
			choiceA = level1.nextLine();
			choiceB = level1.nextLine();
			choiceC = level1.nextLine();
			choiceD = level1.nextLine();
			answer = level1.nextLine();

			Question newQuestion = new Question(question, choiceA, choiceB, choiceC, choiceD, answer);
			level1Pool[i] = newQuestion;
			i++;
		}// while

		// LEVEL TW0 QUESTIONS
		Scanner level2 = new Scanner(new File("questions2.txt"));
		while (level2.hasNextLine()) 
		{
			question = level2.nextLine();
			choiceA = level2.nextLine();
			choiceB = level2.nextLine();
			choiceC = level2.nextLine();
			choiceD = level2.nextLine();
			answer = level2.nextLine();

			Question newQuestion = new Question(question, choiceA, choiceB, choiceC, choiceD, answer);
			level2Pool[j] = newQuestion;
			j++;
		}// while

		// LEVEL THREE QUESTIONS
		Scanner level3 = new Scanner(new File("questions3.txt"));
		while (level3.hasNextLine()) 
		{
			question = level3.nextLine();
			choiceA = level3.nextLine();
			choiceB = level3.nextLine();
			choiceC = level3.nextLine();
			choiceD = level3.nextLine();
			answer = level3.nextLine();

			Question newQuestion = new Question(question, choiceA, choiceB, choiceC, choiceD, answer);
			level3Pool[k] = newQuestion;
			k++;
		} // while
	}// fillQuestions
	
	
	public void questionGenerator()
	// Generates a random question based on array placement.
	// Different questions come up depending on the level
	// which is determined by correctQuestions
	{
		Random rand = new Random();
		int randQue = rand.nextInt(20);
		int randQue3 = rand.nextInt(10);

		// Level 1
		if (correctQuestions <= 5)
		{
			Object[] options = { level1Pool[randQue].getA(), level1Pool[randQue].getB(), level1Pool[randQue].getC(),
					level1Pool[randQue].getD() };
			
			JLabel question = new JLabel();
			question.setFont (new Font ("Papyrus", Font.BOLD, 30) );
			question.setText(level1Pool[randQue].getQuestion());
			int ans = JOptionPane.showOptionDialog(null, question,
					"A Wild Question has Appeared!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					questionIcon, options, options[0]);

			String StringAnswer = Character.toString((char) ((ans + 65))); //converts inputed answer to a String
			if (((String) StringAnswer).equals((String) (level1Pool[randQue].getAnswerKey()))) 
			// Right Answer
			{
				correctQuestions++;
				JLabel right = new JLabel();
				right.setFont (new Font ("Papyrus", Font.BOLD, 30) );
				right.setText("Good job!");
				JOptionPane.showMessageDialog(null, right, "Level 1: Question Defeated!", JOptionPane.PLAIN_MESSAGE, null);
			} // if
			else 
			// Wrong Answer
			{
				timesGet -= 5; // takes off five points from score
				JLabel wrong = new JLabel();
				wrong.setFont (new Font ("Papyrus", Font.BOLD, 30) );
				wrong.setText("Oh no! You dropped some PokeBalls!");
				JOptionPane.showMessageDialog(null, wrong, "Level 1: The Question Fled...",
						JOptionPane.PLAIN_MESSAGE, null);
			} // else
		} // if

		// Level 2
		else if (correctQuestions <= 10) 
		{
			Object[] options = { level2Pool[randQue].getA(), level2Pool[randQue].getB(), level2Pool[randQue].getC(),
					level2Pool[randQue].getD() };
			
			JLabel question = new JLabel();
			question.setFont (new Font ("Papyrus", Font.BOLD, 30) );
			question.setText(level2Pool[randQue].getQuestion());
			int ans = JOptionPane.showOptionDialog(null, question,
					"A Wild Question has Appeared!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					questionIcon, options, options[0]);

			String StringAnswer = Character.toString((char) ((ans + 65))); //converts inputed answer to a String
			if (((String) StringAnswer).equals((String) (level2Pool[randQue].getAnswerKey()))) 
			// Right Answer
			{
				correctQuestions++;
				JLabel right = new JLabel();
				right.setFont (new Font ("Papyrus", Font.BOLD, 30) );
				right.setText("Good job!");
				JOptionPane.showMessageDialog(null, right, "Level 2: Question Defeated!", JOptionPane.PLAIN_MESSAGE, null);
			} // if
			else 
			// Wrong Answer
			{
				timesGet -= 5; // takes five points from score
				JLabel wrong = new JLabel();
				wrong.setFont (new Font ("Papyrus", Font.BOLD, 30) );
				wrong.setText("Oh no! You dropped some PokeBalls!");
				JOptionPane.showMessageDialog(null, wrong, "Level 2: The Question Fled...",
						JOptionPane.PLAIN_MESSAGE, null);
			} // else
		} // if

		// Level 3
		else if (correctQuestions <= 13) 
		{
			Object[] options = { level3Pool[randQue3].getA(), level3Pool[randQue3].getB(), level3Pool[randQue3].getC(),
					level3Pool[randQue3].getD() };

			JLabel question = new JLabel();
			question.setFont (new Font ("Papyrus", Font.BOLD, 30) );
			question.setText(level3Pool[randQue].getQuestion());
			int ans = JOptionPane.showOptionDialog(null, question,
					"A Wild Question has Appeared!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					questionIcon, options, options[0]);

			String StringAnswer = Character.toString((char) ((ans + 65))); //converts inputed answer to a String
			if (((String) StringAnswer).equals((String) (level3Pool[randQue3].getAnswerKey()))) 
			// Right Answer
			{
				correctQuestions++;
				JLabel right = new JLabel();
				right.setFont (new Font ("Papyrus", Font.BOLD, 30) );
				right.setText("Good job!");
				JOptionPane.showMessageDialog(null, right, "Level 3: Question Defeated!", JOptionPane.PLAIN_MESSAGE, null);
			} // if
			else 
			// Wrong Answer
			{
				timesGet -= 5; // takes five points off
				JLabel wrong = new JLabel();
				wrong.setFont (new Font ("Papyrus", Font.BOLD, 30) );
				wrong.setText("Oh no! You dropped some PokeBalls!");
				JOptionPane.showMessageDialog(null, wrong, "Level 3: The Question Fled...",
						JOptionPane.PLAIN_MESSAGE, null);
			} // else
		} // if
		
		levelUp();
	}// questionGenerator
	
	public void levelUp()
	{
		if (correctQuestions == 5) 
		{
			JLabel levelUp = new JLabel();
			levelUp.setFont (new Font ("Papyrus", Font.BOLD, 30) );
			levelUp.setText("Great job! Next Level!");
			JOptionPane.showMessageDialog(null, levelUp, "Level 1 --> Level 2",
					JOptionPane.PLAIN_MESSAGE, null);
		} // if
		else if (correctQuestions == 10) 
		{	
			JLabel levelUp = new JLabel();
			levelUp.setFont (new Font ("Papyrus", Font.BOLD, 30) );
			levelUp.setText("Great job! FINAL LEVEL!");
			JOptionPane.showMessageDialog(null, levelUp, "Level 2 --> Level 3",
					JOptionPane.PLAIN_MESSAGE, null);
		}// else if
	}// levelUp

	public void handleKeyPress() 
	{
		int key = grid.checkLastKeyPressed();

		if ((key == 38) && (userRow != 0))
			userRow--;
		else if (key == 40 && (userRow != grid.getNumRows() - 1))
			userRow++;

		handleCollision(new Location(userRow, 0));

		grid.setImage(new Location(userRow, 0), null);
		grid.setImage(new Location(userRow, 0), "pikachurun.gif");
	}// handleKeyPress

	public void populateRightEdge() 
	{
		int size = 20; // the frequency of the population
		for (int i = 0; i < grid.getNumRows(); i++) 
		{
			int num = (int) (Math.random() * size);

			if (num == 0)
				grid.setImage(new Location(i, grid.getNumCols() - 1), "avoid.gif");
			else if (num == 1)
				grid.setImage(new Location(i, grid.getNumCols() - 1), "get.png");
			else // puts in a blank space
				continue;
		} // for loop
		
		int lifeSize = 400; // how often the life appears
		for (int i = 0; i < grid.getNumRows(); i++) 
		{
			int num = (int) (Math.random() * lifeSize);

			if (num == 1)
				grid.setImage(new Location(i, grid.getNumCols() - 1), "life.png");
			else // puts in a blank space
				continue;
		} // for loop
	}// populateRightEdge

	public void scrollLeft() 
	{
		handleCollision(new Location(userRow, 0));
		for (int row = 0; row < grid.getNumRows(); row++) 
		{
			for (int col = 0; col < grid.getNumCols() - 1; col++) 
			{
				if (col == 0)
					grid.setImage(new Location(row, col), null);
				if (grid.getImage(new Location(row, col + 1)) != null) 
				{
					grid.setImage(new Location(row, col), grid.getImage(new Location(row, col + 1)));
					grid.setImage(new Location(row, col + 1), null);
				} // if
			} // for loop
		} // for loop
	}// scrollLeft

	public void handleCollision(Location loc) 
	{
		if (grid.getImage(loc) != null) 
		{
			if (grid.getImage(loc).equals("get.png"))
				timesGet++;
			else if (grid.getImage(loc).equals("avoid.gif"))
				timesAvoid++;
			else if(grid.getImage(loc).equals("life.png"))
				timesAvoid--;
		} // if

	}// handleCollision

	public int getScore() 
	{
		score = timesGet;

		if (score > 0)
			return score;
		else
			return 0;
	}// getScore

	public void updateTitle() 
	{
		grid.setTitle("PokeBalls Caught:  " + getScore() + " | Lives:  " + (3 - timesAvoid) 
				+ " | Correct Questions:  " + correctQuestions);
	}// updateTitle
	

	public boolean isGameOver() 
	{
		if (timesAvoid == 3) 
		{
			if(timesGet < 0) // score will not go below 0
				timesGet = 0;
			
			JLabel results = new JLabel();
			results.setFont (new Font ("Papyrus", Font.BOLD, 30) );
			results.setText("<html>Game Over! <br></br>Better luck next time! <br></br><br></br>PokeBalls Caught: "
					+ timesGet + "<br></br>Correct Questions(x5): " + correctQuestions + "(x5)" + "<br></br>Grand Total: " 
					+ (correctQuestions*5 + timesGet) + "</html>");
			JOptionPane.showMessageDialog(null, results );
			grid.closeFrame();
			return true;
		}// if
		if(correctQuestions == 13)
		{
			if(timesGet < 0) // score will not go below 0
				timesGet = 0;
			
			JLabel results = new JLabel();
			results.setFont (new Font ("Papyrus", Font.BOLD, 30) );
			results.setText("<html>You have finished the game!! <br></br><br></br>PokeBalls Caught: "
					+ timesGet + "<br></br>Correct Questions(x5): " + correctQuestions + "(x5)" + "<br></br>Grand Total: " 
					+ (correctQuestions*5 + timesGet) + "</html>");
			JOptionPane.showMessageDialog(null, results);
			grid.closeFrame();
			 return true;
		}// if
		return false;
	}// isGameOver
	
	public static int getMenuChoice()
	{
		JLabel intro1 = new JLabel();
		intro1.setFont (new Font ("Papyrus", Font.BOLD, 30) );
		
		Object[] options = { "Play", "Instructions", "High Scores", "Exit Game"};
		intro1.setText("Pikachu Catch!");
		int ans = JOptionPane.showOptionDialog(null, intro1,
				"Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
				pikachuIcon, options, options[0]);
		return ans;
		
	}// getMenuChoice

	public static void main(String[] args) throws IOException
	{
		String name = ""; //the player's name
		ImageIcon trainerIcon = new ImageIcon("trainer.gif");
		ImageIcon pokeBallIcon = new ImageIcon("get.png");
		ImageIcon pidgeyIcon = new ImageIcon("avoid.gif");
		ImageIcon masterIcon = new ImageIcon("life.png");
		
		ScoreKeeperFacade storeScore = new ScoreKeeperFacade();
		
		JLabel intro1 = new JLabel();
		intro1.setFont (new Font ("Papyrus", Font.BOLD, 30) );

		//////// Menu ////////
		int ans = getMenuChoice();
		while (ans != 3)
		{
			switch(ans)
			{
				case 0: 
					//////// Intro ////////
					intro1.setText("<html>Your Pokemon Trainer is walking along, "
							+ "<br></br>but their bag suddenly breaks open!!</html>");
					JOptionPane.showMessageDialog(null, intro1, 
							"Pikachu Catch", JOptionPane.PLAIN_MESSAGE, trainerIcon);
					
					intro1.setText("<html>As a trusty Pikachu, you will help "
							+ "<br></br>catch all the runaway PokeBalls!</html>");
					JOptionPane.showMessageDialog(null, intro1, 
							"Pikachu Catch", JOptionPane.PLAIN_MESSAGE, pikachuIcon);
					
					//////// Game Start ////////
					
					do
					{
						intro1.setText("Enter your name");
						name = JOptionPane.showInputDialog(intro1);
					}
					while(name == null || name == " " || name.length() == 0);

					intro1.setText("Let's Play Pikachu Catch, "+ name + "!!");
					JOptionPane.showMessageDialog(null, intro1, 
							"Game", JOptionPane.PLAIN_MESSAGE, pikachuIcon);
					
					int reply = 0;
					do
					{
						Game game = new Game();
						game.play();
						storeScore.newScore(score, name);
						storeScore.fromFileToHeap();
						
						Object[] choices = { "Play Again", "Return to Menu"};
						intro1.setText("Game Over!");
						reply = JOptionPane.showOptionDialog(null, intro1,
								"Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
								null, choices, choices[0]);
						
						if(reply == 0)
						{
							intro1.setText("Let's Play Pikachu Catch Again, "+ name + "!!");
							JOptionPane.showMessageDialog(null, intro1, 
									"Game", JOptionPane.PLAIN_MESSAGE, pikachuIcon);
						}// if
					}while(reply == 0);
					break;
				case 1: 
					//////// Instructions ////////
					intro1.setText("Catch PokeBalls with the arrow keys to get points");
					JOptionPane.showMessageDialog(null, intro1, 
							"Pikachu Catch", JOptionPane.PLAIN_MESSAGE, pokeBallIcon);
					
					intro1.setText("If you run into a Pidgey, it will take off a life");
					JOptionPane.showMessageDialog(null, intro1, 
							"Pikachu Catch", JOptionPane.PLAIN_MESSAGE, pidgeyIcon);
					
					intro1.setText("But you can get more lives by catching MasterBalls!");
					JOptionPane.showMessageDialog(null, intro1, 
							"Pikachu Catch", JOptionPane.PLAIN_MESSAGE, masterIcon);
					
					intro1.setText("<html>Wild questions that will show up." + 
							"<br></br>You need to get 13 wild questions right to be done!</html>");
					JOptionPane.showMessageDialog(null, intro1, 
							"Pikachu Catch", JOptionPane.PLAIN_MESSAGE, questionIcon);
					break;
				case 2:
					//////// High Scores ////////
					storeScore.fromFileToHeap();
					intro1.setText("<html>" + storeScore.printTop10().replaceAll("\n", "<br></br>") + "</html>");
					JOptionPane.showMessageDialog(null, intro1, 
							"High Scores", JOptionPane.PLAIN_MESSAGE, pokeBallIcon);
					break;
				case 3:
					storeScore.saveAllScores();
					System.exit(0);
			}// switch
			
			ans = getMenuChoice();
		}// while
		
	}// main

}// Game