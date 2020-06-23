package dungeon.model; 

import dungeon.model.Item; 

import java.lang.*; 

/**************************************************************************
*  Class: Enchantment                                                     *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Fire dmg enchantment used to encapsulate Item with            *
*  functionality                                                          *
***************************************************************************/

public class EnchantmentFireDmg extends Enchantment
{
    
    //CONSTRUCTOR 

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inItem (Item)                                                  *
    *  EXPORT: none                                                           *
    *  ASSERTION: A EnchantmentFireDmg object initialised with valid          *
    *  parameters                                                             *
    ***************************************************************************/

    public EnchantmentFireDmg(Item inItem)
    {
	super(inItem);
    }

    /**************************************************************************
    *  SUBMODULE: use                                                         *
    *  IMPORT: void                                                           *
    *  EXPORT: effect (int)                                                   *
    *  ASSERTION: Returns results of use + rand(5-10)                         *
    ***************************************************************************/

    @Override
    public int use()
    {
      return (int) (item.use() + (Math.random()*((10-5)+1)+5));  
    }

    /**************************************************************************
    *  SUBMODULE: getName                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: name (String)                                                  *
    *  ASSERTION: Return results of getName() + fire-dmg                      *
    ***************************************************************************/

    @Override
    public String getName()
    {
      return item.getName() + " + FIRE-DMG";
    }

    /**************************************************************************
    *  SUBMODULE: getCost                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: cost (int)                                                     *
    *  ASSERTION: Return result from getCost() + 20                           *
    ***************************************************************************/

    @Override
    public int getCost()
    {
        return item.getCost() + 20;
    }   
}
