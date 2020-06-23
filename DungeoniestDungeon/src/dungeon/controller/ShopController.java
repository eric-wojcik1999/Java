package dungeon.controller; 

import dungeon.controller.EnchantmentFactory; 
import dungeon.model.Player; 
import dungeon.model.Item; 
import java.util.*; 

/**************************************************************************
*  Class: ShopController                                                  *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Serves as a thin layer between the ShopInterface and the      *
*  model classes it uses.                                                 *
***************************************************************************/

public class ShopController
{
 
    private HashMap<Integer,Item> shopItems;  
    private HashMap<Integer,String> enchantments; 
    private int itemIndex; 
    private Player player;
    private EnchantmentFactory enchantmentFactory; 

    //CONSTRUCTORS 

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inPlayer (Player), inEnchantmentFactory (EnchantmentFactory)   *
    *  EXPORT: none                                                           *
    *  ASSERTION: A ShopController initialised with a valid player, and a     *
    *  valid enchantment factory                                              *
    ***************************************************************************/

    public ShopController(Player inPlayer, EnchantmentFactory inEnchantmentFactory)
    {
        shopItems = new HashMap<Integer,Item>();
	enchantments = new HashMap<Integer,String>(); 
	populateEnchantments(); 
	itemIndex = 1;  
	player = inPlayer;
	enchantmentFactory = inEnchantmentFactory;  
    }

    //ACCESSORS 

    /**************************************************************************
    *  SUBMODULE: getItemIndex                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: itemIndex (int)                                                *
    *  ASSERTION: Return the index of the latest shop item                    *
    ***************************************************************************/

    public int getItemIndex()
    {
	return itemIndex; 
    }

    /**************************************************************************
    *  SUBMODULE: getItem                                                     *
    *  IMPORT: i (int)                                                        *
    *  EXPORT: shopItem (Item)                                                *
    *  ASSERTION: Return a shop item based off a passed in index              *
    ***************************************************************************/
 
    public Item getItem(int i)
    {
	return shopItems.get((Integer)i); 
    }

    /**************************************************************************
    *  SUBMODULE: getInvetoryItem                                             *
    *  IMPORT: i (int)                                                        *
    *  EXPORT: inventoryItem (Item)                                           *
    *  ASSERTION: Return an inventory item based off a passed in index        *
    ***************************************************************************/

    public Item getInventoryItem(int i)
    {
	return player.getInventoryItem((Integer)(i-1)); 
    }

    /**************************************************************************
    *  SUBMODULE: getShopListSize                                             *
    *  IMPORT: none                                                           *
    *  EXPORT: shopListSize (int)                                             *
    *  ASSERTION: Return the size in length of the shop list                  *
    ***************************************************************************/

    public int getShopListSize()
    {
	return shopItems.size(); 
    }

    /**************************************************************************
    *  SUBMODULE: getEnchantmentListSize                                      *
    *  IMPORT: none                                                           *
    *  EXPORT: shopEnchantmentSize (int)                                      *
    *  ASSERTION: Return the size in length of the enchantment list           *
    ***************************************************************************/

    public int getEnchantmentListSize()
    {
	return enchantments.size(); 
    }

    /**************************************************************************
    *  SUBMODULE: getPlayerInventorySize                                      *
    *  IMPORT: none                                                           *
    *  EXPORT: playerInventorySize (int)                                      *
    *  ASSERTION: Return the size in length of the playeri inventory list     *
    ***************************************************************************/

    public int getPlayerInventorySize()
    {
	return player.getAllInventory().size(); 
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
    *  SUBMODULE: getEnchantmentCost                                          *
    *  IMPORT: selectedOption (int)                                           *
    *  EXPORT: enchantmentCost (int)                                          *
    *  ASSERTION: Returns the cost of the specified enchantment in the        *
    *  enchantment list                                                       *
    ***************************************************************************/

    public int getEnchantmentCost(int selectedOption)
    {
    	//Splits the string value of the enchantment map via every instance of 'COST', thus 
    	//The second element will always be the value of cost 
        String[] parts = enchantments.get((Integer)selectedOption).split("COST:"); 

	return Integer.parseInt(parts[1].trim());
    }

    /**************************************************************************
    *  SUBMODULE: getEnchantmentName                                          *
    *  IMPORT: selectedOption (int)                                           *
    *  EXPORT: enchantmentName (String)                                       *
    *  ASSERTION: Returns the name of the specified enchantment               *
    ***************************************************************************/

    public String getEnchantmentName(int selectedOption)
    {
    //Splits enchanment map string value up by every instance of '|' 
	String[] parts = enchantments.get((Integer)selectedOption).split("\\|"); 
	return parts[0].trim();
    } 

    /**************************************************************************
    *  SUBMODULE: displayStock                                                *
    *  IMPORT: none)                                                          *
    *  EXPORT: result (String)                                                *
    *  ASSERTION: Returns a transcript of all the shop itemss at the shop     *
    ***************************************************************************/

    public String displayStock()
    {
	String result = "------------------------------------BASIC STOCK-----------------------------------"; 

	for(Map.Entry<Integer,Item> entry : shopItems.entrySet())
	{
	    //Return this as a string 
	    result += "\n[" + entry.getKey() + "] - " + entry.getValue().toString(); 
	}
	return result; 
    }

    /**************************************************************************
    *  SUBMODULE: displayEnchantments                                         *
    *  IMPORT: none                                                          *
    *  EXPORT: result (String)                                                *
    *  ASSERTION: Returns a transcript of all the enchantments at the shop    *
    ***************************************************************************/
 
    public String displayEnchantments() 
    {
	String result = "-----------------------------------ENCHANTMENTS-----------------------------------"; 


	for(Map.Entry<Integer,String> entry: enchantments.entrySet())
	{
	    //Return this as a string to interface 
	    result += "\n[" + entry.getKey() + "] - " + entry.getValue(); 
	}
	return result;  	
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
	    results = "------------------------------YOUR INVENTORY WEAPONS------------------------------"; 
	    results += "\n" + player.displayItems(itemType);
	}
	else if(itemType.equals("P"))
	{
	    results = "------------------------------YOUR INVENTORY POTIONS------------------------------"; 
	    results += "\n" + player.displayItems(itemType);
	}
	else if(itemType.equals(""))
	{
	    results = "-------------------------------YOUR INVENTORY ITEMS-------------------------------"; 
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
    *  SUBMODULE: addShopItem                                                 *
    *  IMPORT: i (Item)                                                       *
    *  EXPORT: void                                                           *
    *  ASSERTION: Assigns the imported item into the shop list                *
    ***************************************************************************/

    public void addShopItem(Item i)
    {
	if(!checkItemExists(i))
	{
	  shopItems.put((Integer)itemIndex, i); 
	  itemIndex++; 
   	}
    }

     /**************************************************************************
    *  SUBMODULE: removeShopItem                                               *
    *  IMPORT: inItemIndex (int), i (Item)                                     *
    *  EXPORT: none                                                            *
    *  ASSERTION: Removes the imported item from the shop list                 *
    ***************************************************************************/

    public void removeShopItem(int inItemIndex, Item i)
    {
	shopItems.remove((Integer)itemIndex, i);
	itemIndex--; 
    }

     /**************************************************************************
    *  SUBMODULE: populateEnchantments                                         *
    *  IMPORT: none                                                            *
    *  EXPORT: none                                                            *
    *  ASSERTION: Populates enchantment list with unique enchantments          *
    ***************************************************************************/
    private void populateEnchantments()
    {
	enchantments.put((Integer)1,"Basic Weapon DMG Enchantment | EFFECT: +2 dmg | COST: 5");
	enchantments.put((Integer)2,"Extra Weapon DMG Enchantment | EFFECT: +5 dmg | COST: 10"); 
	enchantments.put((Integer)3,"Fire Weapon DMG Enchantment | EFFECT: +5-10 dmg | COST: 20");
	enchantments.put((Integer)4,"Power-Up Weapon DMG Enchantment | EFFECT: dmg * 1.1 | COST: 10");  
    }

    //ACTIONS

    /**************************************************************************
    *  SUBMODULE: sellItem                                                    *
    *  IMPORT: selectedOption (int)                                           *
    *  EXPORT: itemSold (boolean)                                             *
    *  ASSERTION: Sell the selected item in your inventory and update th      * 
    *  results                                                                *
    ***************************************************************************/

    public boolean sellItem(int selectedOption)
    {
	boolean itemSold = false;
	int gold; 
	Item i = player.getInventoryItem((Integer)(selectedOption-1));
	gold = i.getCost();

	if(player.removeInventoryItem(i))
	{
	    player.setGold(player.getGold() + gold); 
	    itemSold = true;          	
	}
	
	return itemSold;
    }

    /**************************************************************************
    *  SUBMODULE: purchaseItem                                                *
    *  IMPORT: selectedOption (int)                                           *
    *  EXPORT: itemPurchased (boolean)                                        *
    *  ASSERTION: Purchase the selected item, adding it to your inventory     *
    ***************************************************************************/

    public boolean purchaseItem(int selectedOption)
    { 
	boolean itemPurchased = false; 

        Item i = shopItems.get((Integer)selectedOption);

	if(player.addInventoryItem(i))
	{ 
	    player.setGold(player.getGold() - i.getCost()); 
	    itemPurchased = true; 
	}
	return itemPurchased;
    }

    /**************************************************************************
    *  SUBMODULE: purchaseEnchantment                                         *
    *  IMPORT: selectedOption (int), selectedWeapon (int)                     *
    *  EXPORT: enchantmentPurchase (boolean)                                  *
    *  ASSERTION: Purchase the selected enchantment and add to inventory      *                                                    
    ***************************************************************************/

    public boolean purchaseEnchantment(int selectedOption, int selectedWeapon) 
    {
	boolean enchantmentPurchased = false; 
	int itemCost = 0; 	

	Item weapon = player.getWeapon(selectedWeapon); 
	Item oldWeapon = weapon; 
	itemCost = getEnchantmentCost(selectedOption); 
	
	weapon = enchantmentFactory.makeEnchantment(selectedOption, weapon); 
	
	//Update item in inventory 
	player.updateInventory(weapon.getName(), oldWeapon, weapon); 

	//Minus the money 
	player.setGold(player.getGold() - itemCost); 

	enchantmentPurchased = true; 
	
	return enchantmentPurchased; 
    }

    /**************************************************************************
    *  SUBMODULE: checkItemExists                                             *
    *  IMPORT: selectedOption (int)                                           *
    *  EXPORT: itemExists (boolean)                                           *
    *  ASSERTION: Returns true of item passed into the method exists in the   *
    *  shop array                                                             *
    ***************************************************************************/

    //Check if item object passed in is already in array
    private boolean checkItemExists(Item i) 
    {
	boolean itemExists = false; 

	for(Item existingItem : shopItems.values())
  	{
	    if(existingItem.equal((Object)i)) 
	    {
	        System.out.println("ITEM ALREADY EXISTS IN SHOP!");
		itemExists = true; 		 
	    }
	}
   	return itemExists; 
    }

     /**************************************************************************
    *  SUBMODULE: checkItemExists                                              *
    *  IMPORT: purchaseOption (int)                                            *
    *  EXPORT: itemExists (boolean)                                            *
    *  ASSERTION: Returns true of the item exists in the shop items list       *
    ***************************************************************************/

    //Checks if item exists by key 
    public boolean checkItemExists(int purchaseOption)
    {
	return shopItems.containsKey((Integer)purchaseOption); 
    }

    /**************************************************************************
    *  SUBMODULE: checkEnchantmentExists                                      *
    *  IMPORT: purchaseOption (int)                                           *
    *  EXPORT: enchantmentExist (boolean)                                     *
    *  ASSERTION: Return true if the enchantment exists in the enchantment    *
    *  list                                                                   *
    ***************************************************************************/

    public boolean checkEnchantmentExists(int purchaseOption)
    {
	return enchantments.containsKey((Integer)purchaseOption); 
    }

    /**************************************************************************
    *  SUBMODULE: sellItem                                                    *
    *  IMPORT: selectedOption (int), browseType (sSring)                      *
    *  EXPORT: afford (boolean)                                               *
    *  ASSERTION: Returns truee if player is able to afford item/enchantment  *
    ***************************************************************************/

    //Checks gold against a selected item for purchase
    public boolean checkGold(int selectedOption, String browseType)
    {
	int itemCost = 0;
	if(browseType.equals("basicStock"))
	{
	    itemCost = shopItems.get((Integer)selectedOption).getCost(); 
	}
	else if(browseType.equals("enchantments"))
	{
	    itemCost = getEnchantmentCost((Integer)selectedOption); 
	}
	int goldBalance = player.getGold() - itemCost;
    	return (goldBalance > 0); 
    }

}
