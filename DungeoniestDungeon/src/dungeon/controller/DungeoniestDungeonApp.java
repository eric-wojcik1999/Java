package dungeon.controller; 

import dungeon.controller.ItemFactory; 
import dungeon.controller.EnchantmentFactory; 
import dungeon.controller.ReaderFactory; 
import dungeon.controller.EnemyFactory; 
import dungeon.controller.fileio.FileReader; 
import dungeon.controller.fileio.LocalFileReader; 
import dungeon.controller.exceptions.CanNotLoadShopDataException; 
import dungeon.controller.BattleController; 
import dungeon.controller.ShopController; 
import dungeon.controller.MenuController; 
import dungeon.view.BattleInterface; 
import dungeon.view.ShopInterface; 
import dungeon.view.MenuInterface;
import dungeon.model.Player; 
import dungeon.model.*; 
import dungeon.view.*; 
import java.util.*; 

/**************************************************************************
*  Class: DungeoniestDungeonApp                                           *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 15/5/2020                                                *
*  Date Modified: 27/5/2020                                               *
*  Purpose: Starting point of entire app, provides overview of program    *
***************************************************************************/

public class DungeoniestDungeonApp
{

    private static final String DEFAULT_FILE_NAME = "shopData.txt";
    private static final String TEST_FILE_NAME1 = "emptyData.txt";
    private static final String TEST_FILE_NAME2 = "missingValuesData.txt"; 

    public static void main(String[] args)
    {
  	try
	{
	    Player player = new Player(); 
	    ItemFactory itemFactory = new ItemFactory(); 
	    EnchantmentFactory enchantmentFactory = new EnchantmentFactory(); 
	    ReaderFactory readerFactory = new ReaderFactory(); 
	    EnemyFactory enemyFactory = new EnemyFactory(); 
	    TurnFactory turnFactory = new TurnFactory(); 

	    BattleController battleController = new BattleController(player, enemyFactory, turnFactory);
	    ShopController shopController = new ShopController(player, enchantmentFactory); 


	    FileReader fileReader = null; 
	    fileReader = new LocalFileReader(shopController, itemFactory);  //Loads default reader which is local 

	    fileReader.loadShopData(DEFAULT_FILE_NAME); 

	    MenuController menuController = new MenuController(player, fileReader, readerFactory);

	    BattleInterface battleInterface = new BattleInterface(battleController);
	    ShopInterface shopInterface = new ShopInterface(shopController);   
	    MenuInterface menuInt = new MenuInterface(battleInterface, shopInterface, menuController); 

 	    menuInt.gameMenu(); 
	}
	catch(CanNotLoadShopDataException e1)
	{
	    //Thrown by FileReader if unable to read a given file 
	    System.err.println("ERROR - INVALID FILE READ: " + e1.getMessage() + "\n");   
	}
	catch(IllegalArgumentException e2)
	{
	    //Thrown whenever input creating object is inapproriate 
	    System.err.println("ERROR: - INVALID INPUT " + e2.getMessage());
	}
	catch(NullPointerException e3)
	{
	    System.err.println("ERROR: " + e3.getMessage());
	}
	catch(Exception e)
	{
	    //Last ditch catch all 
	    System.err.println("ERROR: " + e.getMessage() + "\n"); 
	}
    }
}
