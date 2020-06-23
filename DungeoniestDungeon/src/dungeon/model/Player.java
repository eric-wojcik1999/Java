package dungeon.model; 

import dungeon.model.Item; 
import java.util.*; 

/**************************************************************************
*  Class: Player                                                          *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 25/5/2020                                               *
*  Purpose: Player model                                                  *
***************************************************************************/

public class Player
{
    private String name; 
    private int maxHP; 
    private int currHP; 
    private int gold; 
    private ArrayList<Item> inventory; 
    private Item equippedWeapon; 
    private Item equippedArmour;
    private boolean hasEquippedWeapon; 
    private boolean hasEquippedArmour;
    private int weaponListSize;  
    private int potionListSize;  
    private int armourListSize; 
    private boolean isAlive = true;  

    //CONSTRUCTORS

    /**************************************************************************
    *  SUBMODULE: DEFAULT CONSTRUCTOR                                         *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: An Player object initialised with default values             *
    ***************************************************************************/ 

    public Player()
    {
	name = "Burger 'Poor Man' Pimblan III";
	maxHP = 25; 
 	currHP = maxHP; 
 	gold = 100; 
	inventory = new ArrayList<Item>();
	Item startingWeapon = new Weapon("Peasant Butterknife", 12, 2, 5, "Short Knife", "Blunt"); 
	Item startingArmour = new Armour("Peasant’s Plate", 12, 4, 12, "Paper"); 
	addInventoryItem(startingWeapon);
	addInventoryItem(startingArmour);       
	hasEquippedWeapon = false;
	hasEquippedArmour = false;
    }

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inName (String), inMaxHP (int), inCurrHP (int), inGold (int)   *
    *  EXPORT: none                                                           *
    *  ASSERTION: A Player object initialised with valid parameters           *
    ***************************************************************************/

    //Alternate constructor 
    public Player(String inName, int inMaxHP, int inCurrHP, int inGold)
    {
	setName(inName); 
	setMaxHP(inMaxHP);
	setCurrHP(inCurrHP); 
	setGold(inGold); 
	inventory = new ArrayList<Item>();
	hasEquippedWeapon = false;
	hasEquippedArmour = false; 
    }

    //ACCESSORS

    /**************************************************************************
    *  SUBMODULE: getName                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: name (String)                                                  *
    *  ASSERTION: Return a name value                                         *
    ***************************************************************************/

    public String getName()
    {
	return name; 
    }

    /**************************************************************************
    *  SUBMODULE: getMaxHP                                                    *
    *  IMPORT: none                                                           *
    *  EXPORT: maxHP (int)                                                    *
    *  ASSERTION: Return a maxHP value                                        *
    ***************************************************************************/

    public int getMaxHP()
    {
	return maxHP;
    }

    /**************************************************************************
    *  SUBMODULE: getCurrHP                                                   *
    *  IMPORT: none                                                           *
    *  EXPORT: currHP (int)                                                   *
    *  ASSERTION: Return a currHP value                                       *
    ***************************************************************************/

    public int getCurrHP()
    {
	return currHP; 
    }

    /**************************************************************************
    *  SUBMODULE: getGold                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: gold (int)                                                     * 
    *  ASSERTION: Return a gold value                                         *
    ***************************************************************************/

    public int getGold()
    {
	return gold; 
    }

    /**************************************************************************
    *  SUBMODULE: getInventoryItem                                            *
    *  IMPORT: i (int)                                                        *
    *  EXPORT: item (Item)                                                    * 
    *  ASSERTION: Return an item from your inventory based on a provided      *
    *  index                                                                  *
    ***************************************************************************/

    public Item getInventoryItem(int i)
    {
	return inventory.get(i); 
    }

    /**************************************************************************
    *  SUBMODULE: getAllInventory                                             *
    *  IMPORT: none                                                           *
    *  EXPORT: inventory (ArrayList<Item>)                                    * 
    *  ASSERTION: Return contents of entire inventory                         *
    *  index                                                                  *
    ***************************************************************************/

    public ArrayList<Item> getAllInventory()
    {
	return inventory; 
    }

    /**************************************************************************
    *  SUBMODULE: getWeapon                                                   *
    *  IMPORT: selectedWeapon (int)                                           *
    *  EXPORT: weapon (Item)                                                  * 
    *  ASSERTION: Return a weapon object based on the imported index          *
    ***************************************************************************/

    //Used by shop to get enchantment applied to specific weapon in inventory 
    //Usually input is ‘gimme 2nd weapon in list’ hence why index increases for every instance of weapon 

    public Item getWeapon(int selectedWeapon)
    {
	Item weapon = null;

	int index = 0; 	

	for(Item i : inventory)
	{
	    if(i.getItemType().equals("W"))
	    {
	       index++; 

	       if(index == selectedWeapon)
	       { 
	           return i;   
	       }		
    	    }
	}
	return weapon;  
    }

    /**************************************************************************
    *  SUBMODULE: getArmour                                                   *
    *  IMPORT: selectedArmour (int)                                           *
    *  EXPORT: armour (Item)                                                  * 
    *  ASSERTION: Return an armour object based on the imported index         *
    ***************************************************************************/

    public Item getArmour(int selectedArmour)
    {
	Item armour = null;

	int index = 0; 	

	for(Item i : inventory)
	{
	    if(i.getItemType().equals("A"))
	    {
	       index++; 

	       if(index == selectedArmour)
	       { 
	           return i;   
	       }		
    	    }
	}
	return armour;  
    }

    /**************************************************************************
    *  SUBMODULE: getAllWeapons                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: weapons (ArrayList<Item>)                                      * 
    *  ASSERTION: Returns a list of weapons in your inventory                 *
    ***************************************************************************/

    public ArrayList<Item> getAllWeapons()
    {
	ArrayList<Item> weapons = new ArrayList<Item>(); 

	for(Item i : inventory)
	{
	    if(i.getItemType().equals("W"))
	    {
	        weapons.add(i); 
    	    }
	}
	weaponListSize = weapons.size(); 
	return weapons; 
    }

    /**************************************************************************
    *  SUBMODULE: getAllPotions                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: potions (ArrayList<Item>)                                      * 
    *  ASSERTION: Returns a list of potions in your inventory                 *
    ***************************************************************************/

    public ArrayList<Item> getPotions()
    {
        ArrayList<Item> potions = new ArrayList<Item>();
	
	for(Item i: inventory)
	{
	    if(i.getItemType().equals("P"))
	    {
	        potions.add(i); 
	    }
	}
	potionListSize = potions.size(); 
	return potions;
    }

    /**************************************************************************
    *  SUBMODULE: getAllArmours                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: armours (ArrayList<Item>)                                      * 
    *  ASSERTION: Returns a list of armours in your inventory                 *
    ***************************************************************************/

    public ArrayList<Item> getAllArmours()
    {
        ArrayList<Item> armours = new ArrayList<Item>();
	
	for(Item i: inventory)
	{
	    if(i.getItemType().equals("A"))
	    {
	        armours.add(i); 
	    }
	}
	armourListSize = armours.size(); 
	return armours;
    }

    /**************************************************************************
    *  SUBMODULE: getEquippedItem                                             *
    *  IMPORT: none                                                           *
    *  EXPORT: returnEquipment (Item)                                         * 
    *  ASSERTION: Returns your currently equipped weapon/armour depending on  *
    *  import parameter                                                       *
    ***************************************************************************/

    public Item getEquippedItem(String itemType)
    {
	Item returnEquipment = null; 

        if(itemType.equals("W"))
	{
	    if(hasEquippedWeapon)
	    {
	        returnEquipment = equippedWeapon; 
	    }
	    else
	    {
                System.out.println("No weapon currently equipped"); 
            }
	}
	else if(itemType.equals("A"))
	{	    
	    if(hasEquippedArmour)
	    {
	        returnEquipment = equippedArmour; 
	    }
  	    else
	    {
	        System.out.println("No armour currently equipped"); 
 	    }
	}
	else
	{
	   throw new IllegalArgumentException("Equipment doesn’t exist"); 
	}
	return returnEquipment; 
    }

    /**************************************************************************
    *  SUBMODULE: getNumWeapons                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: weaponListSize                                                 * 
    *  ASSERTION: Returns the number of items catergorised as weapons in your *
    *  inventory                                                              *
    ***************************************************************************/

    public int getNumWeapons()
    {
	return weaponListSize; 
    }

    /**************************************************************************
    *  SUBMODULE: getNumPotions                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: potionListSize                                                 * 
    *  ASSERTION: Returns the number of items catergorised as potions in your *
    *  inventory                                                              *
    ***************************************************************************/

    public int getNumPotions()
    {
	return potionListSize; 
    }

    /**************************************************************************
    *  SUBMODULE: getNumArmour                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: armourListSize                                                 * 
    *  ASSERTION: Returns the number of items catergorised as armour in your  *
    *  inventory                                                              *
    ***************************************************************************/

    public int getNumArmours()
    {
	return armourListSize; 
    }

    /**************************************************************************
    *  SUBMODULE: getIsAlive                                                  *
    *  IMPORT: none                                                           *
    *  EXPORT: isAlive (boolean)                                              * 
    *  ASSERTION: Returns value specifying if player is alive                 *
    ***************************************************************************/

    public boolean getIsAlive()
    {
	return isAlive; 
    }

    //MUTATORS

    /**************************************************************************
    *  SUBMODULE: setName                                                     *
    *  IMPORT: inName (String)                                                *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets a valid name value                                     *
    ***************************************************************************/
 
    public void setName(String inName)
    {
        if ((validateString(inName)))
  	{
	    name = inName; 
	}
	else
	{
	    throw new IllegalArgumentException("Error: Name is null"); 
	}
    }

    /**************************************************************************
    *  SUBMODULE: setMaxHP                                                    *
    *  IMPORT: inMaxHP (int)                                                  *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets a valid maxHP value                                    *
    ***************************************************************************/
   
    public void setMaxHP(int inMaxHP)
    {
	if ((validateInt(inMaxHP)))
  	{
	    maxHP = inMaxHP; 
	}
	else
	{
	    throw new IllegalArgumentException("Error: Max HP cannot be less than 0"); 
	}
    }

    /**************************************************************************
    *  SUBMODULE: setCurrHP                                                   *
    *  IMPORT: inCurrHP (int)                                                 *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets a valid currHP value                                   *
    ***************************************************************************/

    public void setCurrHP(int inCurrHP)
    {
	if ((validateInt(inCurrHP)))
  	{
	    if(validateHP(inCurrHP))
	    {
	        currHP = inCurrHP;
		
		if(currHP == 0)
		{
		   isAlive = false;
		}
 
	    }
	    else
	    {
	        currHP = maxHP; 
	    }
	}
	else
	{
	     throw new IllegalArgumentException("Error: Current HP cannot be less than 0"); 
	}
    }

    /**************************************************************************
    *  SUBMODULE: addInventoryItem                                            *
    *  IMPORT: i (Item)                                                       *
    *  EXPORT: itemAdded (boolean)                                            *
    *  ASSERTION: Adds imported item yo your inventory and returns true if    *
    *  successful                                                             *
    ***************************************************************************/

    public boolean addInventoryItem(Item i)
    {

	boolean itemAdded = false; 	

	if(validateItemAddition()) 
 	{
            inventory.add(i);
	    itemAdded = true; 
	}
	else
	{
	    System.out.println("Inventory full!"); 
	} 
	return itemAdded; 
    }

    public boolean removeInventoryItem(Item i) 
    {

	boolean itemRemoved = false; 	

	if(validateItemRemoval(i))
	{
            inventory.remove(i);
	    itemRemoved = true;
	    
	    if(checkIfEquipped(i))
	    {
		removeEquipment(i.getItemType()); 
 	    }
	}
	else
	{
	    System.out.println("Item does not exist in inventory!"); 
	}  
	
	return itemRemoved; 
    }

    /**************************************************************************
    *  SUBMODULE: checkIfEquipped                                             *
    *  IMPORT: i (Item)                                                       *
    *  EXPORT: itemIsEquipped (boolean)                                       *
    *  ASSERTION: Check if the imported item is the same as the equipped item *
    ***************************************************************************/

    public boolean checkIfEquipped(Item i)
    {
	boolean itemIsEquipped = false; 

        if(i.getItemType().equals("W"))
	{
	    if(i.equal(getEquippedItem("W")))
	    {
 	 	itemIsEquipped = true; 
	    } 
	}
	else if(i.getItemType().equals("A"))
	{
	    if(i.equal(getEquippedItem("A")))
	    {
 	 	itemIsEquipped = true; 
	    } 
	}
	return itemIsEquipped; 
    }

    /**************************************************************************
    *  SUBMODULE: checkIfEquipped                                             *
    *  IMPORT: itemType (String)                                              *
    *  EXPORT: itemAdded (boolean)                                            *
    *  ASSERTION: Checks if an equipped item of a certain type is present     *
    ***************************************************************************/

    public boolean checkIfEquipped(String itemType)
    {
	boolean itemIsEquipped = false; 

        if(itemType.equals("W"))
	{
	    itemIsEquipped = hasEquippedWeapon; 
	}
	else if(itemType.equals("A"))
	{
	    itemIsEquipped = hasEquippedArmour; 
	}
	
	return itemIsEquipped; 

    }

    /**************************************************************************
    *  SUBMODULE: setEquippedItem                                             *
    *  IMPORT: i (Item)                                                       *
    *  EXPORT: none                                                           *
    *  ASSERTION: Equips the imported item as weapon or armour depending on   *
    *  the type of item imported                                              *
    ***************************************************************************/

    public void setEquippedItem(Item i)
    {
 	if(i.getItemType().equals("W"))
	{
	    equippedWeapon = i; 
	    hasEquippedWeapon = true; 
	}
	else if(i.getItemType().equals("A"))
	{
	    equippedArmour = i;
	    hasEquippedArmour = true; 
	}
	else
	{
	    System.out.println("Could not equip that item"); 
	}
    }

    /**************************************************************************
    *  SUBMODULE: updateEquipment                                             *
    *  IMPORT: i (Item)                                                       *
    *  EXPORT: none                                                           *
    *  ASSERTION: Equipped item is updated to state of version of imported    *
    *  item                                                                   *
    ***************************************************************************/

    public void updateEquipment(Item i)
    {
        if(i.getItemType().equals("W"))
	{
	    equippedWeapon = i; 
	}
	else if(i.getItemType().equals("A"))
	{	    
	    equippedArmour = i; 
	}
	else
	{
	   throw new IllegalArgumentException("Equipment doesn’t exist"); 
	}
    }  

    /**************************************************************************
    *  SUBMODULE: removeEquipment                                             *
    *  IMPORT: itemType (String)                                              *
    *  EXPORT: none                                                           *
    *  ASSERTION: Unequips an armour or weapon depending on the import string *
    ***************************************************************************/

    public void removeEquipment(String itemType)
    {
        if(itemType.equals("W"))
	{
	    equippedWeapon = null;
	    hasEquippedWeapon = false;  
	}
	else if(itemType.equals("A"))
	{	    
	    equippedArmour = null; 
	    hasEquippedArmour = false; 
	}
	else
	{
	   throw new IllegalArgumentException("Equipment doesn’t exist"); 
	}
    }

    /**************************************************************************
    *  SUBMODULE: updateInventory                                             *
    *  IMPORT: i (Item)                                                       *
    *  EXPORT: none                                                           *
    *  ASSERTION: Updates an inventory item with details from the new         *
    *  imported item                                                          *
    ***************************************************************************/

    public void updateInventory(String itemName, Item oldItem, Item newItem)
    {
	removeInventoryItem(oldItem);
	addInventoryItem(newItem); 
    }   

    /**************************************************************************
    *  SUBMODULE: setGold                                                     *
    *  IMPORT: inGold (int)                                                   *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets a valid gold value                                     *
    ***************************************************************************/
    
    public void setGold(int inGold)
    {
	if ((validateInt(inGold)))
  	{
	    gold = inGold; 
	}
	else
	{
	    throw new IllegalArgumentException("Error: Gold cannot be less than 0"); 
	}
    }

    /**************************************************************************
    *  SUBMODULE: resetPlayerState                                            *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: Reverts player state back to default (usually after         *
    *  game loss)                                                             *
    ***************************************************************************/

    public void resetPlayerState() 
    {
	maxHP = 25; 
 	currHP = maxHP; 
 	gold = 100; 
	inventory.clear();
	Item startingWeapon = new Weapon("Peasant Butterknife", 12, 2, 5, "Short Knife", "Blunt"); 
	Item startingArmour = new Armour("Peasant’s Plate", 12, 4, 12, "Paper"); 
	addInventoryItem(startingWeapon);
	addInventoryItem(startingArmour); 
	equippedWeapon = null;	
	equippedArmour = null;       
	hasEquippedWeapon = false;
	hasEquippedArmour = false;
	isAlive = true; 
    }

    /**************************************************************************
    *  SUBMODULE: toString                                                    *
    *  IMPORT: none                                                           *
    *  EXPORT: result (String)                                                *
    *  ASSERTION: Returns a transcript of the player's state, including all   *
    *  of their inventory items                                               *
    ***************************************************************************/

    @Override
    public String toString()
    {
	String equippedWeaponStr = "N/A", equippedArmourStr = "N/A"; 
	String result = "\n---------------------------------PLAYER STATISTICS--------------------------------"; 
	result += "\nPlayer Name: " + name; 
	result += "\nHP: " + currHP + "/" + maxHP; 
	result += "\nGold: " + gold; 

	if(hasEquippedWeapon)
	{
	    equippedWeaponStr = equippedWeapon.getName();
	}
	
 	if(hasEquippedArmour)
	{
	    equippedArmourStr = equippedArmour.getName(); 
	}

	result += "\nEquipped Weapon: " + equippedWeaponStr; 
	result += "\nEquipped Armour: " + equippedArmourStr; 

	result += "\nInventory: "; 

	for(Item i : inventory)
	{
	    result += "\n-" + i.getName();
	} 	

	result += "\n";

	return result; 
    }

    /**************************************************************************
    *  SUBMODULE: toStringAbridged                                            *
    *  IMPORT: none                                                           *
    *  EXPORT: result (String)                                                *
    *  ASSERTION: Returns a transcript of the player's state, excluding all   *
    *  of their inventory items                                               *
    ***************************************************************************/

    public String toStringAbridged()
    {
	String equippedWeaponStr = "N/A", equippedArmourStr = "N/A"; 
	String result = "\n---------------------------------PLAYER STATISTICS--------------------------------"; 
	result += "\nPlayer Name: " + name; 
	result += "\nHP: " + currHP + "/" + maxHP; 
	result += "\nGold: " + gold; 

	if(hasEquippedWeapon)
	{
	    equippedWeaponStr = equippedWeapon.getName();
	}
	
 	if(hasEquippedArmour)
	{
	    equippedArmourStr = equippedArmour.getName(); 
	}

	result += "\nEquipped Weapon: " + equippedWeaponStr; 
	result += "\nEquipped Armour: " + equippedArmourStr; 

	result += "\n";

	return result; 
    }

    /**************************************************************************
    *  SUBMODULE: displayItems                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: result (String)                                                *
    *  ASSERTION: Returns a list of weapons/potions/armour in the players     *
    *  inventory, given the type of item imported                             *
    ***************************************************************************/

    public String displayItems(String itemType)
    {
	String result = ""; 
	ArrayList<Item> list; 
	int index = 0; 

	if(itemType.equals("W"))
	{
	    list = getAllWeapons();
	    if(!list.isEmpty())
	    {
	        for(Item i : list)
	        {
 		    index++; 
	 	    result += "[" + index + "] - " +i.getName() + " (Gold: " + i.getCost() + ") (DMG: " + i.use()  + ")\n"; 
	        }
	    }   
	}
	else if(itemType.equals("P"))
	{
	    list = getPotions(); 
	    if(!list.isEmpty())
	    {
	        for(Item i : list)
	        {
		    Potion p = (Potion) i;
 		    index++; 
	 	    result += "[" + index + "] - " +p.getName() + " (Gold: " + p.getCost() + ") (EFFECT: " + i.use() +  " Potion Type: " + p.getPotionType() + ")\n"; 
	        }
	    }     
	}
	else if(itemType.equals("A"))
	{
	    list = getAllArmours(); 
	    
	    if(!inventory.isEmpty())
	    {
	        for(Item i : list)
	        {
 		    index++; 
	 	    result += "[" + index + "] - " +i.getName() + " (Gold: " + i.getCost() + ") (PROTECTION: " + i.use()  + ")\n"; 
	        }
	    }     
	}
	else if(itemType.equals(""))
	{
	    
	    if(!inventory.isEmpty())
	    {
	        for(Item i : inventory)
	        {
 		    index++; 
	 	    result += "[" + index + "] - " +i.getName() + " (Gold: " + i.getCost() + ")\n"; 
	        }
	    }     
	}
	
	return result; 
    }
    

    //PRIVATE VALIDATORS 
    
    /**************************************************************************
    *  SUBMODULE: validateString                                              *
    *  IMPORT: inString (String)                                              *
    *  EXPORT: validString (boolean)                                          *
    *  ASSERTION: Check if incoming string is valid                           *
    ***************************************************************************/

    private boolean validateString(String inString)
    {
	return (!("".equalsIgnoreCase(inString))); 
    }

    
    /**************************************************************************
    *  SUBMODULE: validateInt                                                 *
    *  IMPORT: inInt (int)                                                    *
    *  EXPORT: validInt (boolean)                                             *
    *  ASSERTION: Check if incoming int is valid                              *
    ***************************************************************************/

    private boolean validateInt(int inInt)
    {
	return (inInt >= 0);   
    }

    /**************************************************************************
    *  SUBMODULE: validateHP                                                  *
    *  IMPORT: inHP (int)                                                     *
    *  EXPORT: validHP (boolean)                                              *
    *  ASSERTION: Check if incoming HP is <= maxHP                            *
    ***************************************************************************/

    private boolean validateHP(int inHP)
    {
	return (inHP <= maxHP); 
    }

    /**************************************************************************
    *  SUBMODULE: validateItemAddition                                        *
    *  IMPORT: none                                                           *
    *  EXPORT: validItemAddition (boolean)                                    *
    *  ASSERTION: Returns true if size of inventory isn't exceeded            *
    ***************************************************************************/

    private boolean validateItemAddition()
    {
	return inventory.size() < 15; 
    }

    /**************************************************************************
    *  SUBMODULE: validateItemRemoval                                         *
    *  IMPORT: i (Item)                                                       *
    *  EXPORT: validRemoval (boolean)                                         *
    *  ASSERTION: Check if imported item is present in inventory before       *
    *  removal                                                                *
    ***************************************************************************/

    private boolean validateItemRemoval(Item i)
    {
	return inventory.contains(i); 
    }
}
