package dungeon.controller; 

import dungeon.model.Enchantment; 
import dungeon.model.EnchantmentBasicDmg; 
import dungeon.model.EnchantmentBigDmg; 
import dungeon.model.EnchantmentFireDmg; 
import dungeon.model.EnchantmentPowerUp; 
import dungeon.model.Item; 

/**************************************************************************
*  Class: EnchantmentFactory                                              *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 15/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Decides which encantment subclass to apply to a given item    *
***************************************************************************/

public class EnchantmentFactory
{
    //For testability
    private static Enchantment testEnchantment = null; 
	
    //For testability

    /**************************************************************************
    *  SUBMODULE: setTestEnchantment                                          *
    *  IMPORT: t1 (Enchantment)                                               *
    *  EXPORT: none                                                           *
    *  ASSERTION: Returns a test enchantment for mocking purposes             *
    ***************************************************************************/

    public void setTestEnchantment(Enchantment t1)
    {
	testEnchantment = t1; 
    }

    /**************************************************************************
    *  SUBMODULE: makeEnchantment                                             *
    *  IMPORT: selectedOption (int), item (Item)                              *
    *  EXPORT: e (Enchantment)                                                *
    *  ASSERTION: Returns an item encapsualted in some sub class of           *
    *  Enchantment based on provided string                                   *
    ***************************************************************************/

    public Enchantment makeEnchantment(int selectedOption, Item item)
    {
	Enchantment e = null; 

	//If testEnchantment == null, means factory isn't being mocked
	if(testEnchantment == null)
	{
	    if(selectedOption == 1)
	    {
	        e = genBasicDmg(item);
	    }
	    else if(selectedOption == 2)
	    {
		e = genBigDmg(item);
	    }
	    else if(selectedOption == 3)
	    {
		e = genFireDmg(item);
  	    }
	    else if(selectedOption == 4)	
	    {
		e = genPowerUp(item);
	    }
	}
	else
	{
	    return testEnchantment; 
	}

	return e;
    }

    /**************************************************************************
    *  SUBMODULE: genBasicDmg                                                 *
    *  IMPORT: item (Item)                                                    *
    *  EXPORT: basicDmg (Enchantment)                                         *
    *  ASSERTION: Encapsulate an Item with the basic damage enchantment       *
    ***************************************************************************/

    private Enchantment genBasicDmg(Item item)    
    {	
	return new EnchantmentBasicDmg(item); 
    }

    /**************************************************************************
    *  SUBMODULE: genBigDmg                                                   *
    *  IMPORT: item (Item)                                                    *
    *  EXPORT: bigDmg (Enchantment)                                           *
    *  ASSERTION: Encapsulate an Item with the big damage enchantment         *
    ***************************************************************************/

    private Enchantment genBigDmg(Item item)
    {
	return new EnchantmentBigDmg(item); 
    }

    /**************************************************************************
    *  SUBMODULE: genFireDmg                                                  *
    *  IMPORT: item (Item)                                                    *
    *  EXPORT: fireDmg (Enchantment)                                          *
    *  ASSERTION: Encapsulate an Item with the fire damage enchantment        *
    ***************************************************************************/

    private Enchantment genFireDmg(Item item)
    {
	return new EnchantmentFireDmg(item); 
    }

    /**************************************************************************
    *  SUBMODULE: genPowerUp                                                  *
    *  IMPORT: item (Item)                                                    *
    *  EXPORT: powerUp (Enchantment)                                          *
    *  ASSERTION: Encapsulate an Item with the powerup damage enchantment     *
    ***************************************************************************/
 
    private Enchantment genPowerUp(Item item)
    {
	return new EnchantmentPowerUp(item); 
    }

}
