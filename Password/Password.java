//Author: Michelle Chen
//Programming Assignment 4 Part 2: Harder Password
//Due Date: 10/31/2017

package password;

import javax.swing.JOptionPane;

public class Password 
{
	public static boolean tooShort(String s)
	{
		if (s.length() >= 8)
			return false;
		else
			return true;
	}//tooShort
	public static boolean noSpecialChar(String s)
	{
		if(s.contains("!"))
			return false;
		if(s.contains("@"))
			return false;
		if(s.contains("$"))
			return false;
		else
			return true;
	}//noSpecialChar
	public static boolean noSpaces(String s)
	{
		if (s.contains(" "))
			return true;
		else 
			return false;
	}//noSpaces
	public static boolean charStart(String s)
	{
		if(s.charAt(0)=='!')
			return true;
		else if (s.charAt(0)=='?')
			return true;
		else
			return false;
	}//charStart
	public static boolean sameFirstThree(String s)
	{
		if (s.charAt(0)==s.charAt(1) && s.charAt(1)==s.charAt(2))
			return true;
		else
			return false;
	}//sameFirstThree
	public static boolean sameLastThree(String s)
	{
		if (s.charAt(s.length()-1)==s.charAt(s.length()-2) && 
				s.charAt(s.length()-2)==s.charAt(s.length()-3))
			return true;
		else
			return false;
	}//sameLastThree
	
	////////////////////////////////////////////////////////
	
	public static boolean lazyPasswordChoice(String s) //DONE
	{
		String[] worstList = {"123456", "123456789", "qwerty", "12345678",
				"111111", "1234567890", "123456", "password", "123123", "987654321", 
				"qwertyuio", "mynoob", "123321", "666666", "18atcskd2w", "7777777", 
				"1q2w3e4r", "654321", "555555", "3rjs1la7qe", "google", "1q2w3e4r5t", 
				"123qwe", "zxcvbn", "1q2w3e"};
		
		for (int i = 0; i < worstList.length; i++) 
		{
			if (s.equals(worstList[i]))
				return true;
		}
		return false;
	}
	
	////////////////////////////////////////////////////////
	
	public static void main (String [] args)
	{
		final int TRYLIMIT = 4;
		int invalidCount = 1;
		
		do 
		{
			String password = JOptionPane.showInputDialog(null,
					"Create a password");

			if (lazyPasswordChoice(password))
			{
				JOptionPane.showMessageDialog(null,
						"Lazy Password!");
				invalidCount++;
			}
			else if (tooShort(password)) 
			{
				JOptionPane.showMessageDialog(null,
						"Must contain 8 or more characters");
				invalidCount++;
			} 
			else if (noSpecialChar(password)) 
			{
				JOptionPane.showMessageDialog(null, "Must contain !, @, or $");
				invalidCount++;
			} 
			else if (noSpaces(password)) 
			{
				JOptionPane.showMessageDialog(null, "Cannot contain spaces");
				invalidCount++;
			} 
			else if (charStart(password)) 
			{
				JOptionPane.showMessageDialog(null, "Cannot start with ! or ?");
				invalidCount++;
			} 
			else if (sameFirstThree(password)) 
			{
				JOptionPane.showMessageDialog(null,
						"Cannot start with the same three characters");
				invalidCount++;
			}
			else if (sameLastThree(password)) 
			{
				JOptionPane.showMessageDialog(null,
						"Cannot end with the same three characters");
				invalidCount++;
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "Perfect password!");
				System.exit(0);
			}
				
		} while (invalidCount <= TRYLIMIT);
	
		if (invalidCount == TRYLIMIT);
			JOptionPane.showMessageDialog(null,
					"Too many attempts. Your account has been locked.");
			System.exit(0);
		
		
	}//end main

}//end Password class
