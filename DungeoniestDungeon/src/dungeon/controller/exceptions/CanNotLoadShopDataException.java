package dungeon.controller.exceptions;  

/**************************************************************************
*  Class: CanNotLoadShopDataException                                     *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: An exception thrown during file reading if shop data cannot   *
*  be loaded                                                              *
***************************************************************************/

public class CanNotLoadShopDataException extends Exception 
{
    public CanNotLoadShopDataException(String msg, Throwable cause)
    {
	super(("Unable to load shop data: " + msg), cause);
    }

    public CanNotLoadShopDataException(String msg)
    {
 	super("Unable to load shop data: " + msg);
    }
}
