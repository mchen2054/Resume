//Michelle Chen
//Program 6
//Software Security
//Due Date: 5-11-2018

// Write a Java program that will perform a XOR cipher encryption. 
// In addition to any other methods you need, your program should 
// contain a Crypt method. The Crypt method should accept a single 
// String parameter for the message, a single String parameter for the codebook, 
// and should each return a String. Make sure your code cannot be 
// crashed no matter what parameters are passed into your functions.

// Use the Java String class .getBytes() method.
// Use the Java String class constructor String (byte [] Bytes).
// Use the Java ^ XOR operator.

import java.util.Scanner;

public class prog6
{
	public static String crypt(String message, String codeBook)
	{
		byte [] messBytes = message.getBytes();
		byte [] bookBytes = codeBook.getBytes();
		byte [] encryptArray = new byte [messBytes.length];

		for(int i = 0; i < messBytes.length; i++)
		{
			encryptArray[i] = (byte)(messBytes[i]^bookBytes[i % bookBytes.length]);
		}// for loop
		
		String encryptedMess = new String(encryptArray);
		
		return encryptedMess;
	}// crypt
	
	public static void main (String [] args)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the message you would like to encrypt: ");
		String message = scan.nextLine();
		System.out.print("\n");
		
		System.out.println("Enter a codebook: ");
		String codeBook = scan.nextLine();
		System.out.print("\n");
		
		//Encrypt the message
		String encryptedMessage = crypt(message, codeBook);
		System.out.println("Your encrypted message is: \n" + encryptedMessage);
		System.out.print("\n");
		
		//Decrypt the message
		String decryptedMessage = crypt(encryptedMessage, codeBook);
		System.out.println("Decrypted: \n" + decryptedMessage);
	}// main
	
}// prog6