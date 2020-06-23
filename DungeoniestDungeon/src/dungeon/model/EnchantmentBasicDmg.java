package dungeon.model; 

import dungeon.model.Item; 

/**************************************************************************
*  Class: Enchantment                                                     *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Basic dmg enchantment used to encapsulate Item with           *
*  functionality                                                          *
***************************************************************************/

public class EnchantmentBasicDmg extends Enchantment
{

    //CONSTRUCTOR 

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inItem (Item)                                                  *
    *  EXPORT: none                                                           *
    *  ASSERTION: A EnchantmentBasicDmg object initialised with valid         *
    *  parameters                                                             *
    ***************************************************************************/

    public EnchantmentBasicDmg(Item inItem)
    {
	super(inItem);
    } 

    /**************************************************************************
    *  SUBMODULE: use                                                         *
    *  IMPORT: void                                                           *
    *  EXPORT: effect (int)                                                   *
    *  ASSERTION: Returns results of use + 2                                  *
    ***************************************************************************/

    @Override
    public int use()
    {
      return item.use() + 2;  
    }

    /**************************************************************************
    *  SUBMODULE: getName                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: name (String)                                                  *
    *  ASSERTION: Return results of getName() + bsc-dmg                       *
    ***************************************************************************/

    @Override
    public String getName()
    {
      return item.getName() + " + BSC-DMG";
    }

    /**************************************************************************
    *  SUBMODULE: getCost                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: cost (int)                                                     *
    *  ASSERTION: Return result from getCost() + 5                            *
    ***************************************************************************/

    @Override
    public int getCost()
    {
        return item.getCost() + 5;
    }   
}
