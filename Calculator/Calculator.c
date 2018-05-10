//Michelle Chen
//Program 1
//Software Security
///Due Date: 2-19-2018

#include <stdio.h>
#include <ctype.h>
#include <errno.h>
#include <limits.h>
#include <stdlib.h>

void main(int argc, char **argv)
{
    int i; //for-loop variable
    long int sum; //sum of input variable
    long int num; //converted number variable
    char *ptr; //char pointer variable

    errno = 0;
    sum = 0;

    for(i = 1; i < argc; i++) //traverses the input
    {
        num = strtol(argv[i], &ptr, 10); //converts input to base 10 numbers

        if(errno == ERANGE) //checking for range errors within the input
        {
            if(num >= INT_MAX) //checks for input overflow
            {
                perror("The input has a overflow ");
                break;
            }//end if
            else if (num <= INT_MIN) //checks for input underflow
            {
                perror("The input has underflow ");
                break;
            }//end else if
        }//end if

        if(ptr == argv[i]) //sets strings to 0
        {
            num = 0;
        }//end if

        if(num > INT_MAX - sum && num > 0 && sum > 0) //checks the sum for overflow
        {
            perror("The sum returns an overflow");
			printf("The sum returns an overflow - Current number: %ld\n", num);
		    break;
        }//end if

        if(num < INT_MIN - sum && num < 0 && sum < 0) //checks the sum for underflow
        {
            perror("The sum returns an underflow");
            printf("The sum returns an underflow - Current number: %ld\n", num);
			break;
        }//end if

        sum = sum + num; //increments num to sum

    }//end for

        printf("The sum is: %ld\n", sum); //prints the sum
}//end main
