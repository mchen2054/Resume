//Author: Michelle Chen
//Course: Computer Science II: Section 2
//Phase II

package Game;

public class Question 
{
	private String question; // This stores the question
	private String choiceA, choiceB, choiceC, choiceD; //Stores different options
	private String answerKey; // This stores the answer

	
	public Question() 
	{
		question = "none";
		choiceA = choiceB = choiceC = choiceD = "none";
		answerKey = "none";
		
	}// Question

	
	public Question(String q, String a, String b, String c, String d, String ans ) 
	{
		question = q;
		choiceA = a;
		choiceB = b;
		choiceC = c;
		choiceD = d;
		answerKey = ans;
		
	}// Question

	
	public String getQuestion()
	{
		return question;
		
	}// getQuestion

	
	public String getAnswerKey()
	{
		return answerKey;
		
	}// getAnswerKey


	public String getA() 
	{
		return choiceA;
		
	}// getA

	
	public String getB() 
	{
		return choiceB;
		
	}// getB
	
	
	public String getC() 
	{
		return choiceC;
		
	}// getC


	public String getD() 
	{
		return choiceD;
		
	}// getD

}// Question class
