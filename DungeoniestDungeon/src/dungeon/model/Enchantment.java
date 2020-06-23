package dungeon.model; 

import dungeon.model.Item; 

/**************************************************************************
*  Class: Enchantment                                                     *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Base class for any enchantment. Lists all potential methods   *
*  so base class can override specific ones                               *
***************************************************************************/

public abstract class Enchantment implements Item
{
    //Decoration superclass - overrides all methods 
    protected Item item; 

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inItem (Item)                                                  *
    *  EXPORT: none                                                           *
    *  ASSERTION: A Enchantment object initialised with valid parameters      *
    ***************************************************************************/
    
    public Enchantment(Item inItem)
    {
	this.item = inItem; 
    }

    /**************************************************************************
    *  SUBMODULE: use                                                         *
    *  IMPORT: void                                                           *
    *  EXPORT: effect (int)                                                   *
    *  ASSERTION: Returns an effect value which is a random value between     *
    *  minEffect and maxEffect                                                *
    ***************************************************************************/

    @Override
    public int use() 
    {
	return item.use(); 
    }

    /**************************************************************************
    *  SUBMODULE: getName                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: name (String)                                                  *
    *  ASSERTION: Return a name value                                         *
    ***************************************************************************/

    @Override
    public String getName()
    {
	return item.getName(); 
    }

    /**************************************************************************
    *  SUBMODULE: getCost                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: cost (int)                                                     *
    *  ASSERTION: Return a cost value                                         *
    ***************************************************************************/

    @Override 
    public int getCost()
    {
        return item.getCost(); 
    }

    /**************************************************************************
    *  SUBMODULE: getMinEffect                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: minEffect (int)                                                *
    *  ASSERTION: Return a minEffect value                                    *
    ***************************************************************************/

    @Override
    public int getMinEffect()
    {
 	return item.getMinEffect(); 
    } 

    /**************************************************************************
    *  SUBMODULE: getMaxEffect                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: maxEffect (int)                                                *
    *  ASSERTION: Return a maxEffect value                                    *
    ***************************************************************************/

    @Override 
    public int getMaxEffect()
    {
	return item.getMaxEffect(); 
    }

    /**************************************************************************
    *  SUBMODULE: getItemType                                                 *
    *  IMPORT: none                                                           *
    *  EXPORT: ITEM_TYPE (String)                                             *
    *  ASSERTION: Return the ITEM_TYPE of an item                             *
    ***************************************************************************/

    @Override
    public String getItemType()
    {
	return item.getItemType();
    }

    /**************************************************************************
    *  SUBMODULE: setName                                                     *
    *  IMPORT: inName (String)                                                *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets name to a valid name value                             *
    ***************************************************************************/

    @Override 
    public void setName(String inName)
    {
	item.setName(inName);
    }

    /**************************************************************************
    *  SUBMODULE: setCost                                                     *
    *  IMPORT: inCost (int)                                                   *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets cost to a valid cost value                             *
    ***************************************************************************/

    @Override 
    public void setCost(int inCost)
    {
	item.setCost(inCost);
    }


    /**************************************************************************
    *  SUBMODULE: setMinEffect                                                *
    *  IMPORT: inMinEffect  (int)                                             *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets min effect to a valid min effect value                 *
    ***************************************************************************/

    @Override
    public void setMinEffect(int inMinEffect)
    {
	item.setMinEffect(inMinEffect); 
    }

    /**************************************************************************
    *  SUBMODULE: setMaxEffect                                                *
    *  IMPORT: inMaxEffect  (int)                                             *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets max effect to a valid max effect value                 *
    ***************************************************************************/

    @Override
    public void setMaxEffect(int inMaxEffect)
    {
	item.setMaxEffect(inMaxEffect); 
    }

    /**************************************************************************
    *  SUBMODULE: toString                                                    *
    *  IMPORT: none                                                           *
    *  EXPORT: toString (String)                                              *
    *  ASSERTION: Returns transcript of object state in string form           *
    ***************************************************************************/

    @Override
    public String toString()
    {
	return item.toString(); 
    }

    /**************************************************************************
    *  SUBMODULE: equal                                                       *
    *  IMPORT: inObj (Object)                                                 *
    *  EXPORT: isEquals (boolean)                                             *
    *  ASSERTION: Check if two objects are the same                           *
    ***************************************************************************/

    @Override
    public boolean equal(Object inObject)
    {
	return item.equal(inObject); 
    } 
}
