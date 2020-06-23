package dungeon.controller.fileio; 

import dungeon.controller.ItemFactory; 
import dungeon.controller.ShopController; 
import dungeon.controller.exceptions.*; 
import dungeon.model.Item; 

import java.io.*;
import java.util.*; 

/**************************************************************************
*  Class: LocalFileReader                                                 *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: FileReader class that reads shop data from local txt files    *
***************************************************************************/

public class LocalFileReader implements FileReader
{
   private ShopController shopController; 
   private ItemFactory itemFactory; 

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inShopController (ShopController), inItemFactory (ItemFactory) *
    *  EXPORT: none                                                           *
    *  ASSERTION: A LocalFileReader is initialised with a shop controller,    *
    *  and a valid item factory                                               *
    ***************************************************************************/

    public LocalFileReader(ShopController inShopController, ItemFactory inItemFactory)
    {
	shopController = inShopController;
	itemFactory = inItemFactory;  
    }

    /**************************************************************************
    *  SUBMODULE: loadShopData                                                *
    *  IMPORT: source (String)                                                *
    *  EXPORT: none                                                           *
    *  ASSERTION: The entire shop data file is read and processed into        *
    *  meaningful data                                                        *
    ***************************************************************************/

    @Override
    public void loadShopData(String source) throws CanNotLoadShopDataException
    {
	FileInputStream fileStream = null; 
	InputStreamReader rdr; 
	BufferedReader bufRdr; 
	
	String line; 

	try
 	{
	    fileStream = new FileInputStream(source); 
	    rdr = new InputStreamReader(fileStream); 
	    bufRdr = new BufferedReader(rdr); 
	    line = bufRdr.readLine(); 

	    while(line != null)
	    {	
		try
		{
                    processLine(line);
		}
		catch(InvalidFileFormatException e)
		{
		    throw new CanNotLoadShopDataException(e.getMessage()); 
		} 
		line = bufRdr.readLine(); 		
    	    }
	}
	catch(IOException e) //If some issue occurs during file io
	{	
	    if(fileStream != null)
	    {
	        try
	        {
 	            fileStream.close(); 
		}
		catch(IOException e2) //If cannot close file 
		{
		    System.out.println("Unable to close file: " + source);
		    throw new CanNotLoadShopDataException(e2.getMessage());  
		}
	    } 
	}
	finally
	{
	    if(fileStream != null)
	    {
	        try
		{
		    fileStream.close(); 
		}
		catch(IOException e3)
		{
		    System.out.println("Unable to close file: " + source); 
		    throw new CanNotLoadShopDataException(e3.getMessage());     
		}
	    }
	}	
    }

    /**************************************************************************
    *  SUBMODULE: getShopController                                           *
    *  IMPORT: none                                                           *
    *  EXPORT: shopController (ShopController)                                *
    *  ASSERTION: Returns a valid shopController                              *
    ***************************************************************************/

    public ShopController getShopController()
    {
		return shopController; 
    }

    /**************************************************************************
    *  SUBMODULE: getItemFactory                                              *
    *  IMPORT: none                                                           *
    *  EXPORT: itemFactory (ItemFactory)                                      *
    *  ASSERTION: Returns a valid itemFactory                                 *
    ***************************************************************************/

    public ItemFactory getItemFactory()
    {
		return itemFactory; 
    }

    /**************************************************************************
    *  SUBMODULE: processLine                                                 *
    *  IMPORT: line (String)                                                  *
    *  EXPORT: none                                                           *
    *  ASSERTION: Splits a given line into data that can be turned into items *
    ***************************************************************************/

    private void processLine(String line) throws InvalidFileFormatException
    {
	
	//Assuming WEAPON will always have 7 properties to read in
	//Assuming ARMOUR will always have 6 properties to read in
	//Assuming POTION will always have 6 properties to read in

	Item item; 

	char itemType; 
	String itemName;
	String[] otherAttr; //Will hold variable num of additional attributes to be processed   
	int cost, minEffect, maxEffect, j = 0; 
	String[] parts = line.split(",");  

	//ORDER: itemType, itemName, minEffect, maxEffect, cost, -->optional items

	if(parts.length > 1)
	{
	    //Item type should be W, A or P 
            if(!(((parts[0].charAt(0))=='W') || ((parts[0].charAt(0))=='A') || ((parts[0].charAt(0))=='P')) )
	    {
	        throw new InvalidFileFormatException("Item Type Invalid - Must be 'W', 'A', or 'P'"); 
  	    }
	    else
	    {
	        itemType = parts[0].charAt(0);
	    }
 
	    //Item name should not be empty 
	    if(parts[1].trim().equals(""))
	    {
     	        throw new InvalidFileFormatException("Item Name Invalid - Must have a name");
	    }
	    else
	    {
 	        itemName = parts[1].trim(); 
	    }
       
	    //Cost shoud not be empty, and must be number 
            if(parts[2].trim().equals(""))
	    {
	        throw new InvalidFileFormatException("Cost Invalid - Must not be empty"); 
 	    }
	    else
	    {
	        try
	        {
 	            cost = Integer.parseInt(parts[2].trim());
   	        }
	        catch(NumberFormatException e)
  	        {
	            throw new InvalidFileFormatException("Cost Invalid - Must be an whole number"); 
	        }
	    }


            //Min Effect should not be empty and must be a number
 	    if(parts[3].trim().equals(""))
	    {
	        throw new InvalidFileFormatException("Min Effect Invalid - Must not be empty"); 
  	    }
	    else
	    {
	        try
	        {
 	            minEffect = Integer.parseInt(parts[3].trim()); 
   	        }
	        catch(NumberFormatException e)
  	        {
	            throw new InvalidFileFormatException("Min Effect Invalid - Must be an whole number"); 
	        }
   	    }

	    //Max Effect should not be empty and must be a number 
	    if(parts[4].trim().equals(""))
	    {
	        throw new InvalidFileFormatException("Max Effect Invalid - Must not be empty"); 
	    }
	    else
	    {
	        try
	        {
 	            maxEffect = Integer.parseInt(parts[4].trim()); 
   	        }
	        catch(NumberFormatException e)
  	        {
	            throw new InvalidFileFormatException("Max Effect Invalid - Must be an whole number"); 
	        }
	    }
	    
            //Other attributes should not be empty 
            if(parts.length > 4)
	    {
	        otherAttr = new String[parts.length-5]; //Initialise array to num of other attributes 
	
	        for(int i = 5; i < parts.length; i++)
	        { 
 	  	    if(parts[i].trim().equals(""))
	 	    {
	                throw new InvalidFileFormatException("Attribute Invalid - Must not be empty"); 
		    }
	  	    else
		    {
	                otherAttr[j] = parts[i].trim(); 
		        j++;
		    }
	        }

	        //A valid item will always have other attributes 
	        item = itemFactory.makeItem(itemType, itemName, minEffect, maxEffect, cost, otherAttr); 	
		
		if(item != null)
		{
		    shopController.addShopItem(item);
		}
		else
		{
		    throw new IllegalArgumentException("Invalid Item"); 
		}	 
	    }
	    else
	    {
  	        //If file has less than 4 rows per line, it is invalid 
	 	throw new InvalidFileFormatException("Invalid File Format - Not enough data"); 
	    }
	}
    } 
}

