package dungeon.controller; 

import dungeon.model.Item; 
import dungeon.model.Weapon;
import dungeon.model.Armour;  
import dungeon.model.Potion; 

/**************************************************************************
*  Class: EnchantmentFactory                                              *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Decides which Item subclass to return                         *
***************************************************************************/

public class ItemFactory
{
    //For testability 
    private static Item testItem = null;


    //For testability
   
    /**************************************************************************
    *  SUBMODULE: setTestItem                                                 *
    *  IMPORT: t1 (Item)                                                      *
    *  EXPORT: none                                                           *
    *  ASSERTION: Returns a test item for mocking purposes                    *
    ***************************************************************************/
  
    public void setTestItem(Item t1)
    {
        testItem = t1; 
    }

    /**************************************************************************
    *  SUBMODULE: makeItem                                                    *
    *  IMPORT: itemType (char), itemName (String), minEffect (int),           *
    *  maxEffect (int), cost (int), otherAttr (String[])                      *
    *  EXPORT: i (Item)                                                       *
    *  ASSERTION: Returns an item based on a provided set of attributes       *
    ***************************************************************************/

    public Item makeItem(char itemType, String itemName, int minEffect, int maxEffect, int cost, String[] otherAttr)
    {
	
	Item i = null; 

	//If testItem == null, means this factory isn't being mocked 
	if(testItem == null)
	{
	    if(itemType == 'W')
	    {
	        i = genWeapon(itemName, minEffect, maxEffect, cost, otherAttr); 	    
  	    }
	    else if(itemType == 'A')
	    {
		i = genArmour(itemName, minEffect, maxEffect, cost, otherAttr); 
    	    }
	    else if(itemType == 'P')
	    {
		i = genPotion(itemName, minEffect, maxEffect, cost, otherAttr); 
	
  	    }
	}
	else
	{
	    return testItem; 
	}
	
	return i; 
    }

   /***************************************************************************
    *  SUBMODULE: genWeapon                                                   *
    *  IMPORT: itemName (String), minEffect (int), maxEffect (int),           *
    *   cost (int), otherAttr (String[])                                      *
    *  EXPORT: item (Item)                                                    *
    *  ASSERTION: Return a subclass of item (weapon) to the user              *
    ***************************************************************************/
 
    private Item genWeapon(String itemName, int minEffect, int maxEffect, int cost, String[] otherAttr)
    {
	String dmgType;
	String weaponType;

	dmgType = otherAttr[0];
	weaponType = otherAttr[1];

	return new Weapon(itemName, cost, minEffect, maxEffect, weaponType, dmgType); 
    }


    /**************************************************************************
    *  SUBMODULE: genArmour                                                   *
    *  IMPORT: itemName (String), minEffect (int), maxEffect (int),           *
    *   cost (int), otherAttr (String[])                                      *
    *  EXPORT: item (Item)                                                    *
    *  ASSERTION: Return a subclass of item (armour) to the user              *
    ***************************************************************************/

    private Item genArmour(String itemName, int minEffect, int maxEffect, int cost, String[] otherAttr)
    {
        String material;
	
	material = otherAttr[0]; 
	
	return new Armour(itemName, cost, minEffect, maxEffect, material); 
    }

    /**************************************************************************
    *  SUBMODULE: genPotion                                                   *
    *  IMPORT: itemName (String), minEffect (int), maxEffect (int),           *
    *   cost (int), otherAttr (String[])                                      *
    *  EXPORT: item (Item)                                                    *
    *  ASSERTION: Return a subclass of item (potion) to the user              *
    ***************************************************************************/

    private Item genPotion(String itemName, int minEffect, int maxEffect, int cost, String[] otherAttr)
    {
        String potionType;
	
	potionType = otherAttr[0]; 
	
	return new Potion(itemName, cost, minEffect, maxEffect, potionType); 
    }
}
