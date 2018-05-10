//Michelle Chen
//Program 4
//Software Security
//Due Date: 5-11-2018

// Program 4 starter code
// Starter Code By: James Cain (jcain@sbuniv.edu)
// This is the required starter code for this assignment.
// Correct the buffer overflow problem(s) in the Java code
// provided. Correct any other problems in the code and make
// sure your code cannot be crashed by any particular choices of
// user input, including obviously erroneous input.
// Do not simply replace all of the character arrays with Java
// Strings. Furthermore, you are not permitted to use Java
// Strings anywhere in this assignment.
// Create a main method to test your code.
public class prog4 
{
	public static char[] strcat(char[] destination, char[] source) 
	{

		// Create an array long enough to hold the concatenated array
		int newLength = destination.length + source.length + 1;
		char [] concatenated = new char[newLength];
		
		//////////////////////////////////////////////////////
		
		// Create a new destination array where '\0' is on the end
		char [] newDestination = new char[destination.length + 1];
		newDestination[newDestination.length - 1] = '\0';
		
		for (int destPlace = 0; destPlace < destination.length; destPlace++)
		{
			newDestination[destPlace] = destination[destPlace];
		}// for loop
		
		//////////////////////////////////////////////////////
		
		// Create a new source array where '\0' is on the end
		char [] newSource = new char[source.length + 1];
		newSource[newSource.length - 1] = '\0';
		
		for (int sourcePlace = 0; sourcePlace < source.length; sourcePlace++)
		{
			newSource[sourcePlace] = source[sourcePlace];
		}// for loop
		
		//////////////////////////////////////////////////////
		
		// Insert newDestination array into the concatenation array
		int i = 0;
		while (newDestination[i] != '\0') 
		{
			concatenated[i] = newDestination[i];
			i++;
		} // while loop
		
		//////////////////////////////////////////////////////
		
		// Continue by inserting newSource array into tje concatenation array
		int j = 0;
		while (newSource[j] != '\0') 
		{
			concatenated[i] = newSource[j];
			i++;
			j++;
		} // while loop
		
		//////////////////////////////////////////////////////
		
		concatenated[i] = '\0'; // Null-terminate the concatenated array after
								// the last character from the source array
		return concatenated; // Return concatenated array
	} // public char [] strcat
		
	public static void main (String [] args)
	{
		/////////// 2 Full ////////////
		char [] text1 = {'A', 'B', 'C', 'D'};
		char [] words1 = {'E', 'F', 'G', 'H', 'I'};
		
		char [] concat1 = strcat(text1, words1);
		System.out.println(concat1);
		
		/////// 1 Empty, 1 Full ///////
		char [] text2 = new char[10];
		char [] words2 = {'E', 'F', 'G', 'H', 'I'};
		
		char [] concat2 = strcat(text2, words2); 
		System.out.println(concat2);
		
		/////////// 2 Empty ///////////
		char [] text3 = new char[10];
		char [] words3 = new char [5];
		
		char [] concat3 = strcat(text3, words3);
		System.out.println(concat3);
		
	}// main
	
} // prog4
