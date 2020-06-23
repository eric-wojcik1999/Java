package dungeon.view; 

import dungeon.controller.MenuController; 
import dungeon.view.ShopInterface; 
import dungeon.view.BattleInterface; 
import dungeon.view.UserInputUtil; 

import java.util.*; 

/**************************************************************************
*  Class: MenuInterface                                                   *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 26/5/2020                                               *
*  Purpose: Serves as the user interface for main menu, allowing players  *
*  to navigate to all core sections of the game                           *
***************************************************************************/

public class MenuInterface
{
    private BattleInterface battleInterface;  
    private ShopInterface shopInterface; 
    private MenuController menuController; 
    
    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inBattleInteface (BattleInterface),                            *
    *   inShopInterface (ShopInterface), inMenuController (MenuController)    *
    *  EXPORT: none                                                           *
    *  ASSERTION: Initalize a MenuInterface object with a valid               *
    *  BattleInterface, ShopInterface and MenuController                      *
    ***************************************************************************/	

    public MenuInterface(BattleInterface inBattleInterface, ShopInterface inShopInterface, MenuController inMenuController)
    {
	battleInterface = inBattleInterface; 
	shopInterface = inShopInterface; 
	menuController = inMenuController; 	
    }

    /**************************************************************************
    *  SUBMODULE: gameMenu                                                    *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: Runs the UI for the main menu                               *
    ***************************************************************************/

    public void gameMenu()
    {
	
	int selectedOption;
	boolean running = true; 
	String gameStatus; 
	String gameTitle = "Welcome to The Dungeoniest Dungeon"; 	
	menuController.setGameStatus("PLAYING"); //Set to default gameStatus 
	
	do
	{
            gameStatus = menuController.getGameStatus(); 

	    if(gameStatus.equals("WON"))
	    {
	        gameTitle += " - NEW GAME PLUS MODE "; 
	        menuController.setGameStatus("PLAYING");
		  
 	    }
	    else if(gameStatus.equals("LOST"))
	    {
	        menuController.setGameStatus("PLAYING"); //Set back to default
		gameStatus = menuController.getGameStatus();   
  	    }

	    System.out.println(""); 
	    System.out.println(gameTitle);  

	    System.out.println(menuController.obtainPlayerStats()); //Post player stats 

	    System.out.println("MENU"); 
	    System.out.println(" 1) Shop");
	    System.out.println(" 2) Choose Character Name"); 
	    System.out.println(" 3) Choose Weapon"); 
	    System.out.println(" 4) Choose Armour"); 
	    System.out.println(" 5) Start Battle");
	    System.out.println(" 6) Load Data"); 
	    System.out.println(" 7) Exit"); 

	    selectedOption = UserInputUtil.userInput("Please select a valid menu option: ",1,7);
	
  	    switch(selectedOption)
	    {
	        case 1: 
	 	    shopInterface.openShop(); 
	  	    break; 
		case 2:
		    chooseName();
	   	    break; 
		case 3:
		    chooseWeapon(); 
	  	    break; 
		case 4:
		    chooseArmour(); 
	  	    break;
		case 5:
		    //Initiate battle 
		    if(menuController.checkIfEquippedGear())  //If no armour/weapon equipped then cannot battle 
		    {
		        menuController.setGameStatus(battleInterface.battleMenu(gameStatus));  
		    }
	 	    else
		    {
			System.out.println("Please equip a weapon and armour!"); 
		    }
		    break; 
		case 6:		   
		    chooseLoadDataMethods();
		    break;
		case 7:
		    running = false; 
		    System.out.println("Exiting game......"); 
		    System.exit(0);
		    break;	
		default:
		    running = false;
		    break;
 	    }
   	}
	while(running); 
    }

    /**************************************************************************
    *  SUBMODULE: chooseName                                                  *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: A player is assigned a new name by the user                 *
    ***************************************************************************/

    public void chooseName()
    {
	String chosenName;
	chosenName = UserInputUtil.userInput("\nPlease enter a new name for your character?"); 
	menuController.setPlayerName(chosenName);	
    } 

    /**************************************************************************
    *  SUBMODULE: chooseWeapon                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: A player equips a new weapon, removes an existing one, or   *
    *  swaps currently equipped weapon with another one from their inventory  *
    ***************************************************************************/

    public void chooseWeapon()
    {
	int subMenuOption, numItems = 0; 
	String prompt = "\nWhich weapon would you like to equip? - 0 to Cancel"; 
	boolean running = true, subMenuRunning = true;

 	do
	{
 	    System.out.println(menuController.obtainItems("W")); 
	    numItems = menuController.getNumPlayerWeapons();
	     

	    if(!menuController.checkIfEquipped("W"))
	    {
	    	
	        if(!equipNewItem("W", prompt, numItems))
		{
 		    running = false; 
 		}		
	    } 
	    else
	    {
	 	do
		{
	    	    System.out.println(" 1) Equip new weapon from inventory "); 
		    System.out.println(" 2) Remove currently equipped Weapon "); 	
		    System.out.println(" 3) Return "); 

		    subMenuOption = UserInputUtil.userInput("\nSelect a valid menu option", 1,3);

		    switch(subMenuOption)
		    {
			case 1:
			    if(!equipNewItem("W", prompt, numItems))
			    {
 			        subMenuRunning = false; 
 			    }		
			    break;
			case 2:
			    removeExistingArmour(); 
			    subMenuRunning = false;  
			    break;
			case 3:
			    subMenuRunning = false;
			    running = false; 
			    break; 
			default:
			    subMenuRunning = false;
			    running = false; 
			    break; 
 		    }		
		}
		while(subMenuRunning); 
	    }	
	}
	while(running);
    }

    /**************************************************************************
    *  SUBMODULE: chooseArmour                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: A player equips a new armour, removes an existing one, or   *
    *  swaps currently equipped armour with another one from their inventory  *
    ***************************************************************************/

    public void chooseArmour()
    {
	int subMenuOption, numItems = 0; 
	String prompt = "\nWhich armour would you like to equip? - 0 to Cancel"; 
	boolean running = true, subMenuRunning = true;

 	do
	{
 	    System.out.println(menuController.obtainItems("A")); 
	    numItems = menuController.getNumPlayerArmours();

	    if(!menuController.checkIfEquipped("A"))
	    {
	        if(!equipNewItem("A", prompt, numItems))
		{
 		    running = false; 
 		}		
	    } 
	    else
	    {
	 	do
		{
	    	    System.out.println(" 1) Equip new armour from inventory "); 
		    System.out.println(" 2) Remove currently equipped Armour "); 	
		    System.out.println(" 3) Return "); 

		    subMenuOption = UserInputUtil.userInput("\nSelect a valid menu option", 1,3);

		    switch(subMenuOption)
		    {
			case 1:
			    if(!equipNewItem("A", prompt, numItems))
			    {
 			        subMenuRunning = false; 
 			    }		
			    break;
			case 2:
			    removeExistingArmour(); 
			    subMenuRunning = false;  
			    break;
			case 3:
			    subMenuRunning = false;
			    running = false; 
			    break; 
			default:
			    subMenuRunning = false;
			    running = false; 
			    break; 
 		    }		
		}
		while(subMenuRunning); 
	    }	
	}
	while(running);
    }

    /**************************************************************************
    *  SUBMODULE: chooseLoadDataMethods                                       *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: Either a LOCAL or REMOTE data loading is chosen to read in  *
    *  shop data                                                              *
    ***************************************************************************/

    public void chooseLoadDataMethods()
    {
	String readerMethod, source; 
	boolean running = false; 

	do
	{
	    readerMethod = UserInputUtil.userInput("Choose a method of loading shop data: (L)ocal or (R)emote? "); 
		
            if(menuController.chooseReader(readerMethod))
	    {
	        source = UserInputUtil.userInput("Enter source name: "); 
	        menuController.loadShopData(source);
	 	running = false;  
            }	
	    else
	    {
		running = true; 
	    } 
	}
	while(running);	
    }

    /**************************************************************************
    *  SUBMODULE: equipNewItem                                                *
    *  IMPORT: itemType (String), prompt (String), numItems (int)             *
    *  EXPORT: running (boolean)                                              *
    *  ASSERTION: A new weapon or item is equipped                            *
    ***************************************************************************/

    private boolean equipNewItem(String itemType, String prompt, int numItems)
    {
	int selectedOption; 
	boolean running = false; 
	
        selectedOption = UserInputUtil.userInput(prompt,0,numItems); 
	    
	if(selectedOption > 0)
	{
	    menuController.equipItem(selectedOption,itemType); 
	    running = true; 
  	}

	return running; 
    }

    private void removeExistingArmour()
    {
	menuController.removeEquippedItem("A"); 
    }
}
