//Michelle Chen
//Program 2
//Software Security
//Due Date: 3-14-2018

#include <stdio.h>
#include <string.h>

int atoi_reports_errors (const char * s, int * resultp)
{
	int i, j; //for-loop variables
	int decimal = 0; //to count decimals
	int result = 0;
	
	if((s[0] == '-') || ((s[0] >= '0') && (s[0] <= '9'))) //checks validity of first "digit"
	{
		for(i = 1; i < strlen(s); i++) //traverses the string
		{		
			if((s[0] >= '0') && (s[0] <= '9')) //checks for positive floating points
			{
				if(s[i] == '.')
					decimal++;
				if (decimal > 1 || (s[strlen(s)-1] == '.'))
					return -1; //input is not completely numeric
			}//if
			
			else if(s[0] == '-') //checks for negative floating points
			{
				if(s[i+1] == '.')
					decimal++;
				if (decimal > 1 || (s[strlen(s)-1] == '.'))
					return -1; //input is not completely numeric
			}//else if
			
			if(!((s[i] >= '0') && (s[i] <= '9')) && !(s[i] == '.')) //checks for non-numeric digits
				return -1; //input is not completely numeric
		}//for
		
		if (decimal == 1) 
			return -2; //input is a floating point
		 

		result = atoi(s);
		resultp = &result;
		
		return 0; //input is a valid int
	}//if
	
	else //full string cases
	{
		for(i = 1; i < strlen(s); i++)
		{
			if((isdigit(s[i])))
				return -1; //string is not completely numeric
		}
		return -3; //input is a string not an integer
	}
}//newAtoi

void main()
{
	int validPositive, validNegative, stringIn, nonNumeric, floatIn;
	
	////////////////T E S T  C A S E S////////////////
	validPositive = atoi_reports_errors("10123", 0);
	if(validPositive == 0)
			printf("Valid integer input\n");
		
	validNegative = atoi_reports_errors("-8741312", 0);
	if(validNegative == 0)
			printf("Valid integer input\n");
		
	nonNumeric = atoi_reports_errors("123123-" , 0);
	if(nonNumeric == -1)
			printf("Input is not completely numeric\n");		
		
	floatIn = atoi_reports_errors("1.2345" , 0);
	if(floatIn == -2)
			printf("Input is a floating point not an integer\n");			
		
	stringIn = atoi_reports_errors("A String", 0);
	if(stringIn == -3)
			printf("Input is a string not an integer\n");
	 
}//main
