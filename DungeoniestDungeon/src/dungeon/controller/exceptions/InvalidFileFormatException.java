package dungeon.controller.exceptions;  

/**************************************************************************
*  Class: InvalidFileFormatException                                      *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: An exception thrown during file reading if the file is not in *
*  the correct format to be read                                          *
***************************************************************************/

public class InvalidFileFormatException extends Exception
{
    public InvalidFileFormatException(String msg, Throwable cause)
    {
        super(("Shop data file is in invalid format" + msg), cause);
    }
   
    public InvalidFileFormatException(String msg)
    {
        super("Shop data file is in invalid format: " + msg); 
    }

}
