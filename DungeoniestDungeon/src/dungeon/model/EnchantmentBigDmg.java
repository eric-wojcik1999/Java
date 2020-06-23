package dungeon.model; 

import dungeon.model.Item; 

/**************************************************************************
*  Class: Enchantment                                                     *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Big dmg enchantment used to encapsulate Item with             *
*  functionality                                                          *
***************************************************************************/

public class EnchantmentBigDmg extends Enchantment
{
    
    //CONSTRUCTOR 

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inItem (Item)                                                  *
    *  EXPORT: none                                                           *
    *  ASSERTION: A EnchantmentBigDmg object initialised with valid           *
    *  parameters                                                             *
    ***************************************************************************/

    public EnchantmentBigDmg(Item inItem)
    {
	super(inItem);
    }

    /**************************************************************************
    *  SUBMODULE: use                                                         *
    *  IMPORT: void                                                           *
    *  EXPORT: effect (int)                                                   *
    *  ASSERTION: Returns results of use + 5                                  *
    ***************************************************************************/

    @Override
    public int use()
    {
      return item.use() + 5;  
    }

    /**************************************************************************
    *  SUBMODULE: getName                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: name (String)                                                  *
    *  ASSERTION: Return results of getName() + xtra-dmg                      *
    ***************************************************************************/

    @Override
    public String getName()
    {
      return item.getName() + " + XTRA-DMG";
    }

    /**************************************************************************
    *  SUBMODULE: getCost                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: cost (int)                                                     *
    *  ASSERTION: Return result from getCost() + 10                           *
    ***************************************************************************/


    @Override
    public int getCost()
    {
        return item.getCost() + 10;
    }   
}
