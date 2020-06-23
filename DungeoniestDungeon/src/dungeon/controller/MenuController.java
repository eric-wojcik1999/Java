package dungeon.controller; 

import dungeon.controller.ReaderFactory; 
import dungeon.controller.fileio.FileReader;
import dungeon.controller.exceptions.CanNotLoadShopDataException; 
import dungeon.model.Player; 
import dungeon.model.Item; 
import java.util.*;
import java.io.*;

/**************************************************************************
*  Class: MenuController                                                  *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 15/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Serves as a thin layer between the MenuInterface and the      *
*  model classes it uses.                                                 *
***************************************************************************/

public class MenuController
{
    private Player player; 
    private FileReader fileReader;
    private ReaderFactory readerFactory; 
    private String gameStatus; 

    //CONSTRUCTORS 

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inPlayer (Player), inFileReader (FileReader),                  *
    *  inReaderFactory(ReaderFactory)                                         *
    *  EXPORT: none                                                           *
    *  ASSERTION: A MenuController initialised with a valid player, a valid   *
    *  and file reader and a valid readerfactory.                             *
    ***************************************************************************/
   
    public MenuController(Player inPlayer, FileReader inFileReader, ReaderFactory inReaderFactory)
    {
	player = inPlayer;
	fileReader = inFileReader; 
        readerFactory = inReaderFactory; 
	gameStatus = "PLAYING"; 
    }

    //ACCESSORS 

    /**************************************************************************
    *  SUBMODULE: getFileReader                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: fileReader (FileReader)                                        *
    *  ASSERTION: Returns a FileReader object                                 *
    ***************************************************************************/

    public FileReader getFileReader()
    {
	return fileReader; 
    }

    /**************************************************************************
    *  SUBMODULE: getNumPlayerArmours                                         *
    *  IMPORT: none                                                           *
    *  EXPORT: numPlayerArmours (int)                                         *
    *  ASSERTION: Returns the total number of armours in a player's inventory *
    ***************************************************************************/

    public int getNumPlayerArmours()
    {
	return player.getNumArmours(); 
    }

    /**************************************************************************
    *  SUBMODULE: getNumPlayerWeapon                                          *
    *  IMPORT: none                                                           *
    *  EXPORT: numPlayerWeapons (int)                                         *
    *  ASSERTION: Returns the total number of weapons in a player's inventory *
    ***************************************************************************/

    public int getNumPlayerWeapons()
    {
	return player.getNumWeapons(); 
    }

    /**************************************************************************
    *  SUBMODULE: getGameStatus                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: gameStatus (String)                                            *
    *  ASSERTION: Returns the string value respresenting the game's status    *
    ***************************************************************************/
 
    public String getGameStatus()
    {
  	return gameStatus; 
    }

    /**************************************************************************
    *  SUBMODULE: obtainItems                                                 *
    *  IMPORT: itemType (String)                                              *
    *  EXPORT: results (String)                                               *
    *  ASSERTION: Returns a transcript of all the items you have in your      *
    *  inventory of a certain type                                            *
    ***************************************************************************/

    public String obtainItems(String itemType)
    {
	String results = "";
	
	if(itemType.equals("W"))
	{
	    results = "\n------------------------------YOUR INVENTORY WEAPONS------------------------------"; 
	    results += "\n" + player.displayItems(itemType);
	}
	else if(itemType.equals("P"))
	{
	    results = "\n------------------------------YOUR INVENTORY POTIONS------------------------------"; 
	    results += "\n" + player.displayItems(itemType);
	}
	else if(itemType.equals("A"))
	{
	    results = "\n------------------------------YOUR INVENTORY ARMOURS------------------------------"; 
	    results += "\n" + player.displayItems(itemType);
	}
	else if(itemType.equals(""))
	{
	    results = "\n-------------------------------YOUR INVENTORY ITEMS-------------------------------"; 
	    results += "\n" + player.displayItems(itemType);
	}
	return results; 
    }

    /**************************************************************************
    *  SUBMODULE: obtainPlayerStats                                           *
    *  IMPORT: none                                                           *
    *  EXPORT: playerStats(String)                                            *
    *  ASSERTION:  Returns a string transcript of the player's current        *
    *  statistics                                                             *
    ***************************************************************************/

    public String obtainPlayerStats()
    {
	return player.toString(); 
    }

    //MUTATORS 


    /**************************************************************************
    *  SUBMODULE: setPlayerName                                               *
    *  IMPORT: name (String)                                                  *
    *  EXPORT: none                                                           *
    *  ASSERTION:  Assigns the name you've inputted as the player's name      *
    ***************************************************************************/

    public void setPlayerName(String name)
    {
        player.setName(name); 
    }

    /**************************************************************************
    *  SUBMODULE: setGameStatus                                               *
    *  IMPORT: inGameStatus (String)                                          *
    *  EXPORT: none                                                           *
    *  ASSERTION:  Changes the gameStatus var to the value of the current     *
    *  gameState                                                              *  
    ***************************************************************************/

    public void setGameStatus(String inGameStatus)
    {
        gameStatus = inGameStatus;     
    }

    /**************************************************************************
    *  SUBMODULE: removeEquippedItem                                          *
    *  IMPORT: itemType(String)                                               *
    *  EXPORT: none                                                           *
    *  ASSERTION: Removes specified equipment from your inventory             *  
    ***************************************************************************/

    public void removeEquippedItem(String itemType)
    {
	player.removeEquipment(itemType); 
    }

    /**************************************************************************
    *  SUBMODULE: loadShopData                                                *
    *  IMPORT: source (String)                                                *
    *  EXPORT: none                                                           *
    *  ASSERTION: Loads shop data via chosen file reader                      *
    ***************************************************************************/

    public void loadShopData(String source)
    {
	try
	{
   	    fileReader.loadShopData(source);
	}
	catch(CanNotLoadShopDataException e)
	{
	    System.out.println(e.getMessage());
	} 
    }

    /**************************************************************************
    *  SUBMODULE: equipItem                                                   *
    *  IMPORT: selectedItem (int), itemType (String)                          *
    *  EXPORT: validReader (boolean)                                          *
    *  ASSERTION: Set the passed in weapon item to that of the player's       *
    *  equipped weapon                                                        *
    ***************************************************************************/

    public void equipItem(int selectedItem, String itemType) 
    {

	Item equippedItem = null; 

	if(itemType.equals("W"))
	{
	    equippedItem = player.getWeapon(selectedItem);  
	    player.setEquippedItem(equippedItem);     
	}
	else if(itemType.equals("A"))
	{
	    equippedItem = player.getArmour(selectedItem);  
	    player.setEquippedItem(equippedItem);   
	}
    }

    //OTHER

    /**************************************************************************
    *  SUBMODULE: chooseReader                                                *
    *  IMPORT: readerMethod (String)                                          *
    *  EXPORT: validReader (boolean)                                          *
    *  ASSERTION: Returns true if reader method is successfully chosen        *
    ***************************************************************************/

    public boolean chooseReader(String readerMethod)
    {
	boolean validReader = false; 
	try
	{
	    fileReader = readerFactory.makeFileReader(readerMethod,   fileReader.getShopController(),fileReader.getItemFactory());  
	    validReader = true; 
	}
	catch(IllegalArgumentException e)
	{
	    System.out.println(e.getMessage()); 
	}
	return validReader; 
    }

    /**************************************************************************
    *  SUBMODULE: checkIfEquipped                                             *
    *  IMPORT: itemType (String)                                              *
    *  EXPORT: checkIfEquipped (boolean)                                      *
    *  ASSERTION: Returns true if the specified item is already equipped      *
    ***************************************************************************/

    public boolean checkIfEquipped(String itemType)
    {
	return player.checkIfEquipped(itemType); 
    }

    /**************************************************************************
    *  SUBMODULE: checkIfEquippedGear                                         *
    *  IMPORT: none                                                           *
    *  EXPORT: everythingEquipped (boolean)                                   *
    *  ASSERTION: Returns true if all equipment slots are filled with         * 
    *  equipped items                                                         *
    ***************************************************************************/
 
    public boolean checkIfEquippedGear()
    {
	boolean everythingEquipped = false; 

	if(player.checkIfEquipped("W") && player.checkIfEquipped("A"))
	{
	    everythingEquipped = true; 
	}

	return everythingEquipped; 
    }
} 
