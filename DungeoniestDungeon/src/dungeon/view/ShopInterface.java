package dungeon.view; 

import dungeon.controller.ShopController; 
import dungeon.view.UserInputUtil; 
import java.util.*; 

/**************************************************************************
*  Class: ShopInterface                                                   *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 26/5/2020                                               *
*  Purpose: Serves as the user interface for the shop system              *
***************************************************************************/

public class ShopInterface
{
    private Scanner sc = new Scanner(System.in); 
    private ShopController shopController; 

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inShopController (ShopController)                              *
    *  EXPORT: none                                                           *
    *  ASSERTION: Initalize a ShopInterface object with a valid               *
    *  ShopController                                                         *
    ***************************************************************************/	

    public ShopInterface(ShopController inShopController)
    {
	shopController = inShopController; 
    }   

    /**************************************************************************
    *  SUBMODULE: openShop                                                    *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: Runs the main UI for the shop                               *
    ***************************************************************************/

    public void openShop()
    {
        int selectedOption; 
	boolean running = true; 
	
	do
	{
	    System.out.println("");
	    System.out.println("-------------------------------------THE SHOP-------------------------------------");
	    System.out.println(" 1) Browse Basic Stock");
	    System.out.println(" 2) Browse Enchantments");
	    System.out.println(" 3) Sell Items");  
	    System.out.println(" 4) Back"); 

 	    selectedOption = UserInputUtil.userInput("Please select a valid shop option: ", 1,4); 

	    switch(selectedOption)
  	    {
	        case 1:
		    browseBasicStock(); 
		    break; 
		case 2:
		    browseEnchantments();
		    break;
		case 3:
		    sellItems();  
		case 4:
		    System.out.println("Going back..."); 
		    running = false;
		    break;  
		default:
		    running = false; 
		    break; 
            }
	}
	while(running); 
    }  

    /**************************************************************************
    *  SUBMODULE: displayPlayerStats                                          *
    *  IMPORT: stats (String)                                                 *
    *  EXPORT: none                                                           *
    *  ASSERTION: Prints out passed in statistics                             *
    ***************************************************************************/

    public void displayPlayerStats(String stats)
    {
	System.out.println(stats); 
    }

    /**************************************************************************
    *  SUBMODULE: browseBasicStock                                            *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: Runs sub-menu for browsing general store stock              *
    *  (non-enchantment items)                                                *
    ***************************************************************************/
	
    private void browseBasicStock()
    {
        int selectedOption, purchaseOption;
	String actionResult; 
	boolean running = true; 

	System.out.println(shopController.displayStock()); 

	do
	{
	   purchaseOption = UserInputUtil.userInput("\nWhich item would you like to buy? - 0 to Cancel",0,shopController.getShopListSize()); 	   	 
	   
	   if(purchaseOption > 0 && shopController.checkItemExists(purchaseOption))
	   {
 	       if(shopController.checkGold(purchaseOption, "basicStock")) //Check if you have enough gold to buy item 
	       {
		   selectedOption = UserInputUtil.userInput("Confirm purchase? 1) Yes, 2) No",1,2); 
		   if(selectedOption == 1)
		   {
		       actionResult = "You bought the " + shopController.getItem(purchaseOption).getName() + " for " + shopController.getItem(purchaseOption).getCost() + " gold!";
	               
 		       if(!shopController.purchaseItem(purchaseOption)) //Peforming actual purchase 
		       {
  	 	           System.out.println("Insufficient inventory space..."); 
		       }
	 	       else
		       {
		           System.out.println(actionResult); 		   
		       }
		   }
	       }
	       else
	       {
	           System.out.println("Insuffecient funds...."); 
	       }
	   }
	   else
	   {
	       running = false; 
	   }
        }
	while(running); 
    }

    /**************************************************************************
    *  SUBMODULE: sellItems                                                   *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: Provides UI for selling your inventory items                *
    ***************************************************************************/

    private void sellItems()
    {
        int selectedItem; 
	boolean running = true; 
	String actionResult; 
		
	do
	{
            System.out.println(shopController.obtainItems(""));  //Showing all player inventory items that are sellable 
	    selectedItem = UserInputUtil.userInput("\nWhich inventory item do you wish to sell? - 0 to Cancel",0,shopController.getPlayerInventorySize());
	    if(selectedItem > 0)
	    {
		actionResult = "You sold the " + shopController.getInventoryItem(selectedItem).getName() + " for " + shopController.getInventoryItem(selectedItem).getCost() + " gold!"; 
		
	        if(!shopController.sellItem(selectedItem)) //Selling item 
		{
		    System.out.println("Unable to sell that item!"); 
		}
		else
		{
		    System.out.println(actionResult);
		} 
	    }
	    else
	    {
	        running = false; 
	    }    	    
	}
	while(running);
    }

    /**************************************************************************
    *  SUBMODULE: browseEnchantments                                          *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: Runs sub-menu for browsing weapon enchantments              *
    ***************************************************************************/

    private void browseEnchantments()
    {
	int selectedWeapon, purchaseOption;
	String actionResult; 
	boolean running = true; 
     
        System.out.println(shopController.displayEnchantments()); 
       
   	do
	{
	    purchaseOption = UserInputUtil.userInput("\nWhich enchantment would you like to buy? - 0 to Cancel",0,shopController.getEnchantmentListSize()); 
	   
	    if(purchaseOption > 0 && shopController.checkEnchantmentExists(purchaseOption))
	    {
	        if(shopController.checkGold(purchaseOption,"enchantments")) //Echking if have enough money to buy enchantment 
	 	{
       		    System.out.println(shopController.obtainItems("W")); //Show all weapons you can apply an enchantment to 

		    if(shopController.getNumPlayerWeapons() > 0) 
		    {	
		        selectedWeapon = UserInputUtil.userInput("\nWhich of your weapons do your wish to apply the enchantment to?",1,shopController.getNumPlayerWeapons());

			actionResult = "You bought the " + shopController.getEnchantmentName(purchaseOption) + ", applied to " + shopController.getInventoryItem(selectedWeapon).getName() + " for " + shopController.getEnchantmentCost(purchaseOption) + " gold!";
			shopController.getEnchantmentName(purchaseOption); 
			
			if(!shopController.purchaseEnchantment(purchaseOption, selectedWeapon)) //Actual purchasing of enchantment 
			{
			    System.out.println("Unable to purchase item"); 
			}
			else
			{
			    System.out.println(actionResult); 
			} 
	  	    }
		    else
		    {
		        System.out.println("No weapons to enchant!"); 
			running = false;
	    	    }
		}
		else
		{
		    System.out.println("Insufficient funds...."); //Make into exception 
		}    
	    }  
	    else
	    {
	        running = false; 
	    } 
	}
	while(running); 
    }

}
