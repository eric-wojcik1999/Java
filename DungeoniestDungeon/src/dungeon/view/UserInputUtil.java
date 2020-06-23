package dungeon.view; 

import java.util.*;

/**************************************************************************
*  Class: UserInputUtil                                                   *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 26/5/2020                                               *
*  Purpose: A generic user input utility provdiding re-usable,            *
*  validitated user input methods for all classes to use                  *
***************************************************************************/

public class UserInputUtil 
{

    /**************************************************************************
    *  SUBMODULE: userInput                                                   *
    *  IMPORT: prompt (String), min (int), max (int)                          *
    *  EXPORT: value (int)                                                    *
    *  ASSERTION: Returns a valid int (within specified range)                *
    ***************************************************************************/

    public static int userInput(String prompt, int min, int max)
    {
	Scanner sc = new Scanner(System.in); 
	int value = min - 1;  

	//Prompts 
	String error = "ERROR: value must be between " + min + " and, " + max + "\n";
	String outputPrompt = "";

	outputPrompt = prompt; 

	do
	{
	    try
 	    {
	        System.out.println(outputPrompt); 
	        value = sc.nextInt();
	    }
	    catch(InputMismatchException e)
	    {
		sc.next(); // Clears buffer of problematic input 
		value = min - 1; //Set number to invalid so we don’t exit 
	    } 
	    outputPrompt = error + prompt;
	} while ((value < min) || (value > max)); 
        return value; 
    }

    /**************************************************************************
    *  SUBMODULE: userInput                                                   *
    *  IMPORT: prompt (String), min (char), max (char)                        *
    *  EXPORT: value (char)                                                   *
    *  ASSERTION: Returns a valid char (within specified range)               *
    ***************************************************************************/

    public static char userInput(String prompt, char min, char max)
    {
	Scanner sc = new Scanner(System.in); 
	char value;  

	//Prompts 
	String error = "ERROR: value must be between " + min + " and, " + max + "\n";
	String outputPrompt = "";

	outputPrompt = prompt; 
	do
	{
	    try
	    {
	        System.out.println(outputPrompt); 
	        value = sc.next().charAt(0);
	    }
	    catch(InputMismatchException e)
 	    {
		sc.next(); // Clears buffer of problematic input 
		int temp = min - 1; 
		value = (char)temp; //Set number to invalid so we don’t exit 
  	    }
	    outputPrompt = error + prompt; 
	} while( ! (((int)value >= (int)min && (int)value <= (int)max))  ); 
	return value;
    }

    /**************************************************************************
    *  SUBMODULE: userInput                                                   *
    *  IMPORT: prompt (String)                                                *
    *  EXPORT: value (String)                                                 *
    *  ASSERTION: Returns a valid String (not null)                           *
    ***************************************************************************/
 
    public static String userInput(String prompt)
    {
	Scanner sc = new Scanner(System.in); 
	String value = "";  

	//Prompts 
	String error = "ERROR: value must be a valid string.\n";
	String outputPrompt = "";

	outputPrompt = prompt; 
	do
	{
	    try
	    {
	        System.out.println(outputPrompt); 
	        value = sc.nextLine(); 
	    }
	    catch(InputMismatchException e)
 	    {
  	        sc.next(); // Clears buffer of problematic input 
	 	value = null; 
            }
	    outputPrompt = error + prompt; 
	} while((value.equals(null))); 
        return value; 
    }
}
